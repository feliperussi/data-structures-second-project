package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);
	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(i);
		}
	}

	@Test
	public void testArregloDinamico() {
		// Completar la prueba
		assertTrue(arreglo!=null);
		assertEquals(0, arreglo.darTamano());  				// El arreglo tiene 0 elementos presentes.
		assertEquals(TAMANO, arreglo.darCapacidad());  		// La capacidad maxima del arreglo es 100
	}

	@Test
	public void testDarElemento() {
		setUp2();
		//Completar la prueba
		assertTrue(arreglo!=null);
		assertEquals(2*TAMANO, arreglo.darTamano());  		// El arreglo debería tener 200 elementos presentes.
		assertEquals(2*TAMANO, arreglo.darCapacidad());  	// La capacidad maxima del arreglo se debió dubplicar
		// Encuentra un elemento agregado dentro del limite inicial de la capacidad
		assertEquals(" "+1, arreglo.darElemento(1));	
		// Encuentra un elemento agregado despues de aumentar la capacidad
		assertEquals(" "+TAMANO++, arreglo.darElemento(TAMANO++));
	}

}
