package top.jessehzx.rabbitmq.demo.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 功能: RabbitMQ 发送端（生产者）
 *
 * @author jessehzx
 * @Date 2018/3/23
 * @since 1.0
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息
        String message = "Hello World!";
        // 消息体
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        // 关闭服务
        channel.close();
        connection.close();
    }
}
