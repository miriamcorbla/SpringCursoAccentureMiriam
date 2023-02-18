package es.rf.tienda.exception;

/**
 * POJO Mensaje para que cliente obtenga
 * más información del error. Cda vez que se produzca un error
 * se va a montar el mensaje con el error y se le va a mostrar al cliente
 * @author m.corchero.blazquez
 *
 */
public class MensajeError {
	private String excepcion;
	private String mensaje;
	
	public MensajeError(Exception excepcion) {
		this.excepcion = excepcion.getClass().getSimpleName();
		this.mensaje = excepcion.getMessage();
	}
	public String getExcepcion() {
		return excepcion;
	}
	public String getMensaje() {
		return mensaje;
	}
	
	@Override
	public String toString() {
		return "Mensaje [excepcion=" + excepcion + ", mensaje=" + mensaje + "]";
	}
	
	



}