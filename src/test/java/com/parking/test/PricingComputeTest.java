package com.parking.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import com.parking.ridha.model.Car;
import com.parking.ridha.model.CarTypeEnum;
import com.parking.ridha.service.PricingPolicy;

public class PricingComputeTest {


    @Test
    public void computeHourPriceTest() {
    	Car car = Car.builder().setMatricule("XX-111-YY").setType(CarTypeEnum.GASOLINE).build();
    	car.setCheckinDate(LocalDateTime.now());
    	car.setCheckoutDate((LocalDateTime.now().plusHours(2)));
    	PricingPolicy policy = PricingPolicy.instance.initPricingPolicy(2);
        double price = policy.computePrice(car);
        assertThat(price).isEqualTo(4);
    }
    
    
    @Test
    public void computeExtraAmountPriceTest() {
    	Car car = Car.builder().setMatricule("XX-111-YY").setType(CarTypeEnum.GASOLINE).build();
    	car.setCheckinDate(LocalDateTime.now());
    	car.setCheckoutDate((LocalDateTime.now().plusHours(2)));
    	PricingPolicy policy = PricingPolicy.instance.initPricingPolicy(2,3);
        double price = policy.computePrice(car);
        assertThat(price).isEqualTo(7);
    }
}