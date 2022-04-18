package com.study.rabbit.mq.config;

import com.study.rabbit.mq.constant.MQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 创建队列，交换机，延时队列，绑定关系 的configuration
 * 1.Broker中的Queue、Exchange、Binding不存在的情况下，会自动创建（在RabbitMQ），不会重复创建覆盖
 * 2.懒加载，只有第一次使用的时候才会创建（例如监听队列）
 */
@Configuration
public class MyRabbitMQConfig {



    //一个交换机，两个队列，两个路由键


    //正常的队列和死信队列公用一个交换机

    //假如：如果支付订单的时候，会将库存锁定，但是如果订单支付失败的话，就需要解锁库存。

    //    1、支付订单的时候先给死信队列发送消息， 通过死信的交换机和路由键发送到死信队列当中
    //    2、死信队列当中设置超时的时间后，会将此消息发布到正常的队列当中，让去解锁  死信队列当中绑定了超时时间、交换机、路由键，通过交换机和路由键的匹配发送到正常的队列当中

    //所以：死信队列绑定的是正常的交换机、通过哪个路由键发送到正常队列当中
    //      消息通过正常的交换机和路由键发送到正常队列当中。



    /**
     * 死信队列的创建
     * @return
     */
    @Bean
    public Queue delayQueue()
    {
        //String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MQConstant.EVENT_EXCHANGE);// 死信路由    意思就是消息到达TTL时间后通过此交换机发送到哪个队列当中
        arguments.put("x-dead-letter-routing-key", MQConstant.RELEASE_ROUTING_KEY);// 死信路由键 消息到达TTL时间后将此交换机当中的队列通过死信通知路由键发送到要处理的交换机上
        arguments.put("x-message-ttl", 30000); // 消息过期时间 1分钟
        return new Queue(MQConstant.DELAY_QUEUE,true,false,false,arguments);
    }
    /**
     * 正常队列的创建
     * @return
     */
    @Bean
    public Queue queue()
    {
        //String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments
        return new Queue(MQConstant.QUEUE,true,false,false);
    }

        /**
     * 正常交换机的创建
     * @return
     */
    @Bean
    public TopicExchange eventExchange()
    {
        //String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments
        return new TopicExchange(MQConstant.EVENT_EXCHANGE,true,false);
    }


        /**
     * 绑定：交换机与死信队列绑定
     */
    @Bean
    public Binding delayNotifyBinding() {
        return new Binding(MQConstant.DELAY_QUEUE,
                Binding.DestinationType.QUEUE,
                MQConstant.EVENT_EXCHANGE,
                MQConstant.CREATE_ROUTING_KEY,
                null);
    }


    /**
     * 绑定：正常交换机和队列的绑定
     */
    @Bean
    public Binding binding() {
        /**
         * String destination, 目的地（队列名或者交换机名字）
         * DestinationType destinationType, 目的地类型（Queue、Exhcange）
         * String exchange,
         * String routingKey,
         * Map<String, Object> arguments
         **/
        return new Binding(MQConstant.QUEUE,
                Binding.DestinationType.QUEUE,
                MQConstant.EVENT_EXCHANGE,
                MQConstant.RELEASE_ROUTING_KEY,
                null);
    }
}
