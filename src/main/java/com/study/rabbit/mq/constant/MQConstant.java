package com.study.rabbit.mq.constant;

public interface MQConstant {

    //死信队列
    static String DELAY_QUEUE = "delay.queue";


    //正常交换机
    static String EVENT_EXCHANGE="event-exchange";

    //正常队列
    static String QUEUE = "queue";

    //正常路由键
    static String CREATE_ROUTING_KEY = "create.routing.key";



    //死信队列到TTL时间后通过此路由键进行消息传递
    static String RELEASE_ROUTING_KEY="release.routing.key";
}
