package es.rf.tienda.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.ICategoria;

@Service
public class ServiceCategoria implements IServiceCategoria{
	
	@Autowired
	private ICategoria cDAO;

	@Override
	public String[] insert(Categoria categoria) throws DomainException {
		if(categoria.isValidInsert()) {
			cDAO.save(categoria);
			return new String[] {"200", "Registro salvado"};
		}else {
			return new String[] {"500", "El registro no se ha podido guardar debido a que no es valido"};
		}		
	}

	@Override
	public String[] delete(int idCategoria) throws Exception {
		try {
			cDAO.deleteById(idCategoria);
			return new String[] {"200", "Registro eliminado"};
		}catch(Exception e) {
			throw new Exception("No se ha podido eliminar el registro");
		}		
	}

	@Override
	public String[] update(Categoria categoria) throws DomainException {
		if(categoria.isValidUpdate()) {
			cDAO.save(categoria);
			return new String[] {"200", "Registro salvado"};
		}else {
			return new String[] {"500", "El registro no se ha podido guardar debido a que no es valido"};
		}
	}


	@Override
	public Categoria list(int idCategoria) {
		return cDAO.findById(idCategoria).get();
	}

	@Override
	public ArrayList<Categoria> listAll() {
		return (ArrayList<Categoria>) cDAO.findAll();
	}

}
