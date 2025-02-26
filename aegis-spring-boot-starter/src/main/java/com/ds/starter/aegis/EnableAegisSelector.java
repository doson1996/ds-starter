package com.ds.starter.aegis;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ds
 * @date 2025/2/26
 * @description
 */
public class EnableAegisSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{AegisAutoConfiguration.class.getName()};
    }

}
