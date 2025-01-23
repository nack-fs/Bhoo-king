package bhooking.exceptions;

public class ParseException extends Exception {

	private static final long serialVersionUID = 1L;
	
   public ParseException(String message) {
	   super(message);
   }
   
   @Override 
   public String getMessage() {
	   return "PARSE ERROR :"+super.getMessage();
   }
   
   
}
