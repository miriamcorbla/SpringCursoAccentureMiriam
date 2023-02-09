package es.rf.tienda.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DomainException;

@Service
public interface IServiceCategoria {
	public String[] insert(Categoria categoria) throws DomainException;
	public String[] delete(int idCategoria) throws Exception;
	public String[] update(Categoria categoria) throws DomainException;
	public Categoria list(int idCategoria);
	public ArrayList<Categoria> listAll();
}
