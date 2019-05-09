package com.wyl.webdemo.annotation;

import com.wyl.webdemo.common.enums.ModuleEnums;
import com.wyl.webdemo.common.enums.OperationKinds;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnalysisActuator {
    ModuleEnums module();
    OperationKinds oper();
    String opDetail() default "";
}
