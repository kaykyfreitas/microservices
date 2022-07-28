package com.microservice.sales.controller;

import com.microservice.sales.constants.RabbitMQConstants;
import com.microservice.sales.dto.PriceDto;
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
@RequestMapping(value = "price")
public class PriceController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alterPrice(@RequestBody PriceDto priceDto) {
        System.out.println(priceDto.productCode);

        this.rabbitMQService.sendMessage(RabbitMQConstants.PRICE_QUEUE, priceDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
