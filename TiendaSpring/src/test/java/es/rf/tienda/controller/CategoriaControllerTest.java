package es.rf.tienda.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.rf.tienda.controller.CategoriaController.Mensaje;
import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.service.ServiceCategoria;
import es.rf.tienda.util.ErrorMessages;


/**
 * Tests unitarios que prueban el controlador de categoria
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

	private final int LONG_MIN = 5;
	private final int LONG_MAX = 50;

	/**
	 * Test para listar categorias
	 * @throws Exception
	 */
	/*
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
	}*/

	/**
	 * Tests para mostar una sola categoría a partir de su ID
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
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
*/
	/**
	 * Test para comprobar que arroja una excepción concreta
	 * al leer una categoria que no existe
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testLeerCategoriaIdErrorDomain() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		when(serviceCat.list(0)).thenThrow(new DomainException(ErrorMessages.ERR_CAT_NOEX));
		Mensaje categoriaControlador = cat.leerCategoriaId(0);
		assertEquals(categoriaControlador.getCategoria(), null);
		assertEquals(categoriaControlador.getNumError(), 400);
		assertEquals(categoriaControlador.getDescripcion_error(), ErrorMessages.ERR_CAT_NOEX);
	}
*/
	/**
	 * Test para eliminar una categoría a partir de su ID 
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testEliminarCategoriaId() throws DomainException, DAOException {
		doNothing().when(serviceCat).delete(0);
		assertThat(cat.eliminarCategoriaId(0)).containsExactly("200",
				"El registro se ha eliminado en la base de datos");
	}*/

	/**
	 * Test para comprobar que lanza excepción al eliminar una categoria
	 * con un id que no existe
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testEliminarCategoriaIdErrorDomain() throws DomainException, DAOException {
		doThrow(new DAOException(ErrorMessages.ERR_ARG_CAT_ID)).when(serviceCat).delete(0);
		assertThat(cat.eliminarCategoriaId(0)).containsExactly("500", ErrorMessages.ERR_ARG_CAT_ID);
	}*/

	/**
	 * Test para comprobar la inserción de una categoria
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testInsertar() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		doNothing().when(serviceCat).insert(c1);
		assertThat(cat.insertar(c1)).containsExactly("200", "El registro se ha guardado en la base de datos");
	}*/

	/**
	 * Test para comprobar que lanza excepción si insertamos una categoria
	 * que no cumple con los requisistos minimos de inserción: id y nombre no nulo
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testInsertarErrorDomainIsValid() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		doThrow(new DomainException(ErrorMessages.ERR_CAT_NOMBRE_NULO + " y " + ErrorMessages.PROERR_LONGITUD_MIN
				+ LONG_MIN + ", " + ErrorMessages.PROERR_LONGITUD_MAX + LONG_MAX)).when(serviceCat).insert(c1);
		assertThat(cat.insertar(c1)).containsExactly("400", (ErrorMessages.ERR_CAT_NOMBRE_NULO + " y "
				+ ErrorMessages.PROERR_LONGITUD_MIN + LONG_MIN + ", " + ErrorMessages.PROERR_LONGITUD_MAX + LONG_MAX));
	}*/
/*
	@Test
	void testInsertarErrorDAO() throws DomainException, DAOException {
		
		Categoria c1 = null;
		
		doThrow(new DAOException(ErrorMessages.ERR_ARG_CAT)).when(serviceCat).insert(c1);
		
		assertThat(cat.insertar(c1)).containsExactly("400", ErrorMessages.ERR_ARG_CAT);
	}*/

	/**
	 * Test para probar si se modifica correctamente una categoria
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testModificarCategoria() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setCat_descripcion("Prueba test");
		c1.setCat_nombre("Prueba");
		doNothing().when(serviceCat).update(c1);
		assertThat(cat.modificarCategoria(c1)).containsExactly("200",
				"El registro se ha modificado en la base de datos");
	}*/
	
	/**
	 * Test para comprobar que se lanza la excepción si la categoria no existe
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testModificarCategoriaErrorDomain() throws DomainException, DAOException {
		Categoria c1 = null;
		
		doThrow(new DomainException(ErrorMessages.ERR_CAT_NOEX)).when(serviceCat).update(c1);
		
		assertThat(cat.modificarCategoria(c1)).containsExactly("400", ErrorMessages.ERR_CAT_NOEX);
		
	}*/
	
	/**
	 * Test para comprobar que se lanza excepción si no se cumple con los requisitos
	 * minimos para almacenar una categoria
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testModificarCategoriaErrorDomainIsValid() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		
		doThrow(new DomainException(ErrorMessages.PROERR_LONGITUD_MIN + LONG_MIN + " " + 
				ErrorMessages.PROERR_LONGITUD_MAX + LONG_MAX)).when(serviceCat).update(c1);
		
		assertThat(cat.modificarCategoria(c1)).containsExactly("400", ErrorMessages.PROERR_LONGITUD_MIN + LONG_MIN + " " + 
				ErrorMessages.PROERR_LONGITUD_MAX + LONG_MAX);
	}*/
	
	/**
	 * Test para comprobar que lanza excepción si el ID de la categoria no existe
	 * @throws DomainException
	 * @throws DAOException
	 */
	/*
	@Test
	void testModificarCategoriaErrorDAO() throws DomainException, DAOException {
		Categoria c1 = new Categoria();
		c1.setId_categoria(111);
		
		doThrow(new DAOException(ErrorMessages.ERR_ARG_CAT_ID)).when(serviceCat).update(c1);
		
		assertThat(cat.modificarCategoria(c1)).containsExactly("500", ErrorMessages.ERR_ARG_CAT_ID);
	}*/
}
