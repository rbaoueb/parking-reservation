package com.parking.ridha.service;

import java.time.Duration;

import com.parking.ridha.model.Car;

/**
 * HourPolicyImpl.java a class used through Factory design pattern to be used
 * when we want to initialize (hour price * nb hours) policy
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class HourPolicyImpl implements PricingPolicy {

	/* ================================ */
	/* ====== INSTANCE VARIABLES ====== */
	/* ================================ */
	private final double hourAmount;

	/**
	 * Class constructor according to hourAmount information
	 * 
	 * @param hourAmount
	 *            a variable of type double
	 */
	public HourPolicyImpl(double hourAmount) {
		this.hourAmount = hourAmount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.parking.ridha.service.PricingPolicy#computePrice(com.parking.ridha.
	 * model.Car)
	 */
	@Override
	public double computePrice(Car car) {
		car.checkDatesAfterCheckout();
		long spentHours = Duration.between(car.getCheckinDate(), car.getCheckoutDate()).toHours();
		return this.hourAmount * spentHours;
	}

	/* ================================ */
	/* ============ GETTERS =========== */
	/* ================================ */

	/**
	 * Retrieve the value of hourAmount
	 * 
	 * @return double data type
	 */
	public double getHourAmount() {
		return hourAmount;
	}

}
