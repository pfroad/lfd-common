package com.lfd.frontend.common.mq;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */
public class ConsumerServer {
    private List<ConsumerBean> consumerBeanList;

    public List<ConsumerBean> getConsumerBeanList() {
        return consumerBeanList;
    }

    public void setConsumerBeanList(List<ConsumerBean> consumerBeanList) {
        this.consumerBeanList = consumerBeanList;
    }

    public void start() {
        if (this.consumerBeanList != null) {
            consumerBeanList.stream().forEach(it -> it.start());
        }
    }

    public void shutdown() {
        if (this.consumerBeanList != null) {
            consumerBeanList.stream().forEach(it -> it.shutdown());
        }
    }
}
