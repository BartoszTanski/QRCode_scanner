package com.bartosztanski.qrcodescanner.error;

public class ImageScanFailureException extends Exception{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1592406962965360702L;

		public ImageScanFailureException() {
			super();
		}
		
		public ImageScanFailureException(String message) {
			super(message);
		}
		
		public ImageScanFailureException(String message, Throwable cause) {
			super(message, cause);
		}
		
		public ImageScanFailureException(Throwable cause) {
			super(cause);
		}
		
		public ImageScanFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
}
