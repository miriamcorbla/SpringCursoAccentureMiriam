package es.rf.tienda.util;


/* *****************************************************
 * NOMBRE: ErrorMessages.java
 * 
 * DESCRIPCION:  
 * 			Clase con los String que contienen los mensajes de error 
 * 			especificados por las reglas de negocio.
 * 
 *  @version	Enero 2016
 *  
 *  @author 	Miguel Garcia
 *  
 *  *****************************************************/
public class ErrorMessages {
	
	
		
	/**
	 * Codigo de producto
	 */
	public final static String PROERR_COD_PROD_FORMATO = "Formato codigo erroneo";
	public final static String PROERR_COD_PROD_LONGITUD = "Longitud de codigo erroneo";
	
	/**
	 * Campo con longitud erronea
	 */
	public final static String PROERR_LONGITUD_MIN = "La longitud debe ser mayor que ";
	public final static String PROERR_LONGITUD_MAX = "La longitud debe ser menor que ";
	
	/**
	 * Rango erroneo
	 */
	public final static String PROERR_RANGO = "El rango ha de estar entre ? y ?";
	
	/**
	 * Fecha erronea
	 */
	public final static String PROERR_FECHA = "La fecha no es correcta. Ha de ser después de la fecha actual (hoy)";

	/**
	 * Correo electronico erroneo
	 */
	public final static String PROERR_MAIL = "Correo electrónico no válido";
	
}
