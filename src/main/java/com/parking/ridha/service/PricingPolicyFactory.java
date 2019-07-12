package com.parking.ridha.service;

/**
 * PricingPolicyFactory.java a class used through Factory design pattern to be
 * used when we want to initialize whether {@link HourPolicyImpl} or
 * {@link ExtraAmountPolicyImpl}
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class PricingPolicyFactory {

	/**
	 * Instantiate {@link ExtraAmountPolicyImpl} object to user (hour price * nb
	 * hours) pricing policy
	 * 
	 * @param hourAmount
	 *            a variable of type double
	 * @return a {@link HourPolicyImpl} instance
	 */
	public PricingPolicy initPricingPolicy(double hourAmount) {
		return new HourPolicyImpl(hourAmount);
	}

	/**
	 * Instantiate {@link ExtraAmountPolicyImpl} object to user (fixed amount +
	 * hour price * nb hours) pricing policy
	 * 
	 * @param hourAmount
	 *            a variable of type double
	 * @param extraAmount
	 *            a variable of type double
	 * @return a {@link ExtraAmountPolicyImpl} instance
	 */
	public PricingPolicy initPricingPolicy(double hourAmount, double extraAmount) {
		return new ExtraAmountPolicyImpl(hourAmount, extraAmount);
	}
}
