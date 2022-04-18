package com.study.rabbit.mq;

import com.study.rabbit.mq.constant.MQConstant;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MqApplicationTests {

    @Autowired
    private RabbitTemplate   rabbitTemplate;

    /***
     * 发送消息体为简单数据类型的消息
     */
    @Test
    public void send() {
        Map<String, Object> heads = new HashMap<>();
        heads.put("msgInfo", "自定义消息头信息");
        // 模拟生成消息 ID,在实际中应该是全局唯一的 消息不可达时候可以在 setConfirmCallback 回调中取得，可以进行对应的重发或错误处理
        String id = String.valueOf(Math.round(Math.random() * 10000));

        rabbitTemplate.convertAndSend(MQConstant.EVENT_EXCHANGE,MQConstant.RELEASE_ROUTING_KEY,heads);

    }

    //插入死信队列
    @Test
    public void sendDelay() {
        Map<String, Object> heads = new HashMap<>();
        heads.put("msgInfo", "死信消息");
        // 模拟生成消息 ID,在实际中应该是全局唯一的 消息不可达时候可以在 setConfirmCallback 回调中取得，可以进行对应的重发或错误处理
        String id = String.valueOf(Math.round(Math.random() * 10000));
        rabbitTemplate.convertAndSend(MQConstant.EVENT_EXCHANGE,MQConstant.CREATE_ROUTING_KEY,heads);
    }

}
