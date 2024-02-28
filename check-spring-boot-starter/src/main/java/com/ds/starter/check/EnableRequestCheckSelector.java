package com.ds.starter.check;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ds
 * @date 2024/2/20
 * @description
 */
public class EnableRequestCheckSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{RequestCheckAspect.class.getName()};
    }

}
