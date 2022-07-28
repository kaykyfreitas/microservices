package com.microservice.stockConsumer.consumer;

import constants.RabbitMQConstants;
import dto.StockDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {

    @RabbitListener(queues = RabbitMQConstants.STOCK_QUEUE)
    private void consumer(StockDto stockDto) {
        System.out.println(stockDto.productCode);
        System.out.println(stockDto.quantity);
        System.out.println("---------------------------");
    }

}
