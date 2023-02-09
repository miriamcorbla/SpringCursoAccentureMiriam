package es.rf.tienda.interfaces.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.interfaces.daos.ICategoria;

public class ServiceCategoria implements IServiceCategoria{
	
	@Autowired
	ICategoria cDAO;

	@Override
	public void insert(Categoria categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String idCategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Categoria categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria list(String idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Categoria> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
