package com.microservice.sales.controller;

import com.microservice.sales.constants.RabbitMQConstants;
import com.microservice.sales.dto.StockDto;
import com.microservice.sales.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "stock")
public class StockController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alterStock(@RequestBody StockDto stockDto) {
        System.out.println(stockDto.productCode);
        this.rabbitMQService.sendMessage(RabbitMQConstants.STOCK_QUEUE, stockDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
