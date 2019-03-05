package es.ubiqua.compareme.exceptions;

public class ExpediaServiceException extends Exception{
	
	private static final long serialVersionUID = -3839085115152809793L;
	private String error = "";
	
	public ExpediaServiceException(String error){
		this.error = error;
	}
	
	@Override
	public String getMessage(){
		return error;
	}
}
