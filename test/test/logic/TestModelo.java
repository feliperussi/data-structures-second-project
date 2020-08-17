package test.logic;

import static org.junit.Assert.*;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	private static int CAPACIDAD=100;
	
	@Before
	public void setUp1() {
		modelo= new Modelo(CAPACIDAD);
	}

	public void setUp2() {
		for(int i =0; i< CAPACIDAD;i++){
			modelo.agregar(i);
		}
	}

	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarTamano() {
		// Completar la prueba
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // El modelo deberia tener 100 elementos
		setUp2();
		assertEquals(CAPACIDAD, modelo.darTamano());  // El modelo deberia tener 100 elementos
	}

	@Test
	public void testAgregar() {
		Integer prueba= 1;
		assertTrue(modelo!=null);
		modelo.agregar(prueba);
		assertEquals(1, modelo.darTamano());  			// El modelo deberia tener 1 elemento
		assertEquals(prueba, modelo.buscar(prueba));    // El modelo deberia encontrar el dato
	}

	@Test
	public void testBuscar() {
		setUp2();
		// Completar la prueba
		Integer prueba= 1;
		Integer noPrueba= CAPACIDAD++;
		assertTrue(modelo!=null);
		assertEquals(prueba, modelo.buscar(prueba));    // El modelo deberia encontrar el dato
		assertNull(modelo.buscar(noPrueba));            // El modelo no deberia encontrar el dato
	}

	@Test
	public void testEliminar() {
		setUp2();
		// Completar la prueba
		Integer prueba= 1;
		assertTrue(modelo!=null);
		assertEquals(prueba, modelo.buscar(prueba));    // El modelo deberia encontrar el dato
		modelo.eliminar(prueba);
		assertNull(modelo.buscar(prueba));        	    // El modelo no deberia encontrar el dato
		assertEquals(CAPACIDAD--, modelo.darTamano());  // El modelo deberia tener 99 elementos
	}

}
