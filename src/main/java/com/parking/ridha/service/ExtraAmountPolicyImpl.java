package com.parking.ridha.service;

import java.time.Duration;

import com.parking.ridha.model.Car;

/**
 * ExtraAmountPolicyImpl.java a class used through Factory design pattern to be
 * used when we want to initialize (fixed amount + hour price * nb hours) policy
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class ExtraAmountPolicyImpl implements PricingPolicy {

	/* ================================ */
	/* ====== INSTANCE VARIABLES ====== */
	/* ================================ */
	private final double hourAmount;

	private final double extraAmount;

	/**
	 * Class constructor according to hourAmount and extraAmount information
	 * 
	 * @param hourAmount
	 *            a variable of type double
	 * @param extraAmount
	 *            a variable of type double
	 */
	public ExtraAmountPolicyImpl(double hourAmount, double extraAmount) {
		this.hourAmount = hourAmount;
		this.extraAmount = extraAmount;
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
		return this.extraAmount + (this.hourAmount * spentHours);
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

	/**
	 * Retrieve the value of extraAmount
	 * 
	 * @return double data type
	 */
	public double getExtraAmount() {
		return extraAmount;
	}

}
