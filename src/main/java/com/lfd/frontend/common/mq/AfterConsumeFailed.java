package com.lfd.frontend.common.mq;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/1/13.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AfterConsumeFailed {
    String mqFailedTopic();

    String mqFailedTag();

    int retry() default 1;
}
