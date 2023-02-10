package es.rf.tienda.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.service.ServiceCategoria;

/**
 * Tests unitarios que prueban el controlador de categoria
 * POR TERMINAR
 * @author m.corchero.blazquez
 *
 */

@SpringBootTest
class CategoriaControllerTest {

	@InjectMocks
	CategoriaController cat;
	
	@Mock
	ServiceCategoria serviceCat;
	
	private MockMvc mockmvc;
	
	@Test
	void testListarCategorias() throws Exception {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		categorias.add(c1);
		Categoria c2 = new Categoria();
		c2.setCat_descripcion("Prueba test 2");
		c2.setCat_nombre("Prueba 2");
		categorias.add(c2);
		
		when(serviceCat.listAll()).thenReturn(categorias);

		
		assertEquals(categorias.size(), 2);
		assertEquals(categorias.get(0).getCat_nombre(), c1.getCat_nombre());
		assertEquals(categorias.get(1).getCat_nombre(), c2.getCat_nombre());
		
		assertEquals(categorias.get(0).getCat_descripcion(), c1.getCat_descripcion());
		assertEquals(categorias.get(1).getCat_descripcion(), c2.getCat_descripcion());
	}

	@Test
	void testLeerCategoriaId() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setId_categoria(0);
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		
		when(serviceCat.list(0)).thenReturn(c1);
		
		assertEquals("Prueba", c1.getCat_nombre());
		
		assertEquals("Prueba test", c1.getCat_descripcion());
		
	}

	@Test
	void testEliminarCategoriaId() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertar() {
		fail("Not yet implemented");
	}

	@Test
	void testModificarCategoria() {
		fail("Not yet implemented");
	}

}
