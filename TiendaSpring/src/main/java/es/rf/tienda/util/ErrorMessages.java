package es.rf.tienda.util;


/**
 *  *****************************************************
 * NOMBRE: ErrorMessages.java
 * 
 * DESCRIPCION:  
 * 			Clase con los String que contienen los mensajes de error 
 * 			especificados por las reglas de negocio.
 * 
 *  @version	Feb 2023
 *  @author m.corchero.blazquez
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
	
	/**
	 * ILlegalArgumentException
	 */
	public final static String ERR_ARG_CAT = "Categoría no válida";
	/**
	 * ILlegalArgumentException Id no válido
	 */
	public final static String ERR_ARG_CAT_ID = "ID de Categoría no válido. No existe";
	
	public final static String ERR_CAT_NOMBRE_NULO = "El campo nombre de la categoria no puede ser nulo ";
	public final static String ERR_CAT_NOEX = "La categoría no existe";
}
