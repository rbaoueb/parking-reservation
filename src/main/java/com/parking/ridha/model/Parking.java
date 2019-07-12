package com.parking.ridha.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.parking.ridha.exception.ParkingException;
import com.parking.ridha.service.PricingPolicy;
import com.parking.ridha.util.ParkingUtils;

/**
 * Parking.java a class represent Parking entity and its possible operations
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class Parking {

	private static final Logger LOGGER = LoggerFactory.getLogger(Parking.class);

	/* ================================ */
	/* ====== INSTANCE VARIABLES ====== */
	/* ================================ */
	private Map<CarTypeEnum, List<ParkingSlot>> nbSlot;

	private PricingPolicy policy;

	/**
	 * return a builder object
	 * 
	 * @return a new {@link ParkingBuilder} instance
	 */
	public static ParkingBuilder builder() {
		return new ParkingBuilder();
	}

	/**
	 * Set the value of parking nbSlot
	 * 
	 * @param type
	 *            a variable of type {@link CarTypeEnum}
	 * @param nbSlots
	 *            type a variable of long
	 */
	public void initSlots(CarTypeEnum type, long nbSlots) {
		if (nbSlots < 0) {
			throw new ParkingException("invalid slots number");
		}

		List<ParkingSlot> slots = Stream.generate(ParkingSlot::new).limit(nbSlots).collect(Collectors.toList());
		this.getNbSlot().put(type, slots);
	}

	/**
	 * check-in a new car in the parking
	 * 
	 * @param {@link
	 * 			Car} instance
	 * @return a {@link Optional<ParkingSlot>} instance
	 */
	public Optional<ParkingSlot> checkin(Car car) {
		Optional<ParkingSlot> slot = ParkingUtils.getFirstAvailableSlot(this.getNbSlot().get(car.getType()));
		slot.ifPresent(s -> {
			car.setCheckinDate(LocalDateTime.now());
			s.setAvailable(false);
			s.setCar(car);
			LOGGER.info("car <{}> checked in on slot number <{}>", car.getMatricule(), s.getNumber());
		});
		return slot;
	}

	/**
	 * check-out an existing car from the parking
	 * 
	 * @param {@link
	 * 			Car} instance
	 * @return a {@link Double} primitive data type
	 */
	public double checkout(Car car) {
		Optional<ParkingSlot> slot = ParkingUtils.getSlotByCar(this.getNbSlot().get(car.getType()), car);
		slot.ifPresent(s -> {
			car.setCheckoutDate(LocalDateTime.now());
			s.setAvailable(true);
			s.setCar(null);
		});

		return this.policy.computePrice(car);
	}

	/**
	 * returns the number of available slots according to the car type
	 * 
	 * @param {@link
	 * 			CarTypeEnum} data type
	 * @return a long data type
	 */
	public long selectAllAvailableSlots(CarTypeEnum type) {
		return ParkingUtils.selectAvailableSlots(this.getNbSlot().get(type));
	}

	/* ================================ */
	/* ====== GETTERS AND SETTERS ===== */
	/* ================================ */

	/**
	 * retrieve an optional of the policy attribute
	 * 
	 * @return an {@link Optional<PricingPolicy>} data type
	 */
	public Optional<PricingPolicy> getPolicy() {
		return Optional.ofNullable(policy);
	}

	/**
	 * set the value of policy
	 * 
	 * @param policy
	 *            a variable of type {@link PricingPolicy}
	 */
	public void setPolicy(PricingPolicy policy) {
		this.policy = policy;
	}

	/**
	 * retrieve the value of nbSlot
	 * 
	 * @return A {@link Map} data type
	 */
	public Map<CarTypeEnum, List<ParkingSlot>> getNbSlot() {
		if (this.nbSlot == null) {
			this.setNbSlot(new HashMap<>());
		}
		return nbSlot;
	}

	/**
	 * set the value of nbSlot
	 * 
	 * @param nbSlot
	 *            a variable of type {@link Map}
	 */
	public void setNbSlot(Map<CarTypeEnum, List<ParkingSlot>> nbSlot) {
		this.nbSlot = nbSlot;
	}

}
