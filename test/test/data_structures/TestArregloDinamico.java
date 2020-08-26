package test.data_structures;

import model.data_structures.ArregloDinamico;
import java.util.Arrays;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings("all")
public class TestArregloDinamico 
{

	private ArregloDinamico arreglo;
	private static int TAMANO = 100;

	@Before
	public void setUp1() 
	{
		//Arreglo por defecto
		arreglo = new ArregloDinamico(TAMANO);
		
		for (int i = 0; i < TAMANO; i++) 
		{
			arreglo.agregar(i);
		}	
	}

	public void setUp2() 
	{
		//Expandir arreglo
		for (int i = TAMANO; i < TAMANO * 2; i++) 
		{
			arreglo.agregar(i);
		}
	}

	@Test
	public void testArregloDinamico() 
	{
		// Completar la prueba
		setUp1();
		assertTrue(arreglo != null);
		assertEquals(TAMANO, arreglo.darTamano()); // El arreglo tiene 0 elementos presentes.
		assertEquals(TAMANO, arreglo.darCapacidad()); // La capacidad maxima del arreglo es 100
		
		setUp2();
		assertEquals(TAMANO*2, arreglo.darTamano());
		assertEquals(TAMANO*2, arreglo.darCapacidad());
	}

	@Test
	public void testDarElemento() 
	{
		setUp1();
		// Completar la prueba
		assertTrue("El arreglo es nulo", arreglo != null);
		assertEquals("El tamaño es incorrecto.", arreglo.darTamano(), TAMANO); // El arreglo deberï¿½a tener 200 elementos presentes.
		assertEquals("La capacidad no es la adecuada", arreglo.darCapacidad(), TAMANO); // La capacidad maxima del arreglo se debiï¿½ dubplicar
		// Encuentra un elemento agregado dentro del limite inicial de la capacidad
		assertEquals("El elemento es inadecuado", arreglo.darElemento(1), 0);
		assertEquals("El elemento es inadecuado", arreglo.darElemento(5), 4);
		
		setUp2();
		assertTrue("El arreglo es nulo", arreglo != null);
		assertEquals("El tamaño es incorrecto.", arreglo.darTamano(), TAMANO*2);
		assertEquals("El elemento es inadecuado", arreglo.darElemento(120), 119);
		assertEquals("El elemento es inadecuado", arreglo.darElemento(80), 79);
		// Encuentra un elemento agregado despues de aumentar la capacidad
	}
	
	@Test
	public void testAddFirst()
	{
		Integer element = 12445;
		setUp1();
		assertTrue("El arreglo es nulo", arreglo != null);
		arreglo.addFirst(element);
		assertEquals("El primer elemento es inadecuado", arreglo.firstElement(), element);
		assertEquals("El ultimo elemento es inadecuado", arreglo.darElemento(arreglo.darTamano()), TAMANO-1);
		
		Integer element2 = 14856;
		arreglo.addFirst(element2);
		assertEquals("El primer elemento es inadecuado", arreglo.firstElement(), element2);
		assertEquals("El segundo elemento es inadecuado", arreglo.darElemento(2), element);
		assertEquals("El ultimo elemento es inadecuado", arreglo.darElemento(arreglo.darTamano()), TAMANO-1);
	}
	
	@Test
	public void testAgregar()
	{
		Integer element = 45;
		setUp1();
		arreglo.agregar(element);
		assertEquals("El primer elemento es inadecuado", arreglo.firstElement(), 0);
		assertEquals("El ultimo elemento es inadecuado", arreglo.darElemento(arreglo.darTamano()), element);
		
		setUp2();
		Integer element2 = 148;
		arreglo.agregar(element2);
		assertEquals("El primer elemento es inadecuado", arreglo.firstElement(), 0);
		assertEquals("El ultimo elemento es inadecuado", arreglo.darElemento(arreglo.darTamano()), element2);
	
	}
	
	@Test
	public void testBuscar()
	{
		Integer element = 148;
		setUp1();
		arreglo.agregar(element);
		assertEquals("El elemento buscado es inadecuado", 148, arreglo.buscar(element));
		
		setUp2();
		arreglo.agregar(element+254);
		assertEquals("El elemento buscado es inadecuado", element+254, arreglo.buscar(element+254));
	}
	
	@Test
	public void testExChangeInfo()
	{
		
		Integer element = 148;
		setUp1();
		arreglo.agregar(element);
		Integer datoPrueba = (Integer) arreglo.darElemento(12);
		arreglo.exchange(arreglo.darTamano(), 12);
		
		assertEquals("Datos mal intercambiados", datoPrueba, arreglo.lastElement());
		assertEquals("Datos mal intercambiados", element, arreglo.darElemento(12)) ;
		
		setUp2();
		Integer element2 = 145;
		arreglo.agregar(element2);	
		
		int indexElement2 = arreglo.isPresent(element2);
		int indexPrueba2 = 12;

		Integer datoPrueba2 = (Integer) arreglo.darElemento(indexPrueba2);
		arreglo.exchange(indexPrueba2, indexElement2);
		
		assertEquals("Datos mal intercambiados", datoPrueba2, arreglo.darElemento(indexElement2));
		assertEquals("Datos mal intercambiados", element2, arreglo.darElemento(indexPrueba2));
	}
	
	@Test
	public void testEliminarPorTipo()
	{
		setUp1();
		Integer dato = 25;
		int indexDato = arreglo.isPresent(dato);
		Integer respuesta = (Integer) arreglo.eliminarPorTipo(dato);
		assertTrue("Eliminó mal", dato == respuesta);	
	}
	
	@Test
	public void testEliminarPorIndice()
	{
		setUp1();
		Integer dato = 25;		
		int indexDato = arreglo.isPresent(dato);
		Integer respuesta = (Integer) arreglo.eliminarPorIndice(indexDato);
		assertTrue("Eliminó mal", dato == respuesta);	

	}
	
}
