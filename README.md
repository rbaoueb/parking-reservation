# ParkReservation API

[![codecov](https://codecov.io/gh/rbaoueb/parking-reservation/branch/master/graph/badge.svg)](https://codecov.io/gh/rbaoueb/parking-reservation)
[![Build Status](https://travis-ci.com/rbaoueb/parking-reservation.svg?branch=master)](https://travis-ci.com/rbaoueb/parking-reservation)
[![CodeFactor](https://www.codefactor.io/repository/github/rbaoueb/parking-reservation/badge)](https://www.codefactor.io/repository/github/rbaoueb/parking-reservation)
[![Maintainability](https://api.codeclimate.com/v1/badges/075bf9e6b0a6b1cc0e58/maintainability)](https://codeclimate.com/github/rbaoueb/parking-reservation/maintainability)


ParkReservation is a JAVA API which allows us check-in / check-out of Cars in a given Parking

## Report
The javadoc report of the API can be reachable through this [link](https://rbaoueb.github.io/parking-reservation/)


## Installation
you can import the source project on your IDE or build the jar and push it to your maven repository :

```bash
git clone https://github.com/rbaoueb/parking-reservation.git
cd parking-reservation
mvn clean install
```

then add the generated dependency to your project pom.xml:
```xml
<dependencies>
    <dependency>
      <groupId>com.mrbaoueb</groupId>
	  <artifactId>parking-reservation</artifactId>
      <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Usage

There is an example of API usage (creating new parking and check-in/check-out a car) : 
```java

	public static void main(String[] args) {

		 Parking parking = Parking.builder()
				.addSlots(CarTypeEnum.GASOLINE, 2)
				.setPolicy(PricingPolicy.instance.initPricingPolicy(1.6))
				.build();
		 
		 Car car = Car.builder()
				.setMatricule("XX-111-YY")
				.setType(CarTypeEnum.GASOLINE)
				.build();

		 parking.checkin(car);
		 car.setCheckinDate(car.getCheckinDate().minusHours(2));
		 double price = parking.checkout(car);
		 System.out.println(price);
	}
	
```

### Custom pricing policy
You can also implement your own pricing policy in the future by the following sample (for example, the first hour should be free):
```java
public class Main {

    public class FirstHoutFreePricingPolicy implements PricingPolicy {

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
	public FirstHoutFreePricingPolicy(double hourAmount) {
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
		spentHours = spentHours > 1 ? spentHours - 1 : 0;
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

	public static void main(String[] args) {

		 Parking parking = Parking.builder()
				.addSlots(CarTypeEnum.GASOLINE, 2)
				.setPolicy(PricingPolicy.instance.initPricingPolicy(1.6))
				.build();
		 
		 Car car = Car.builder()
				.setMatricule("XX-111-YY")
				.setType(CarTypeEnum.GASOLINE)
				.build();
				
		 parking.checkin(car);
		 car.setCheckinDate(car.getCheckinDate().minusHours(2));
		 double price = parking.checkout(car);
		 System.out.println(price);
	}
}