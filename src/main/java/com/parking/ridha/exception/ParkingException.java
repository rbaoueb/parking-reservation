package com.parking.ridha.exception;

/**
 * ParkingException.java a class to customize exceptions app
 * @author mrbaoueb
 * @version 1.0
 * @since 2019-07-11
 *
 */
public class ParkingException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor specifying error message
	 * 
	 * @param errorMessage
	 */
	public ParkingException(String errorMessage) {
		super(errorMessage);
	}
}
