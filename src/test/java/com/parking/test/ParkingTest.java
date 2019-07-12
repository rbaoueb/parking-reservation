package com.parking.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Optional;

import org.junit.Test;

import com.parking.ridha.exception.ParkingException;
import com.parking.ridha.model.Car;
import com.parking.ridha.model.CarTypeEnum;
import com.parking.ridha.model.Parking;
import com.parking.ridha.model.ParkingSlot;
import com.parking.ridha.service.PricingPolicy;

/**
 * Test class for parking reservation
 * 
 * @author mrbaoueb
 *
 */
public class ParkingTest {
	/**
	 * 
	 */
	@Test(expected = ParkingException.class)
	public void throwExceptionNegativeSlotsTest() {
		Parking.builder().addSlots(CarTypeEnum.GASOLINE, -1)
		.setPolicy(PricingPolicy.instance.initPricingPolicy(3)).build();
	}
	
	/**
	 * 
	 */
	@Test(expected = ParkingException.class)
	public void throwExceptionWithoutPolicyTest() {
		Parking.builder().addSlots(CarTypeEnum.GASOLINE, 5).build();
	}

	@Test
	public void throwExceptionWithoutSlotsTest() {
		try {
			Parking.builder().setPolicy(PricingPolicy.instance.initPricingPolicy(3)).build();
			fail("parking builder without slots throw a ParkingException");
		} catch (ParkingException e) {
			assertThat(true).isTrue();
		}
	}

	@Test
	public void parkReservationTest() {

		/*
		 * STEP 1 : Initializing the parking with 1 slot for GASOLINE and
		 * pricing policy with 3 euro for hour
		 */
		Parking parking = Parking.builder().addSlots(CarTypeEnum.GASOLINE, 1)
				.setPolicy(PricingPolicy.instance.initPricingPolicy(3)).build();

		/*
		 * STEP 2 : try to check-in a first GASOLINE car, the unique slot should
		 * be reserved
		 */
		Car firstGasolineCar = Car.builder().setMatricule("XX-111-YY").setType(CarTypeEnum.GASOLINE).build();

		assertThat(firstGasolineCar.getCheckinDate()).isNull();
		Optional<ParkingSlot> firstSlot = parking.checkin(firstGasolineCar);

		assertThat(firstSlot.isPresent()).isTrue();
		assertThat(firstSlot.get().getCar()).isEqualTo(firstGasolineCar);
		assertThat(firstGasolineCar.getCheckinDate()).isNotNull();
		assertThat(firstSlot.get().getCar().getMatricule()).isEqualTo("XX-111-YY");
		assertThat(parking.selectAllAvailableSlots(firstGasolineCar.getType())).isEqualTo(0);

		/*
		 * STEP 3 : try to check-in a second GASOLINE car, the reservation
		 * should fail because of the unique slot is already reserved by the
		 * first car
		 */
		Car secondGasolineCar = Car.builder().setMatricule("XX-222-YY").setType(CarTypeEnum.GASOLINE).build();

		assertThat(secondGasolineCar.getCheckinDate()).isNull();
		Optional<ParkingSlot> secondSlot = parking.checkin(secondGasolineCar);
		assertThat(secondSlot.isPresent()).isFalse();
		assertThat(secondGasolineCar.getCheckinDate()).isNull();
		assertThat(parking.selectAllAvailableSlots(CarTypeEnum.GASOLINE)).isEqualTo(0);

		/*
		 * STEP 4 : check-out the first car
		 */
		firstGasolineCar.setCheckinDate(firstGasolineCar.getCheckinDate().minusHours(2));
		double price = parking.checkout(firstGasolineCar);
		assertThat(firstGasolineCar.getCheckoutDate()).isNotNull();
		assertThat(price).isEqualTo(6d);
		assertThat(parking.selectAllAvailableSlots(CarTypeEnum.GASOLINE)).isEqualTo(1);

		/*
		 * STEP 4 : now we can check-in the second GASOLINE car !!
		 */
		assertThat(secondGasolineCar.getCheckinDate()).isNull();
		secondSlot = parking.checkin(secondGasolineCar);
		assertThat(secondSlot.isPresent()).isTrue();
		assertThat(secondSlot.get().getCar()).isEqualTo(secondGasolineCar);
		assertThat(secondGasolineCar.getCheckinDate()).isNotNull();
		assertThat(parking.selectAllAvailableSlots(CarTypeEnum.GASOLINE)).isEqualTo(0);

		/*
		 * STEP 5 : check-in an ELECTRIC50 car, the reservation should fail
		 * because we don't have any ELECTRIC50 slot in the parking
		 */
		Car electricCar = Car.builder().setMatricule("XX-333-YY").setType(CarTypeEnum.ELECTRIC50).build();

		assertThat(electricCar.getCheckinDate()).isNull();
		Optional<ParkingSlot> thirdSlot = parking.checkin(electricCar);
		assertThat(thirdSlot.isPresent()).isFalse();
		assertThat(electricCar.getCheckinDate()).isNull();
		assertThat(parking.selectAllAvailableSlots(CarTypeEnum.ELECTRIC50)).isEqualTo(0);

		/*
		 * STEP 6 : add 1 ELECTRIC50 slot to the existing parking
		 */
		parking = Parking.builder().addSlots(CarTypeEnum.GASOLINE, 1).addSlots(CarTypeEnum.ELECTRIC50, 1)
				.setPolicy(PricingPolicy.instance.initPricingPolicy(3)).build();

		/*
		 * STEP 7 : now we can check-in the ELECTRIC50 car !!
		 */
		thirdSlot = parking.checkin(electricCar);
		assertThat(thirdSlot.isPresent()).isTrue();
		assertThat(thirdSlot.get().getCar()).isEqualTo(electricCar);
		assertThat(electricCar.getCheckinDate()).isNotNull();
		assertThat(parking.selectAllAvailableSlots(CarTypeEnum.ELECTRIC50)).isEqualTo(0);

		/*
		 * STEP 8 : check-out the ELECTRIC50 car
		 */
		electricCar.setCheckinDate(electricCar.getCheckinDate().minusHours(3));
		price = parking.checkout(electricCar);
		assertThat(electricCar.getCheckoutDate()).isNotNull();
		assertThat(price).isEqualTo(9d);
		assertThat(parking.selectAllAvailableSlots(CarTypeEnum.ELECTRIC50)).isEqualTo(1);
	}

}