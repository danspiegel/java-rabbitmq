package com.daniel.rabbitapp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.daniel.rabbitapp.senders.OrderQueueSender;

@SpringBootApplication
@EnableRabbit
public class RabbitAppApplication implements CommandLineRunner {

	@Value("${queue.order.name}")
    private String orderQueue;
	
	@Autowired
	private OrderQueueSender sender;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitAppApplication.class, args);
	}
	
	@Bean
    public Queue queue() {
        return new Queue(orderQueue, true);
    }

	@Override
	public void run(String... args) throws Exception {
		sender.send();
	}

}
