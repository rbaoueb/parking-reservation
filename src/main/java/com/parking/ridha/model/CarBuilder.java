package com.parking.ridha.model;

import com.parking.ridha.exception.ParkingException;

/**
 * Car.java a class represent a builder pattern to build {@link Car} entity
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class CarBuilder {

	/* ================================ */
	/* ====== INSTANCE VARIABLES ====== */
	/* ================================ */
	private String matricule;

	private CarTypeEnum type;

	/**
	 * Set the value of car matricule
	 * 
	 * @param matricule
	 *            a variable of type String
	 * @return a new {@link CarBuilder} instance
	 */
	public CarBuilder setMatricule(String matricule) {
		this.matricule = matricule;
		return this;
	}

	/**
	 * Set the value of car type
	 * 
	 * @param type
	 *            a variable of type CarTypeEnum
	 * @return a new {@link CarBuilder} instance
	 */
	public CarBuilder setType(CarTypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * this method return the {@link Car} object through its builder, it throws
	 * exceptions when there is no initialization of matricule or type
	 * 
	 * @return a new {@link Car} instance
	 */
	public Car build() {
		if (this.matricule == null) {
			throw new ParkingException("you should initialize the matricule");
		}

		if (this.type == null) {
			throw new ParkingException("you should initialize the car type");
		}
		return new Car(matricule, type);
	}

}
