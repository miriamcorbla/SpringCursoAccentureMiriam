package es.rf.tienda.exception;

import org.springframework.http.HttpStatus;

public class ControllerException extends Exception{
	
	public ControllerException(String mensaje){
		super(mensaje);
	}	
	
	public ControllerException(){
	}
}
