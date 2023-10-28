package io.github.kenneth.framework;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${mq.queues.book-reserve}")
    private String queue;

    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

}