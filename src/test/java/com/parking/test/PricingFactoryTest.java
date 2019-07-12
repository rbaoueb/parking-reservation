package com.parking.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.parking.ridha.service.ExtraAmountPolicyImpl;
import com.parking.ridha.service.HourPolicyImpl;
import com.parking.ridha.service.PricingPolicy;

public class PricingFactoryTest {

	@Test
	public void hourPolicyTest() {
		PricingPolicy policy = PricingPolicy.instance.initPricingPolicy(3);
		assertThat(policy).isNotNull();
		assertThat(policy.getClass()).isEqualTo(HourPolicyImpl.class);
		assertThat(((HourPolicyImpl) policy).getHourAmount()).isEqualTo(3);
	}

	@Test
	public void extraAmountPolicyTest() {
		PricingPolicy policy = PricingPolicy.instance.initPricingPolicy(3, 5);
		assertThat(policy).isNotNull();
		assertThat(policy.getClass()).isEqualTo(ExtraAmountPolicyImpl.class);
		assertThat(((ExtraAmountPolicyImpl) policy).getHourAmount()).isEqualTo(3);
		assertThat(((ExtraAmountPolicyImpl) policy).getExtraAmount()).isEqualTo(5);
	}
}