package es.rf.tienda.exception;

public class ControllerException extends Exception{
	
	public ControllerException(String mensaje){
		super(mensaje);
	}	
	
	public ControllerException(){
	}
}
