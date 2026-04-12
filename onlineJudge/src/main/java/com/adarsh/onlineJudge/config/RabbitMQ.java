package com.adarsh.onlineJudge.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQ {
   public static final String QUEUE_NAME = "submissions";
   public static final String EXCHANGE_NAME = "submissions.exchange";
   public static final String ROUNTING_KEY = "submissions.routing.key";

   @Bean
    public Queue queue(){
       return new Queue(QUEUE_NAME , true);
   }
   @Bean
    public DirectExchange exchange(){
       return new DirectExchange(EXCHANGE_NAME);
   }
   @Bean
    public Binding binding(){
      return  BindingBuilder
               .bind(queue())
               .to(exchange())
               .with(ROUNTING_KEY);
   }
    @Bean
    public MessageConverter messageConverter() {
        return new org.springframework.amqp.support.converter.SimpleMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
