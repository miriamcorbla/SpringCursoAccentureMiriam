package es.rf.tienda.interfaces.service;

import java.util.ArrayList;

import es.rf.tienda.dominio.Categoria;

public interface IServiceCategoria {
	public void insert(Categoria categoria);
	public void delete(String idCategoria);
	public void update(Categoria categoria);
	public Categoria list(String idCategoria);
	public ArrayList<Categoria> listAll();
}
