package com.parking.ridha.model;

import com.parking.ridha.exception.ParkingException;
import com.parking.ridha.service.PricingPolicy;

/**
 * ParkingBuilder.java a class represent a builder pattern to build
 * {@link Parking} entity
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class ParkingBuilder {

	/* ================================ */
	/* ====== INSTANCE VARIABLES ====== */
	/* ================================ */
	private final Parking parking;

	/**
	 * Class constructor to instantiate {@link Parking}
	 */
	public ParkingBuilder() {
		this.parking = new Parking();
	}

	/**
	 * @param carType
	 * @param nbSlots
	 * @return
	 */
	public ParkingBuilder addSlots(CarTypeEnum carType, int nbSlots) {
		this.parking.initSlots(carType, nbSlots);
		return this;
	}

	/**
	 * Set the value of parking policy
	 * 
	 * @param policy
	 *            a variable of type {@link PricingPolicy}
	 * @return the current {@link ParkingBuilder} instance
	 */
	public ParkingBuilder setPolicy(PricingPolicy policy) {
		this.parking.setPolicy(policy);
		return this;
	}

	/**
	 * this method return the {@link Parking} object through its builder, it
	 * throws {@link ParkingException} when there is no initialization of policy or nbSlot
	 * 
	 * @return an initialized instance of {@link Parking}
	 */
	public Parking build() {

		this.parking.getPolicy().orElseThrow(() -> new ParkingException("you should initialize a pricing policy"));

		if (this.parking.getNbSlot().isEmpty()) {
			throw new ParkingException("you should initialize the number of slots");
		}
		return this.parking;
	}

}
