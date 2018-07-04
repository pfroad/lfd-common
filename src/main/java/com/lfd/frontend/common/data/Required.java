package com.lfd.frontend.common.data;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/1/19.
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    boolean value() default false;
}
