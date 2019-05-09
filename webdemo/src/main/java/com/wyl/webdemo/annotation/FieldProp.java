package com.wyl.webdemo.annotation;

import org.apache.commons.lang3.EnumUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface FieldProp {
    String name();
    String label();
    boolean ignore() default false;
    boolean needDetail() default true;
    boolean needChange() default false;
    Class<?> targetEnum() default Enum.class;
}
