package es.ubiqua.compareme.exceptions;

public class CustomerException extends Exception{

	private static final long serialVersionUID = 1L;

	public CustomerException(){
		
	}
	
	@Override
	public String getMessage(){
		return "OtaException error";
	}
}
