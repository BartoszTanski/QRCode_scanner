package com.bartosztanski.qrcodescanner.error;

public class ArgumentNotValidException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -653567819979668891L;

	public ArgumentNotValidException() {
		super();
	}
	
	public ArgumentNotValidException(String message) {
		super(message);
	}
	
	public ArgumentNotValidException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ArgumentNotValidException(Throwable cause) {
		super(cause);
	}
	
	public ArgumentNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}