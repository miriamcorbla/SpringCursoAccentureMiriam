package es.rf.tienda.exception;

@SuppressWarnings("serial")
public class DomainException extends Exception {
	
	public DomainException(String mensaje){
		super("DomainException. " + mensaje);
	}	
	
	public DomainException(){
	}	

}
