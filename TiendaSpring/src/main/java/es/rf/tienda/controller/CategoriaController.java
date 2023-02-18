package es.rf.tienda.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.ControllerException;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.service.IServiceCategoria;

@RestController
@CrossOrigin
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private IServiceCategoria serviceCat;

	@GetMapping
	public ResponseEntity<Map<String, Object>> listarCategorias() throws ControllerException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		ArrayList<Categoria> categorias = serviceCat.listAll();
		if(!categorias.isEmpty()) {
			map.put("codigo", 200);
			map.put("descripcion", "Registros encontrados");
			map.put("datos", categorias);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}else {
			map.put("codigo", 404);
			map.put("descripcion", "No hay registros en la Base de Datos");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	/*
	@GetMapping("/{id}")
	public Mensaje leerCategoriaId(@PathVariable("id") int id) throws DAOException, DomainException, ControllerException {
		try {
			Mensaje msj = new Mensaje(200, "Registro encontrado");
			msj.setCategoria(serviceCat.list(id));
			return msj;
		} catch (DomainException d) {
			throw new ControllerException(d.getMessage());
		} catch (DAOException e) {
			throw new ControllerException(e.getMessage());
		}
	}*/
	@GetMapping("/{id}")
	public ResponseEntity<Mensaje> leerCategoriaId(@PathVariable("id") int id) throws ControllerException {
		try {
			Mensaje msj = new Mensaje(200, "Registro encontrado");
			msj.setCategoria(serviceCat.list(id));
			return new ResponseEntity<Mensaje>(msj, HttpStatus.OK);
		} catch (DomainException d) {
			//Mensaje msj = new Mensaje(400, d.getMessage());
			//return new ResponseEntity<Mensaje>(msj,  HttpStatus.BAD_REQUEST);
			throw new ControllerException(d.getMessage());
		} catch (DAOException e) {
			//Mensaje msj = new Mensaje(500, e.getMessage());
			//return new ResponseEntity<Mensaje>(msj, HttpStatus.INTERNAL_SERVER_ERROR);
			throw new ControllerException(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String[]> eliminarCategoriaId(@PathVariable("id") int id) throws ControllerException {
		try {
			serviceCat.delete(id);
			return new ResponseEntity<String[]>(new String[]{ "200", "El registro se ha eliminado en la base de datos" }, HttpStatus.OK);
		} catch (DomainException d) {
			throw new ControllerException(d.getMessage());
			//return new ResponseEntity<String[]>(new String[]{"400", d.getMessage()},  HttpStatus.BAD_REQUEST);
		} catch (DAOException e) {
			throw new ControllerException(e.getMessage());
			//return new ResponseEntity<String[]>(new String[]{ "500", e.getMessage() }, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String[]> insertar(@RequestBody Categoria cat) throws ControllerException {
		try {
			cat.setId_categoria(0);
			serviceCat.insert(cat);
			return new ResponseEntity<String[]>(new String[]{ "201", "El registro se ha guardado en la base de datos" }, HttpStatus.CREATED);
		} catch (DomainException d) {
			throw new ControllerException(d.getMessage());
			//return new ResponseEntity<String[]>(new String[]{"400", d.getMessage()},  HttpStatus.BAD_REQUEST);
		} catch (DAOException e) {
			throw new ControllerException(e.getMessage());
			//return new ResponseEntity<String[]>(new String[]{ "500", e.getMessage() }, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping
	public ResponseEntity<String[]> modificarCategoria(@RequestBody Categoria cat) throws ControllerException { // OJO que si no existe, añade uno!!!
		try {
			serviceCat.update(cat);
			return new ResponseEntity<String[]>(new String[]{ "200", "El registro se ha guardado en la base de datos" }, HttpStatus.OK);
		} catch (DomainException d) {
			throw new ControllerException(d.getMessage());
			//return new ResponseEntity<String[]>(new String[]{"400", d.getMessage()},  HttpStatus.BAD_REQUEST);
		} catch (DAOException e) {
			throw new ControllerException(e.getMessage());
			//return new ResponseEntity<String[]>(new String[]{ "500", e.getMessage() }, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public class Mensaje {
		private int codigo;
		private String descripcion;
		private Categoria categoria;

		public Mensaje(int codigo, String descripcion) {
			this.codigo = codigo;
			this.descripcion = descripcion;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int numError) {
			this.codigo = numError;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion_error) {
			this.descripcion = descripcion_error;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
	}
}
