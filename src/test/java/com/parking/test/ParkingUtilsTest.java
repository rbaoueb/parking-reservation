package com.parking.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Optional;

import org.junit.Test;

import com.parking.ridha.model.Car;
import com.parking.ridha.model.CarTypeEnum;
import com.parking.ridha.model.Parking;
import com.parking.ridha.model.ParkingSlot;
import com.parking.ridha.service.PricingPolicy;
import com.parking.ridha.util.ParkingUtils;

public class ParkingUtilsTest {
	@Test
	public void computeExtraAmountPriceTest() {
		ParkingUtils util = new ParkingUtils();
		Optional<ParkingSlot> result = ParkingUtils.getSlotByCar(null, null);
		assertThat(result).isEmpty();

		result = ParkingUtils.getSlotByCar(Collections.emptyList(), null);
		assertThat(result).isEmpty();

		Car car = Car.builder().setMatricule("XX-111-YY").setType(CarTypeEnum.GASOLINE).build();
		Parking parking = Parking.builder().addSlots(CarTypeEnum.GASOLINE, 2)
				.setPolicy(PricingPolicy.instance.initPricingPolicy(3)).build();
		
		parking.checkin(car);
		result = ParkingUtils.getSlotByCar(parking.getNbSlot().get(CarTypeEnum.GASOLINE), car);
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get().getCar()).isEqualTo(car);
	}
}
