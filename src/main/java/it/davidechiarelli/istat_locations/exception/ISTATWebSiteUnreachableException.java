package it.davidechiarelli.istat_locations.exception;

/**
 * This exception is launched when ISTAT web site is unreachablw
 * 
 * @author Davide Chiarelli
 * @version 1.0.0
 *
 */
public class ISTATWebSiteUnreachableException extends RuntimeException{
	private static final long serialVersionUID = 714364745663430261L;
	
	public ISTATWebSiteUnreachableException() {
		super("ISTAT web site is unreachable.");
	}

}
