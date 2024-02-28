package com.ds.starter.check;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.PhoneUtil;
import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.ResultMsg;
import com.ds.nas.lib.common.util.StringUtils;
import com.ds.starter.check.annotion.Check;

import java.lang.reflect.Field;

/**
 * @author ds
 * @date 2023/1/22
 * @description 入参校验
 */
public class RequestCheck {

    /**
     * 参数校验
     *
     * @param request
     */
    public static void check(BaseRequest request) {
        String msg = ResultMsg.PARAMETER_ERROR_MSG;
        if (request == null)
            throw new BusinessException(msg);
        try {
            Class<? extends BaseRequest> clazz = request.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object value = declaredField.get(request);
                Check check = declaredField.getDeclaredAnnotation(Check.class);
                if (check != null) {
                    // 判断是否必输
                    if (check.require()) {
                        msg = genMsg(check, declaredField, "为必输!");
                        // 如果为null，直接抛异常
                        if (value == null)
                            throw new BusinessException(msg);
                        // 如果为字符串的情况
                        if (value instanceof String && StringUtils.isEmpty((String) value))
                            throw new BusinessException(msg);
                    }

                    // 判断最大长度，maxLen大于0才进行校验
                    if (check.maxLen() > 0) {
                        msg = genMsg(check, declaredField, "最大长度为" + check.maxLen() + "!");
                        // 如果为字符串的情况
                        if (value instanceof String && StringUtils.length((String) value) > check.maxLen())
                            throw new BusinessException(msg);
                    }

                    // 判断是否只能为数字(只针对字符串)
                    if (check.onlyNumber()) {
                        msg = genMsg(check, declaredField, "请传入数字!");
                        if (value instanceof String && !NumberUtil.isNumber((String) value))
                            throw new BusinessException(msg);
                    }

                    // 判断是否为合格身份证号码(只针对字符串)
                    if (check.idCard()) {
                        //  msg = StringUtils.isNotBlank(check.msg()) ? check.msg() : "[" + declaredField.getName() + "]请传入合格身份证号码!!";
                        msg = genMsg(check, declaredField, "无效!");
                        if (!IdcardUtil.isValidCard((String) value))
                            throw new BusinessException(msg);
                    }

                    // 判断是否为合格手机号码（中国大陆）
                    if (check.isMobile()) {
                        //  msg = StringUtils.isNotBlank(check.msg()) ? check.msg() : "[" + declaredField.getName() + "]请传入合格手机号码!!";
                        msg = genMsg(check, declaredField, "无效!");
                        if (!PhoneUtil.isMobile((String) value))
                            throw new BusinessException(msg);
                    }

                }
            }
        } catch (Exception e) {
            throw new BusinessException(msg);
        }
    }

    /**
     * 生成检验不通过返回消息
     *
     * @param check         参数校验注解
     * @param declaredField 当前校验字段
     * @param errorMsg      检验不通过返回消息
     * @return
     */
    private static String genMsg(Check check, Field declaredField, String errorMsg) {
        // 注解报错字段名
        String fieldName = check.fieldName();
        return StringUtils.isNotBlank(check.msg()) ? check.msg() : "[" + (StringUtils.isNotBlank(fieldName) ? fieldName : declaredField.getName()) + "]" + errorMsg;
    }

}
