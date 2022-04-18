package com.study.rabbit.mq.config;

public class BlogNewIdeas {
    public static void main(String[] args) throws Exception {
//        //1. 消费者1接口：
//        Channel channel = MqConnectUtil.getChannel();
//        // 声明死信和普通交换机，类型为direct
//        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
//        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
//        // 声明普通队列
//        Map<String, Object> arguments = new HashMap<>();
//        // 过期时间
//        arguments.put("dead-ttl", 10000);
//        // 正常队列设置死信交换机
//        arguments.put("dead-exchange", DEAD_EXCHANGE);
//        // 设置死信routing-key
//        arguments.put("dead-routingKey", "lisi");
//        channel.queueDeclare(NORMAL_QUEUE, false, false, false, arguments);
//
//        // 声明死信队列
//        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
//
//        // 绑定普通的交换机与队列
//        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "zhangsan");
//        // 绑定死信的交换机与队列
//        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lisi");
//
//        System.out.println("等待接收消息。。。。。");
//
//        channel.basicConsume(NORMAL_QUEUE, true, (counsumerTag, message) -> {
//            System.out.println("consumer01接收的消息是：" + new String(message.getBody(), "UTF-8"));
//        }, (cancelCallback) -> {});


//        //2. 生产者接口：
//        public static void main(String[] args) throws Exception {
//            Channel channel = MqConnectUtil.getChannel();
//
//            // 死信消息，设置TTL时间 time to live
//            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
//
//            for (int i = 0; i < 10; i++) {
//                String msg = i + "";
//                channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", properties, msg.getBytes());
//            }
//        }
    }

}
