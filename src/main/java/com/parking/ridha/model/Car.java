package com.parking.ridha.model;

import java.time.LocalDateTime;

import com.parking.ridha.exception.ParkingException;

/**
 * Car.java a class represent Car entity
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class Car {

	/* ================================ */
	/* ====== INSTANCE VARIABLES ====== */
	/* ================================ */
	private final String matricule;

	private final CarTypeEnum type;

	private LocalDateTime checkinDate;

	private LocalDateTime checkoutDate;

	/**
	 * Class constructor specifying matricule and type
	 * 
	 * @param matricule
	 * @param type
	 */
	public Car(String matricule, CarTypeEnum type) {
		super();
		this.matricule = matricule;
		this.type = type;
	}

	/**
	 * return a builder object
	 * 
	 * @return a new {@link CarBuilder} instance
	 */
	public static CarBuilder builder() {
		return new CarBuilder();
	}

	/**
	 * this method is used to check the coherence of check-in/check-out dates of
	 * the car before computing the price
	 */
	public void checkDatesAfterCheckout() {
		if (this.getCheckinDate() == null) {
			throw new ParkingException("the car checkin date is not set");
		}
		if (this.getCheckoutDate() == null) {
			throw new ParkingException("the car checkout date is not set");
		}
		if (this.getCheckinDate().isAfter(this.getCheckoutDate())) {
			throw new ParkingException("the checkin/checkout dates are not coherent");
		}
	}

	/* ================================ */
	/* ====== GETTERS AND SETTERS ===== */
	/* ================================ */

	/**
	 * Retrieve the value of checkinDate
	 * 
	 * @return LocalDateTime data type
	 */
	public LocalDateTime getCheckinDate() {
		return checkinDate;
	}

	/**
	 * Set the value of checkinDate
	 * 
	 * @param checkinDate
	 *            a variable of type LocalDateTime
	 */
	public void setCheckinDate(LocalDateTime checkinDate) {
		this.checkinDate = checkinDate;
	}

	/**
	 * Retrieve the value of checkoutDate
	 * 
	 * @return LocalDateTime data type
	 */
	public LocalDateTime getCheckoutDate() {
		return checkoutDate;
	}

	/**
	 * Set the value of checkoutDate
	 * 
	 * @param checkoutDate
	 *            a variable of type LocalDateTime
	 */
	public void setCheckoutDate(LocalDateTime checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	/**
	 * Retrieve the value of car type
	 * 
	 * @return CarTypeEnum data type
	 */
	public CarTypeEnum getType() {
		return type;
	}

	/**
	 * Retrieve the value of car matricule
	 * 
	 * @return String data type
	 */
	public String getMatricule() {
		return matricule;
	}

}
