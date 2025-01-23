package bhooking.exceptions;

public class PaymentException extends Exception {

	private static final long serialVersionUID = 1L;
	
   public PaymentException(String message) {
	   super(message);
   }
   
   @Override 
   public String getMessage() {
	   return "PAYMENT ERROR :"+super.getMessage();
   }
   
   
}
