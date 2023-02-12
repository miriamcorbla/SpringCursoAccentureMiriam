package es.rf.tienda.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import es.rf.tienda.controller.CategoriaController.Mensaje;
import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.service.ServiceCategoria;

/**
 * Tests unitarios que prueban el controlador de categoria POR TERMINAR
 * 
 * @author m.corchero.blazquez
 *
 */

@SpringBootTest
class CategoriaControllerTest {

	@InjectMocks
	CategoriaController cat;

	@Mock
	ServiceCategoria serviceCat;

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

		ArrayList<Categoria> categoriasControlador = cat.listarCategorias();

		assertThat(categoriasControlador).containsExactly(c1, c2);

	}

	@Test
	void testLeerCategoriaId() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");

		when(serviceCat.list(0)).thenReturn(c1);

		Mensaje categoriaControlador = cat.leerCategoriaId(0);

		assertEquals(categoriaControlador.getCategoria(), c1);
		assertEquals(categoriaControlador.getNumError(), 200);
		assertEquals(categoriaControlador.getDescripcion_error(), "Registro encontrado");

	}

	@Test
	void testEliminarCategoriaId() throws DomainException, DAOException {
		
		doNothing().when(serviceCat).delete(0);
		assertThat(cat.eliminarCategoriaId(0)).containsExactly("200", "El registro se ha eliminado en la base de datos");

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
