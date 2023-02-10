package es.rf.tienda.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.Validator;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * 
 * Nombre		Categoria
 * Descripcion	Lista de categorías
 * @author 		Miguel Garcia. Modificaciones: Miriam Corchero
 * @version		Febrero 2023
 *
 */
@Entity
@Table(schema = "ALUMNO_MCB", name = "Categorias")
public class Categoria implements ModeloValido{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id_categoria;			//identificador categoria AUTOINCREMENTAL
	
	@Column(nullable = false, length = 50)
	private String cat_nombre;			//nombre de la categoria
	
	@Column(nullable = true, length = 200) //es updatable este atributo?
	private String cat_descripcion;		//descripcion de la categoria
	
	@Transient
	@JsonIgnore
	private final int LONG_MIN = 5;
	@Transient
	@JsonIgnore
	private final int LONG_MAX = 50;
	@Transient
	@JsonIgnore
	private final int LONG_MAX_DESCR = 200;
	
	
	
	public boolean isValidInsert() throws DomainException{	
		if((!Validator.isVacio(cat_nombre) && Validator.cumpleLongitud(cat_nombre, LONG_MIN, LONG_MAX))){
			return true;
		}else{
			throw new DomainException(ErrorMessages.ERR_CAT_NOMBRE_NULO + " y " + ErrorMessages.PROERR_LONGITUD_MIN + LONG_MIN + ", " + 
					ErrorMessages.PROERR_LONGITUD_MAX + LONG_MAX);
		}
	}
	
	public boolean isValidUpdate() throws DomainException{	
		if(!Validator.isVacio(cat_nombre) && id_categoria != 0 
				&& Validator.cumpleLongitud(cat_nombre, LONG_MIN, LONG_MAX)){
			return true;
		}else {
			throw new DomainException(ErrorMessages.PROERR_LONGITUD_MIN + LONG_MIN + " " + 
					ErrorMessages.PROERR_LONGITUD_MAX + LONG_MAX);
		}
	}
	
	/**
	 * Getter para identificador de categoria
	 * @return Integer con el id de la categoria
	 */
	public int getId_categoria() {
		return id_categoria;
	}
	
	/**
	 * Setter para identificador de categoria
	 * @throws DomainException 
	 * 
	 */
	public void setId_categoria(int id_categoria){
		this.id_categoria = id_categoria;

	}
	
	/**
	 * Getter para el nombre de categoria
	 * @return cadena con el nombre de la categoria
	 */
	public String getCat_nombre() {
		return cat_nombre;
	}
	
	/**
	 * Setter para el nombre de categoria
	 * @throws DomainException 
	 * 
	 */
	public void setCat_nombre(String cat_nombre) throws DomainException {
		//if(!Validator.isVacio(cat_nombre) 
			//	&& Validator.cumpleLongitud(cat_nombre, LONG_MIN, LONG_MAX)) {
		this.cat_nombre = cat_nombre;
		//}else {
			//throw new DomainException(ErrorMessages.ERR_CAT_NOMBRE_NULO + " y " + ErrorMessages.PROERR_LONGITUD_MIN + LONG_MIN + ", " + 
				//	ErrorMessages.PROERR_LONGITUD_MAX + LONG_MAX);		
		//}
		
	}
	
	/**
	 * Getter para la descripcion de categoria
	 * @return cadena con la descripcion de la categoria
	 */
	public String getCat_descripcion() {
		return cat_descripcion;
	}
	
	/**
	 * setter para la descripcion de categoria
	 * @throws DomainException 
	 * 
	 */
	public void setCat_descripcion(String cat_descripcion) throws DomainException {
		if(cat_descripcion == null) {
			this.cat_descripcion = cat_descripcion;
		}else {
			this.cat_descripcion = StringUtils.truncate(cat_descripcion, LONG_MAX_DESCR);
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat_descripcion == null) ? 0 : cat_descripcion.hashCode());
		result = prime * result + ((cat_nombre == null) ? 0 : cat_nombre.hashCode());
		result = prime * result + id_categoria;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (cat_descripcion == null) {
			if (other.cat_descripcion != null)
				return false;
		} else if (!cat_descripcion.equals(other.cat_descripcion))
			return false;
		if (cat_nombre == null) {
			if (other.cat_nombre != null)
				return false;
		} else if (!cat_nombre.equals(other.cat_nombre))
			return false;
		if (id_categoria != other.id_categoria)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Categoria [id_categoria=" + id_categoria + ", cat_nombre=" + cat_nombre + ", cat_descripcion="
				+ cat_descripcion + "]";
	}
	
	
}
