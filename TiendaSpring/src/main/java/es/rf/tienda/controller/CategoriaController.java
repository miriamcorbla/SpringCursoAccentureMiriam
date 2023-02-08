package es.rf.tienda.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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
import es.rf.tienda.interfaces.daos.ICategoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
//	private es.rf.tienda.interfaces.daos.ICategoria cDAO;
//	
//	public void ICategoria(ICategoria cDAO) {
//		this.cDAO = cDAO;
//	}
	@Autowired
	private ICategoria cDAO;
	
	@GetMapping
	public ArrayList<Categoria> listarCategorias(){
		return (ArrayList<Categoria>) cDAO.findAll();
	}
	
	@GetMapping("/{id}")
	public Categoria leerCategoriaId(@PathVariable("id") int id) {
		return cDAO.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	public void eliminarCategoriaId(@PathVariable("id") int id) {
		cDAO.deleteById(id);
	}
	
	@DeleteMapping
	public void eliminarCategoria(Categoria cat) {
		eliminarCategoriaId(cat.getId_categoria());
	}
	
	@PostMapping
	public String[] insertar(@RequestBody Categoria cat){
		cat.setId_categoria(1);
		cDAO.save(cat);
		return new String[] {"200", "Salvado"};
	}
	
	@PutMapping
	public String[] modificarCategoria(@RequestBody Categoria cat) {
		cDAO.save(cat);
		return new String[] {"200", "Modificado"};
	}
	

}
