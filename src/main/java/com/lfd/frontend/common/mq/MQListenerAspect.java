package com.lfd.frontend.common.mq;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import com.airparking.cloud.entity.other.MQFailed;

/**
 * Created by Administrator on 2017/1/13.
 */
@Aspect
public class MQListenerAspect {
    private static final Logger logger = LoggerFactory.getLogger(MQListenerAspect.class);

    private ProducerBean producerBean;

    private Map<String, Integer> retryMap = new ConcurrentHashMap<>();

    public MQListenerAspect() {
    }

    public MQListenerAspect(ProducerBean producerBean) {
        this.producerBean = producerBean;
    }

    @Pointcut("@annotation(AfterConsumeFailed)")
    public void listenerPointcut() {
    }

    @AfterThrowing(pointcut = "listenerPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        logger.error("请求失败：", e);
    }

    @Around("listenerPointcut()")
    public Object aroundConsume(ProceedingJoinPoint joinPoint) {
        Message message = (Message) joinPoint.getArgs()[0];
        String className = joinPoint.getTarget().getClass().getName();
        logger.info("{} received msg id={}, content={} ", className, message.getMsgID(), new String(message.getBody()));

        try {
            return joinPoint.proceed();
        } catch (MQListenerException e) {
            logger.error("Failed to handle MQ message:", e);
            logger.info("Store failed message information.");
            try {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                Method method = methodSignature.getMethod();

                AfterConsumeFailed afterConsumeFailed = method.getAnnotation(AfterConsumeFailed.class);

                if (afterConsumeFailed != null) {
                    int retry = afterConsumeFailed.retry();
                    int hasRetry = retryMap.containsKey(message.getMsgID()) ? retryMap.get(message.getMsgID()) : 0;
                    if (retry > 0 &&  hasRetry < retry) {
                        retryMap.put(message.getMsgID(), hasRetry + 1);
                        return Action.ReconsumeLater;
                    }

//                    MQFailed mqFailed = new MQFailed();
//                    mqFailed.setMessageId(message.getMsgID());
//                    mqFailed.setTopic(message.getTopic());
//                    mqFailed.setTag(message.getTag());
//                    mqFailed.setListenerClass(className);
//                    mqFailed.setContent(new String(message.getBody()));
//                    mqFailed.setMqKey(message.getKey());
//                    mqFailed.setDescription(ExceptionUtils.getExceptionInfo(e));
//
//                    Message failedMessage = new Message(afterConsumeFailed.mqFailedTopic(),
//                            afterConsumeFailed.mqFailedTag(),
//                            JsonUtils.toJson(mqFailed).getBytes());
//                    SendResult sendResult = producerBean.send(failedMessage);

                    if (retryMap.containsKey(message.getMsgID())) {
                        retryMap.remove(message.getMsgID());
                    }

                    return Action.CommitMessage;
                }
            } catch (Exception e1) {
                logger.error("Exception for send failed message!", e);
            }
        } catch (Throwable throwable) {
            logger.error("Failed to handle message", throwable);
        }

        return Action.ReconsumeLater;
    }

//    private String getExceptionInfo(Exception e) {
//        StackTraceElement[] trace = e.getStackTrace();
//
//        StringBuilder builder = new StringBuilder();
//        for (StackTraceElement traceElement : trace) {
//            builder.append("\tat ");
//            builder.append(traceElement.toString());
//        }
//
//        return builder.toString();
//    }

    public ProducerBean getProducerBean() {
        return producerBean;
    }

    public void setProducerBean(ProducerBean producerBean) {
        this.producerBean = producerBean;
    }
}
