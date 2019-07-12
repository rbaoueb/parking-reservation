package com.parking.ridha.service;

import com.parking.ridha.exception.ParkingException;
import com.parking.ridha.model.Car;

/**
 * PricingPolicy.java an interface used through Factory design pattern to be
 * used when we want to initialize a concrete pricing policy
 * ({@link ExtraAmountPolicyImpl} or {@link HourPolicyImpl})
 * 
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public interface PricingPolicy {

	/**
	 * and instance of {@link PricingPolicyFactory} to be used when we want to
	 * initialize the pricing policy
	 */
	PricingPolicyFactory instance = new PricingPolicyFactory();

	/**
	 * compute price method, should be used when we check-out the {@link Car}
	 * (should throw a {@link ParkingException} when we have incoherent
	 * check-in/check-out dates)
	 * 
	 * @param {@link
	 * 			Car} instance
	 * @return a double data type
	 */
	public double computePrice(Car car);

}
