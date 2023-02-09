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
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.service.IServiceCategoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private IServiceCategoria serviceCat;
	
	@GetMapping
	public ArrayList<Categoria> listarCategorias(){
		return serviceCat.listAll();
	}
	
	@GetMapping("/{id}")
	public Categoria leerCategoriaId(@PathVariable("id") int id) {
		return serviceCat.list(id);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarCategoriaId(@PathVariable("id") int id) throws Exception {
		serviceCat.delete(id);
	}
	
	@PostMapping
	public String[] insertar(@RequestBody Categoria cat) throws DomainException{
		cat.setId_categoria(0);
		return serviceCat.insert(cat);
	}
	
	@PutMapping
	public String[] modificarCategoria(@RequestBody Categoria cat) throws DomainException {
		return serviceCat.update(cat);
	}	

}
