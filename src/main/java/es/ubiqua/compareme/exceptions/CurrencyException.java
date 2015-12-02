package es.ubiqua.compareme.exceptions;

public class CurrencyException extends Exception{

	private static final long serialVersionUID = 1L;

	public CurrencyException(){
		
	}
	
	@Override
	public String getMessage(){
		return "CurrencyException error";
	}
}
