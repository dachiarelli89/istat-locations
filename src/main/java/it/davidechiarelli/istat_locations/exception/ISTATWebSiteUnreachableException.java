package it.davidechiarelli.istat_locations.exception;

public class ISTATWebSiteUnreachableException extends RuntimeException{
	private static final long serialVersionUID = 714364745663430261L;
	
	public ISTATWebSiteUnreachableException() {
		super("ISTAT web site is unreachable.");
	}

}
