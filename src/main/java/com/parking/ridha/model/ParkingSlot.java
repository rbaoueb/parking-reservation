package com.parking.ridha.model;

import com.parking.ridha.util.ParkingUtils;

/**
 * ParkingSlot.java a class represent ParkingSlot entity
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class ParkingSlot {

	/* ================================ */
	/* ====== INSTANCE VARIABLES ====== */
	/* ================================ */
	private long number;

	private Car car;

	private boolean available;

	public ParkingSlot() {
		this.setNumber(ParkingUtils.generayteId());
		this.setAvailable(true);
	}

	/* ================================ */
	/* ====== GETTERS AND SETTERS ===== */
	/* ================================ */

	/**
	 * Retrieve the value of the slot number
	 * 
	 * @return long data type
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Set the value of slot number
	 * 
	 * @param long
	 *            a variable of type long
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Retrieve the value of the associated {@link Car}
	 * 
	 * @return {@link Car} data type
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * Set the value of car
	 * 
	 * @param car
	 *            a variable of type {@link Car}
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * Retrieve the value available information
	 * 
	 * @return boolean data type
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Set the value of available
	 * 
	 * @param available
	 *            a variable of type boolean
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
