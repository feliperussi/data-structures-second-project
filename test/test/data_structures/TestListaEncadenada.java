package test.data_structures;

import model.data_structures.ListaEncadenada;
import model.data_structures.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TestListaEncadenada 
{
	private ListaEncadenada<Integer> lista;
	private static final int TAMANO = 100;
	
	public TestListaEncadenada() 
	{
		lista = new ListaEncadenada<Integer>();
	}
	
	public void setUp1() 
	{
		//Arreglo por defecto			
		for (Integer i = 0; i < 100; i++)
		{
			lista.append(i);			
		}		
	}
	
	
	@Test
	public void ListaEncadenada() 
	{
		assertTrue("La lista no debe ser nula", lista != null);
		assertTrue("La lista no puede tener cabeza", lista.head() == null);
		assertEquals("El tamaño de la lista debe ser cero", 0, lista.size());
		
		setUp1();	
		assertTrue("La lista no debe ser nula", lista != null);
		assertTrue("La lista debería tener cabeza", lista.head() != null);
		assertEquals("El tamaño de la lista no es igual a " + TAMANO,TAMANO, lista.size());
	}
	
	@Test
	public void addFirst()
	{
		setUp1();
		Integer numero1 = 158;
		lista.addFirst(numero1);
		assertTrue("El primer número debe ser " + numero1, lista.firstElement() == numero1);
		
		Integer numero2 = numero1+28;
		lista.addFirst(numero2);
		assertEquals("El primer número debe ser " + numero2, numero2, lista.firstElement());	
	}
	
	@Test
	public void append()
	{
		setUp1();
		Integer numero1 = 160;
		lista.append(numero1);
		assertTrue("El último número debe ser " + numero1, lista.lastElement() == numero1);
		
		Integer numero2 = numero1+20;
		lista.append(numero2);
		assertEquals("El último número debe ser " + numero2, numero2, lista.lastElement());	
	}
	
	@Test
	public void buscar()
	{
		setUp1();
		Integer numero1 = 50;
		assertEquals("El ítem retornado debe ser " + numero1, numero1, lista.buscar(numero1));	
	}
	
	@Test
	public void size()
	{
		setUp1();
		Integer numero1 = 160;
		int aumentar = TAMANO;
		lista.append(numero1);
		assertEquals("El tamaño de la lista debe ser " + ++aumentar, aumentar, lista.size());	
		
		lista.append(++numero1);
		assertEquals("El tamaño de la lista debe ser " + ++aumentar, aumentar, lista.size());	
		
		lista.append(++numero1);
		assertEquals("El tamaño de la lista debe ser " + ++aumentar, aumentar, lista.size());	
		
		lista.append(++numero1);
		assertEquals("El tamaño de la lista debe ser " + ++aumentar, aumentar, lista.size());	
	}
	
	@Test
	public void buscarDato()
	{
		setUp1();
		Integer numero1 = 55;
		Node<Integer> nodo = new Node<Integer>(numero1);
		
		assertEquals("El ítem del nodo retornado debe ser " + numero1, nodo.getItem(), lista.buscarDato(numero1).getItem());	
	}
	
	@Test
	public void get()
	{
		setUp1();
		Integer numero1 = 12;
		Integer encontrado = lista.get(13);
		assertEquals("El ítem retornado debe ser " + numero1, numero1, lista.get(13));			
	}
	
	@Test
	public void changeInfo()
	{
		setUp1();
		Integer numero1 = 12424;
		lista.changeInfo(13, numero1);
		assertEquals("El ítem retornado debe ser " + numero1, numero1, lista.get(13));	
		
		lista.changeInfo(14, ++numero1);
		assertEquals("El ítem retornado debe ser " + numero1, numero1, lista.get(14));	
	}
	
	@Test
	public void exchange()
	{
		setUp1();
		Integer numero13 = lista.get(13);
		Integer numero55 = lista.get(55);
		
		lista.exchange(13, 55);
		
		assertEquals("El ítem retornado debe ser " + numero13, numero13, lista.get(55));	
		assertEquals("El ítem retornado debe ser " + numero55, numero55, lista.get(13));		
	}
	
	@Test
	public void isEmpty()
	{
		assertTrue("La lista no debe ser nula", lista != null);
		assertTrue("La lista debe ser vacía", lista.isEmpty());
	}
	
	@Test
	public void isPresent()
	{
		setUp1();
		Integer numero13 = lista.get(13);
		Integer numero55 = lista.get(55);
		
		lista.exchange(13, 55);
		
		assertEquals("La posición debería ser " + 13, 13, lista.isPresent(numero55));	
		assertEquals("La posición debería ser " + 55, 55, lista.isPresent(numero13));		
	}
	
	@Test
	public void insertElement()
	{
		setUp1();
		Integer insertado = 14785;
		Integer insertado2 = insertado+75;
		
		Integer numero10 = lista.get(10);		
		lista.insertElement(insertado, 10);
		
		Integer numero89 = lista.get(89);
		lista.insertElement(insertado2, 89);
			
		assertEquals("No se insertó el número " + insertado, insertado, lista.buscar(insertado));
		assertEquals("Se insertó en la posición equivocada ", 10, lista.isPresent(insertado));
		assertEquals("No se insertó el número " + insertado2, insertado2, lista.buscar(insertado2));
		assertEquals("Se insertó en la posición equivocada ", 89, lista.isPresent(insertado2));
		assertEquals("Se cambió mal el elemento " + numero10, numero10, lista.get(11));
		assertEquals("Se cambió mal el elemento " + numero89, numero89, lista.get(90));
	}
	
	@Test
	public void remove()
	{
		setUp1();
		Integer insertado = 14785;
		Integer insertado2 = insertado+75;
		Integer numero10 = lista.get(10);
	
		lista.insertElement(insertado, 10);
		Integer numero89 = lista.get(89);
		
		lista.insertElement(insertado2, 89);
		
		lista.removeByIndex(89);
		assertEquals("No se eliminó el número " + insertado2, numero89, lista.get(89));	
		
		lista.removeByType(insertado);
		
		assertTrue("No se eliminó el dato " + insertado, lista.buscar(insertado) == null);	
		
		Integer primero = lista.firstElement();
		Integer ultimo = lista.lastElement();
		
		lista.removeFirst();
		assertTrue("No se eliminó el primer dato ", lista.firstElement() != primero);	
		lista.removeLast();
		assertTrue("No se eliminó el último dato ", lista.lastElement() != ultimo);	
		
	}
}
