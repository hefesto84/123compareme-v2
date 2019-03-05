package es.ubiqua.compareme.exceptions;

public class OtaException extends Exception{

	private static final long serialVersionUID = 1L;

	public OtaException(){
		
	}
	
	@Override
	public String getMessage(){
		return "OtaException error";
	}
}
