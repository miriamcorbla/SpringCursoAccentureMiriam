package es.rf.tienda.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;

@Service
public interface IServiceCategoria {
	public void insert(Categoria categoria) throws DomainException, DAOException;
	public void delete(int idCategoria) throws DomainException, DAOException;
	public void update(Categoria categoria) throws DomainException, DAOException;
	public Categoria list(int idCategoria) throws DAOException, DomainException;
	public ArrayList<Categoria> listAll();
}
