package com.lfd.frontend.common.mq;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/1/12.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface QueueListener {
    String consumerId();

    String topic();

    String expression();

    String description() default "";
}