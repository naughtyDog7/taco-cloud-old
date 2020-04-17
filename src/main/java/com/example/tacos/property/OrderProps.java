package com.example.tacos.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@Data
@ConfigurationProperties("taco.orders")
@Validated
public class OrderProps {

    @Min(value=5, message="Orders List Length should be in range 5-25")
    @Max(value=25, message="Orders List Length should be in range 5-25")
    private int ordersListLength;
}
