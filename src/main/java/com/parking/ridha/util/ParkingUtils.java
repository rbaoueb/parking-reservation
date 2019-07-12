package com.parking.ridha.util;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.parking.ridha.model.Car;
import com.parking.ridha.model.ParkingSlot;

/**
 * ParkingUtils.java an utility class used to manager the parking reservation
 * processes
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class ParkingUtils {
	/**
	 * a {@link Random} instance (initialized from current time) to be used to
	 * generate unique IDs
	 */
	private static final Random random = new Random(System.currentTimeMillis());

	/**
	 * this method generates a random integer
	 * 
	 * @return a long data type.
	 */
	public static int generayteId() {
		return Math.abs(random.nextInt());
	}

	/**
	 * this method returns a first available element of the slotList variable.
	 * it returns Optional.empty() when no slot available
	 * 
	 * @param slotList
	 *            a variable of type {@link List}
	 * @return an {@link Optional<ParkingSlot>} data type.
	 */
	public static final Optional<ParkingSlot> getFirstAvailableSlot(List<ParkingSlot> slotList) {
		if (slotList == null) {
			return Optional.empty();
		}
		return slotList.stream().filter(ParkingSlot::isAvailable).findFirst();

	}

	/**
	 * this method returns a first element of the slotList variable that
	 * contains a given {@link Car}
	 * 
	 * @param slotList
	 *            a variable of type {@link List}
	 * @param car
	 *            a variable of type {@link Car}
	 * @return an {@link Optional<ParkingSlot>} data type.
	 */
	public static final Optional<ParkingSlot> getSlotByCar(List<ParkingSlot> slotList, Car car) {
		if (slotList == null || car == null) {
			return Optional.empty();
		}
		return slotList.stream().filter(s -> s.getCar().getMatricule().equals(car.getMatricule())).findFirst();

	}

	/**
	 * this method counts the number of available slots
	 * 
	 * @param slotList
	 *            a variable of type {@link List}
	 * 
	 * @return a long data type.
	 */
	public static final long selectAvailableSlots(List<ParkingSlot> slotList) {
		if (slotList == null) {
			return 0;
		}
		return slotList.stream().filter(ParkingSlot::isAvailable).count();

	}
}
