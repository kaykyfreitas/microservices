package com.microservice.sales.connections;

import com.microservice.sales.constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String EXCHANGE_NAME = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        Queue stockQueue = this.queue(RabbitMQConstants.STOCK_QUEUE);
        Queue priceQueue = this.queue(RabbitMQConstants.PRICE_QUEUE);

        DirectExchange exchange = this.directExchange();

        Binding stockBind = this.binding(stockQueue, exchange);
        Binding priceBind = this.binding(priceQueue, exchange);

        // Creating rabbitmq queues
        this.amqpAdmin.declareQueue(stockQueue);
        this.amqpAdmin.declareQueue(priceQueue);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(stockBind);
        this.amqpAdmin.declareBinding(priceBind);

    }

}
