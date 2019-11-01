package com.daniel.rabbitapp.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.daniel.rabbitapp.senders.OrderQueueSender;

@Component
public class OrderConsumer {

	@Autowired
	private OrderQueueSender sender;
	
	@RabbitListener(queues = {"${queue.order.name}"})
    public void receive(@Payload String order) {
		System.out.println("Lendo da fila\n");
		System.out.println("Order: " + order);
		sender.sendTeste(order);
    }
	
}
