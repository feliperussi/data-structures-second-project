package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;

import model.logic.Modelo;
import model.logic.Peliculas;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {

	private Modelo modelo;
	private static int CAPACIDAD = 100;

	@Before
	public void setUp1() throws IOException {
		modelo = new Modelo(CAPACIDAD);
		// modelo.agregarDatosCsvOpt();
		System.out.println("Numero de peliculas cargadas: " + modelo.darTamano());
	}

	@Test
	public void testModelo() throws IOException {
		assertTrue(modelo != null);
		assertEquals(1988, modelo.darTamano()); // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarTamano() throws IOException {
		setUp1();
		assertTrue(modelo != null);
		assertEquals(1988, modelo.darTamano());
	}

	@Test
	public void testAgregar() throws IOException {
		setUp1();
		Peliculas prueba = modelo.darDatos().firstElement();
		Peliculas eliminada = modelo.darDatos().removeByIndex(1);
		assertEquals("Error", prueba.darNombre(), eliminada.darNombre());

		modelo.darDatos().addFirst(eliminada);
		assertEquals("Error agregando.", modelo.darDatos().firstElement().darNombre(), eliminada.darNombre());

		Peliculas prueba2 = modelo.darDatos().get(5);
		Peliculas eliminada2 = modelo.darDatos().removeByIndex(5);
		modelo.darDatos().append(prueba2);

		assertEquals("Error agregando.", modelo.darDatos().lastElement().darNombre(), eliminada2.darNombre());
	}

	@Test
	public void testBuscar() throws IOException {
		setUp1();
		Peliculas prueba = modelo.darDatos().removeByIndex(20);
		assertTrue("Resultado inesperado. ", !prueba.darNombre().equals(modelo.darDatos().get(20).darNombre()));

		assertTrue("Deber�a ser null", modelo.buscar(prueba) == null);
	}

	@Test
	public void testEliminar() throws IOException {
		setUp1();
		Peliculas prueba = modelo.darDatos().firstElement();
		Peliculas eliminada = modelo.darDatos().removeByIndex(1);
		assertEquals("Error", prueba.darNombre(), eliminada.darNombre());
	}

}