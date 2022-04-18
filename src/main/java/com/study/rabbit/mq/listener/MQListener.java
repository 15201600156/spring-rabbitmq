package com.study.rabbit.mq.listener;

import com.rabbitmq.client.Channel;
import com.study.rabbit.mq.constant.MQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RabbitListener(queues = MQConstant.QUEUE)
@Component
public class MQListener {

    @RabbitHandler
    public void handleStockLockedRelease(Map<String, Object> heads ,Message message, Channel channel) throws IOException {
        log.info("进入消息消费");
        try {
            log.info("消费成功");
            // 解锁成功，手动确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.info("消费失败");
            // 解锁失败，消息入队
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
