package top.jessehzx.rabbitmq.demo.publishsubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 功能: EmitLog（生产者）
 *
 * @author jessehzx
 * @Date 2018/3/24
 * @since 1.0
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 指定一个交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 发送消息
        String message = getMessage();
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        // 关闭资源
        channel.close();
        connection.close();
    }
    
    private static String getMessage() {
        return "hello, jessehzx";
    }
}
