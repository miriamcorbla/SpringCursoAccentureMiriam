package es.rf.tienda.interfaces.daos;

import es.rf.tienda.dominio.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoria extends JpaRepository<Categoria, Integer>{
}


