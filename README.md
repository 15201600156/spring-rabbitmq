# spring-rabbitmq
Spring整合RabbitMQ

##1、配置文件
spring:
  rabbitmq:
    addresses: 10.10.3.104
    # RabbitMQ 默认的用户名和密码都是 guest 而虚拟主机名称是 "/"
    # 如果配置其他虚拟主机地址，需要预先用管控台或者图形界面创建 图形界面地址 http://主机地址:15672
    username: guest
    password: guest
    virtual-host: /    #虚拟主机
    # 开启发送端发送确认，无论是否到达broker都会触发回调【发送端确认机制+本地事务表】
    publisher-confirm-type: correlated
    # 开启发送端抵达队列确认，消息未被队列接收时触发回调【发送端确认机制+本地事务表】
    publisher-returns: true
    # 消息在没有被队列接收时是否强行退回
    template:
      mandatory: true
    # 消费者手动确认模式，关闭自动确认，否则会消息丢失
    listener:
      simple:
        acknowledge-mode: manual


##2 抽取公共处理
MQ当中消息内容的序列号
消息发送成功或者失败的处理方法 （可以解决消息丢失的问题）


##3 配置死信
可以解决类似，下订单锁定库存，但是用户在支付的时候取消了支付，这个时候就有一个30min内支付有效，但是过了三十分钟后会自动取消订单，解锁库存，这个时候就可以使用死信。

用户在点击支付的时候往MQ的延迟队列推送消息，延迟队列的时间是30分钟。
这个时候等30分钟后就会往对应配置的队列托送消息
在监听者一方可以处理消息，进而解锁库存，消息订单