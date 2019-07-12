package com.parking.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.Test;

import com.parking.ridha.exception.ParkingException;
import com.parking.ridha.model.Car;
import com.parking.ridha.model.CarTypeEnum;
import com.parking.ridha.model.Parking;
import com.parking.ridha.service.PricingPolicy;

public class CarTest {
	
	@Test(expected = ParkingException.class)
	public void throwExceptionWithoutMatriculeTest() {
		Car.builder().setType(CarTypeEnum.GASOLINE).build();
	}

	@Test(expected = ParkingException.class)
	public void throwExceptionWithoutTypeTest() {
		Car.builder().setMatricule("XX-111-YY").build();
	}
	
	@Test(expected = ParkingException.class)
	public void checkCarWithoutCheckinDateTest() {
		Car car = Car.builder().setType(CarTypeEnum.GASOLINE).setMatricule("XX-111-YY").build();
		car.setCheckinDate(LocalDateTime.now());
		car.checkDatesAfterCheckout();
	}
	

	@Test(expected = ParkingException.class)
	public void checkCarWithoutCheckoutDateTest() {
		Car car = Car.builder().setType(CarTypeEnum.GASOLINE).setMatricule("XX-111-YY").build();
		car.setCheckoutDate(LocalDateTime.now());
		car.checkDatesAfterCheckout();
	}
	
	@Test(expected = ParkingException.class)
	public void checkCarWithIncoherentDatesTest() {
		Car car = Car.builder().setType(CarTypeEnum.GASOLINE).setMatricule("XX-111-YY").build();
		car.setCheckinDate(LocalDateTime.now());
		car.setCheckoutDate(car.getCheckinDate().minusHours(1));
		car.checkDatesAfterCheckout();
	}
}
