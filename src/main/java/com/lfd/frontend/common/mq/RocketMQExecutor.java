package com.lfd.frontend.common.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.TransactionProducerBean;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;

/**
 * Created by Administrator on 12/19/2016.
 */
public class RocketMQExecutor {
    private ProducerBean producerBean;

    private TransactionProducerBean transactionProducerBean;

    public SendResult send(Message message) {
        SendResult sendResult = producerBean.send(message);
        assert sendResult != null;
        return sendResult;
    }

    private SendResult send(Message message, LocalTransactionExecuter transactionExecutor) {
        return transactionProducerBean.send(message, transactionExecutor, null);
    }

    public ProducerBean getProducerBean() {
        return producerBean;
    }

    public void setProducerBean(ProducerBean producerBean) {
        this.producerBean = producerBean;
    }

    public TransactionProducerBean getTransactionProducerBean() {
        return transactionProducerBean;
    }

    public void setTransactionProducerBean(TransactionProducerBean transactionProducerBean) {
        this.transactionProducerBean = transactionProducerBean;
    }
}
