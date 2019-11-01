package com.daniel.rabbitapp.senders;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderQueueSender {

	@Autowired
    private RabbitTemplate rabbitTemplate;
 
    @Autowired
    private Queue queue;
    
    @Value("${queue.teste.name}")
    private String queueTeste;
 
    public void send() {
    	System.out.println("Enviando para a fila " + this.queue.getName() + "\n");
        rabbitTemplate.convertAndSend(this.queue.getName(), "{\"name\":\"John\", \"age\":31, \"city\":\"New York\"}");
        System.out.println("Mensagem enviada.");
    }
    
    public void sendTeste(String mensagem) {
    	System.out.println("Enviando para a fila " + queueTeste + "\n");
        rabbitTemplate.convertAndSend(queueTeste, mensagem);
        System.out.println("Mensagem enviada.");
    }
	
}
