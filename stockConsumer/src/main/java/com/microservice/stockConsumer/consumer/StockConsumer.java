package com.microservice.stockConsumer.consumer;

import constants.RabbitMQConstants;
import dto.StockDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StockConsumer {

    @RabbitListener(queues = RabbitMQConstants.STOCK_QUEUE)
    private void consumer(String message) throws JsonProcessingException, InterruptedException {
        StockDto stockDto = new ObjectMapper().readValue(message, StockDto.class);

        System.out.println(stockDto.productCode);
        System.out.println(stockDto.quantity);
        System.out.println("---------------------------");

//        throw new IllegalArgumentException("Invalid argument");
    }

}
