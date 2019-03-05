package es.ubiqua.compareme.exceptions;

public class ServiceException extends Exception{
	private static final long serialVersionUID = 4501185222920933106L;
	public static final String INVALID_ARGUMENTS = "Not enough arguments or are invalid!";
	public static final String INVALID_CRAWLER_URL = "Website is not defined or it's unreachable!";
}
