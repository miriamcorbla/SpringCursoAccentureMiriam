package es.rf.tienda.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.service.IServiceCategoria;
import es.rf.tienda.util.ErrorMessages;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private IServiceCategoria serviceCat;

	@GetMapping
	public ArrayList<Categoria> listarCategorias() {
		return serviceCat.listAll();
	}

	@GetMapping("/{id}")
	public Mensaje leerCategoriaId(@PathVariable("id") int id) throws DAOException, DomainException {
		try {
			Mensaje msj = new Mensaje(200, "Registro encontrado");
			msj.setCategoria(serviceCat.list(id));
			return msj;
		} catch (DomainException d) {
			return new Mensaje(400, d.getMessage());
		} catch (DAOException e) {
			return new Mensaje(500, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public String[] eliminarCategoriaId(@PathVariable("id") int id) {
		try {
			serviceCat.delete(id);
			return new String[] { "200", "El registro se ha eliminado en la base de datos" };
		} catch (DomainException d) {
			return new String[] { "400", d.getMessage() };
		} catch (DAOException e) {
			return new String[] { "500", e.getMessage() };
		}
	}

	@PostMapping
	public String[] insertar(@RequestBody Categoria cat) {
		try {
			cat.setId_categoria(0);
			serviceCat.insert(cat);
			return new String[] { "200", "El registro se ha guardado en la base de datos" };
		} catch (DomainException d) {
			return new String[] { "400", d.getMessage() };
		} catch (DAOException e) {
			return new String[] { "500", e.getMessage() };
		}
	}

	@PutMapping
	public String[] modificarCategoria(@RequestBody Categoria cat) { // OJO que si no existe, añade uno!!!
		try {
			serviceCat.update(cat);
			return new String[] { "200", "El registro se ha modificado en la base de datos" };
		} catch (DomainException d) {
			return new String[] { "400", d.getMessage() };
		} catch (DAOException e) {
			return new String[] { "500", e.getMessage() };
		}
	}

	public class Mensaje {
		private int codeNum;
		private String descripcionCode;
		private Categoria categoria;

		public Mensaje(int numError, String descripcion_error) {
			this.codeNum = numError;
			this.descripcionCode = descripcion_error;
		}

		public int getNumError() {
			return codeNum;
		}

		public void setNumError(int numError) {
			this.codeNum = numError;
		}

		public String getDescripcion_error() {
			return descripcionCode;
		}

		public void setDescripcion_error(String descripcion_error) {
			this.descripcionCode = descripcion_error;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
	}
}
