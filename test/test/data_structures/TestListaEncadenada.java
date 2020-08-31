package test.data_structures;

import model.data_structures.ArregloDinamico;
import model.data_structures.ListaEncadenada;

import java.util.Arrays;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class TestListaEncadenada 
{
	private ListaEncadenada<Integer> lista;
	
	public void setUp1() 
	{
		//Arreglo por defecto
		lista = new ListaEncadenada();
		
		for (Integer i = 10; i < 20; i++)
		{
			lista.append(i);			
		}		
	}
	
	@Test
	public void testArregloDinamico() 
	{
		setUp1();		

		for (int i = 1; i < 11; i++) 
		{
			System.out.println(i+ ": "+ lista.giveMeThatNode(i).getItem());
		}
		
		lista.exchange(5, 8);
		
		System.out.println("\n");
		for (int i = 1; i < 11; i++) 
		{
			System.out.println(i+ ": " + lista.giveMeThatNode(i).getItem());
		}
		
		System.out.println("\n");
		lista.changeInfo(7, 1254);
		
		for (int i = 1; i < 11; i++) 
		{
			System.out.println(i+ ": " + lista.giveMeThatNode(i).getItem());
		}
		
		System.out.println("\n"+ "Indice");
		int resp = lista.isPresent(1254);
		System.out.println(resp);
		
		System.out.println("\n");
		
		System.out.println(lista.tail().getItem());
		System.out.println("\n");
		
		System.out.println(lista.size());
		System.out.println("\n");
		
		lista.insertElement(47858, 7);
		lista.insertElement(47, 10);
		lista.changeInfo(5, 10000);
		
		for (int i = 1; i <= lista.size(); i++) 
		{
			System.out.println(i+ ": " + lista.giveMeThatNode(i).getItem());
		}
		
		
		System.out.println("\n"+" Remove: "+ lista.removeByType(10000));
		System.out.println("\n"+" Remove: "+ lista.removeFirst());
		System.out.println("\n"+" Remove: "+ lista.removeLast());
		System.out.println("\n"+" Remove: "+ lista.removeByIndex(3));
		for (int i = 1; i <= lista.size(); i++) 
		{
			System.out.println(i+ ": " + lista.giveMeThatNode(i).getItem());
		}
	}
}
