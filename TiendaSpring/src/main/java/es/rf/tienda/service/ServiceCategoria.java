package es.rf.tienda.service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.ICategoria;
import es.rf.tienda.util.ErrorMessages;

@Service
public class ServiceCategoria implements IServiceCategoria{
	
	@Autowired
	private ICategoria cDAO;

	@Override
	public void insert(Categoria categoria) throws DomainException, DAOException{
		try {
			if(categoria.isValidInsert()) {
				cDAO.save(categoria);
			}
		}catch(DomainException d) {
			throw d;
		}catch(IllegalArgumentException ia) {
			throw new DAOException(ErrorMessages.ERR_ARG_CAT);
		}
	}

	@Override
	public void delete(int idCategoria) throws DAOException{
		try {
			cDAO.deleteById(idCategoria);
		}catch(EmptyResultDataAccessException ea) {
			throw new DAOException(ErrorMessages.ERR_ARG_CAT_ID);
		}
	}

	@Override
	public void update(Categoria categoria) throws DomainException, DAOException {
		try {
			Categoria c = cDAO.findById(categoria.getId_categoria()).get();
			if(categoria.getCat_nombre() == null) {
				categoria.setCat_nombre(c.getCat_nombre());
			}
			if(categoria.getCat_descripcion() == null) {
				categoria.setCat_descripcion(c.getCat_descripcion());
			}
			if((categoria.getId_categoria() == c.getId_categoria()) 
					&& categoria.isValidUpdate()) {
				
				cDAO.save(categoria);
			}else {
				throw new DomainException(ErrorMessages.ERR_CAT_NOEX);
			}
		}catch(DomainException d) {
			throw d;
		}catch(IllegalArgumentException ia) {
			throw new DAOException(ErrorMessages.ERR_ARG_CAT);
		}catch(NoSuchElementException e) {
			throw new DAOException(ErrorMessages.ERR_ARG_CAT_ID);
		}
	}


	@Override
	public Categoria list(int idCategoria) throws DAOException, DomainException {
		try {
			Categoria c = cDAO.findById(idCategoria).get();
			if(c != null) {
				return c;
			}else {
				throw new DomainException(ErrorMessages.ERR_CAT_NOEX);
			}
		}catch(IllegalArgumentException ia) {
			throw new DAOException(ErrorMessages.ERR_ARG_CAT);
		}catch(EmptyResultDataAccessException ea) {
			throw new DAOException(ErrorMessages.ERR_ARG_CAT_ID);
		}catch(NoSuchElementException e) {
			throw new DAOException(ErrorMessages.ERR_ARG_CAT_ID);
		}
	}

	@Override
	public ArrayList<Categoria> listAll() {
		return (ArrayList<Categoria>) cDAO.findAll();
	}

}
