package es.rf.tienda.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.ICategoria;

/**
 * Clase para realizar las pruebas del servicio de categoría
 * @author m.corchero.blazquez
 *
 */
@SpringBootTest
class ServiceCategoriaTest {
	
	@InjectMocks
	ServiceCategoria serviceCat;

	@Mock
	ICategoria cDAO;
	
	private final int LONG_MIN = 5;
	private final int LONG_MAX = 50;
	
	
	/**
	 * Test para comprobar que se inserta una categoria correctamente
	 * @throws DomainException
	 * @throws DAOException
	 */
	@Test
	void testInsert() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");

		serviceCat.insert(c1);
		verify(cDAO, times(1)).save(c1);
	}
	/**
	 * Test para comprobar que se lanza una excepción domain
	 * cuando inserta una categoría no válida por tener un campo
	 * nulo
	 * @throws DomainException
	 * @throws DAOException
	 */
	@Test
	void testInsertErrorDomain() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		
		assertThrows(DomainException.class,
	            () -> { serviceCat.insert(c1);});
	}
	/**
	 * Test para comprobar que elimina correctamente
	 * @throws DAOException
	 * @throws DomainException
	 */
	@Test
	void testDelete() throws DAOException, DomainException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		
		serviceCat.delete(1);
		
		verify(cDAO, times(1)).deleteById(1);
	}
	
	/**
	 * Test para comprobar que se lanza una excepción domain
	 * cuando inserta una categoría no válida por tener un campo
	 * nulo
	 * @throws DomainException
	 * @throws DAOException
	 */
	@Test
	void testDeleteErrorDomain() throws DomainException, DAOException {
		//doThrow(new DAOException(ErrorMessages.ERR_ARG_CAT_ID)).when(cDAO).deleteById(0);
		assertThrows(DAOException.class,
	            () -> { serviceCat.delete(0);});
	}

	/*
	@Test
	void testUpdate() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");

		serviceCat.insert(c1);
		
		serviceCat.update(c1);
		
		verify(cDAO, times(1)).save(c1);

		
	}*/

	/**
	 * Test para probar que lista por Id
	 * @throws DomainException
	 * @throws DAOException
	 */
	@Test
	void testList() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		
		when(cDAO.findById(1)).thenReturn(Optional.of(c1));

		Categoria cat = serviceCat.list(1);

		assertEquals(c1.getCat_descripcion(), cat.getCat_descripcion());
		assertEquals(c1.getCat_nombre(), cat.getCat_nombre());
	}
	

	/**
	 * Test para comprobar que lista todas las categorias
	 * @throws DomainException
	 */
	@Test
	void testListAll() throws DomainException {
		
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		categorias.add(c1);
		Categoria c2 = new Categoria();
		c2.setCat_descripcion("Prueba test 2");
		c2.setCat_nombre("Prueba 2");
		categorias.add(c2);

		when(cDAO.findAll()).thenReturn(categorias);

		ArrayList<Categoria> categoriasServicio = serviceCat.listAll();

		assertThat(categoriasServicio).containsExactly(c1, c2);
	}

}
