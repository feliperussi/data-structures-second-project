package model.data_structures;

import java.util.NoSuchElementException;


public class ListaEncadenada<T extends Comparable<T>> implements Lista<T>
{
    private static final long serialVersionUID = 6769829250639411880L;

	/**
	 * Primer nodo de la lista.
	 */
	private Node<T> list;

	/**
	 * Ultimo nodo de la lista.
	 */
	private Node<T> last;
	
	/**
	 * Tamano de la lista.
	 */
	private int listSize;

	public ListaEncadenada() 
	{
		list = null;
		last = null;
	}

	public ListaEncadenada(T item)
	{
		list = new Node<T> (item) ;
		last = list;
		listSize = 1;
	}

	/**
	 * Retorna la cantidad de nodos
	 */
	public int size() 
	{
		return listSize;
	}

	public Node<T> head()
	{
		return list;
	}
	
	public Node<T> tail()
	{
		return last;
	}
	/**
	 * Retorna el item del nodo seg�n el �ndice. El primer node es el n�mero 1.
	 */
	public T get(int index) 
	{
		index = index - 1;
		T resp = null;

		if(index == 0)
			return list.getItem();

		Node<T> tempNode = list;
		
		boolean stop = false;

		for(int i = 1; i <= index && !stop; i++ )
		{		
			tempNode = tempNode.getNext();

			if(tempNode != null)
			{
				if(i == index)
				{		
					resp = tempNode.getItem();
				}
				stop = true;
			}	
		}
		return resp;
	}

	/**
	 * Inserta un item en la lista en la posici�n dada. 
	 */
	public void insertElement(T element, int pos) 
	{
		if( list == null )
			throw new NoSuchElementException();
		
		if(pos > size())
			throw new NoSuchElementException();
			
		else if(pos == 1)
		{
			addFirst(element);
		}
		
		else if(pos == size())
		{
			append(element);
		}
		
		else
		{
			Node<T> newNode = new Node<T>(element);	
			Node<T> tempNode = giveMeThatNode(pos);
			Node<T> previousNode = giveMeThatNode(pos-1);
						
			newNode.setNextNode(tempNode);
			previousNode.setNextNode(newNode);
			listSize++;
		}
	}

	/**
	 * Agrega un item antes del primer elemento actual
	 */
	public void addFirst(T item) 
	{
		Node <T> newHead = new Node<> (item);
		newHead.setNextNode(list);
		list = newHead;
		listSize++;
	}

	/**
	 * Agrega un item al �ltimo elemento de la lista
	 */
	public void append(T item) 
	{
		Node <T> newNode = new Node<> (item);

		if(list == null) 
		{
			list = newNode;
			last = list;
		}

		else 
		{
			last.setNextNode(newNode);
			last = last.getNext();
		}
		
		listSize++;

	}

	/**
	 * Retorna el primer elemento de la lista
	 */
	public T firstElement() 
	{	
		return list.getItem();
	}

	/**
	 * Retorna el �ltimo elemento de la lista
	 */
	public T lastElement() 
	{
		return last.getItem();
	}

	/**
	 * Busca un elemento de la lista seg�n el tipo de dato.
	 */
	public Node<T> buscarDato(T dato) 
	{
		int c = 0;
		
		Node <T> nodeToSearch = new Node<> (dato);
		Node <T> tempNode = list;
		
		while(c < size())
		{
			if(nodeToSearch.getItem().compareTo(tempNode.getItem()) == 0)
			{
				nodeToSearch = tempNode;
				break;
			}
			else if(tempNode.getNext() != null)
			{
				tempNode = tempNode.getNext();
				c++;
			}
			else 
			{
				nodeToSearch = null;
				break;
			}
				
		}
		return nodeToSearch;
	}

	/**
	 * Retorna True si la lista est� vac�a. False si no lo est�.
	 */
	public boolean isEmpty() 
	{
		if(size() == 0)
			return true;
		else
			return false;
	}

	/**
	 * Retorna la posici�n del elemento por par�metro con respecto al primer elemento de la lista.
	 * Retorna 0 si no lo encuentra.
	 */
	
	public int isPresent(T element)
	{
		int c = 0;
		int resp = 0;
		Node <T> nodeToSearch = new Node<> (element);
		Node <T> tempNode =  list;
		
		while(c < size())
		{
			if(nodeToSearch.getItem().compareTo(tempNode.getItem()) == 0)
			{
				nodeToSearch = tempNode;
				resp = c+1;
				break;
			}
			else if(tempNode.getNext() != null)
			{
				c++;
				tempNode = tempNode.getNext();	
			}	
		}
		return resp;
	}

	/**
	 * Intercambia la posici�n de dos elementos de la lista.
	 */
	public void exchange(int pos1, int pos2)
	{
		Node <T> node1 = giveMeThatNode(pos1);
		Node <T> node2 = giveMeThatNode(pos2);
		
		if(node1.getItem() != null && node2.getItem() != null)
		{		
			T item1 = node1.getItem();
			node1.setItem(node2.getItem());
			node2.setItem(item1);
		}
		else
			throw new NoSuchElementException();
		
	}

	/**
	 * Cambia la informacion del elemento en la posicion dada por parametro
	 */
	public void changeInfo(int pos, T elem) 
	{
		Node <T> node1 = giveMeThatNode(pos);
		node1.setItem(elem);
	}

	/**
	 * Elimina el item dado por par�metro.
	 */
	public T removeByType(T dato) 
	{
		T resp = dato;
		Node<T> newNode = new Node<T>(dato);
		
		if(list.getItem().compareTo(dato) == 0)
		{
			list = newNode; 
		}
		
		else if(last.getItem().compareTo(dato) == 0)
		{
			last = newNode;
		}
		
		else
		{
			Node<T> find = buscarDato(dato);
			resp = find.getItem();
			
			if(resp == null)
			{
				throw new NoSuchElementException();
			}
			else
			{
				int index = isPresent(dato)-1;	
				giveMeThatNode(index).setNextNode(find.getNext());
				
			}		
		}
		listSize--;
			return resp;		
	}

	/**
	 * Elmina el primer elemento de la lista.
	 */
	public T removeFirst() 
	{
		T resp = firstElement();
		
		if(resp != null)
		{
			list = list.getNext();
			listSize--;
		}
		
		return resp;
	}

	/**
	 * Elimina el �ltimo elemento de la lista.
	 */
	public T removeLast() 
	{
		T resp = lastElement();
		if(resp != null)
		{
			last = giveMeThatNode(size()-1);
			listSize--;
		}
		return resp;
	}

	/**
	 * Elimina el nodo en la posici�n dada por par�metro.
	 */
	public T removeByIndex(int index) 
	{
		T resp = null;

		if(index == 1)
		{
			resp = firstElement();
			removeFirst();
		}
		
		else if(index == size())
		{
			resp = lastElement();
			removeLast();
		}
		
		else
		{
			Node<T> find = giveMeThatNode(index);
			resp = find.getItem();
			
			if(resp == null)
			{
				throw new NoSuchElementException();
			}
			else
			{
				int i = index-1;	
				giveMeThatNode(i).setNextNode(find.getNext());
				
			}		
		}
		listSize--;
			return resp;		
	} 

	/**
	 * Retorna el nodo seg�n el �ndice.
	 * @param index
	 * @return
	 */
	public Node<T> giveMeThatNode(int index)
	{
		index = index - 1;
		Node<T> resp = null;

		if(index+1 == size())
		{
			resp = last;
		}
		else if (index < size()) 
        {
            resp = list;
            for (int i = 0; i < index; i++)
            {
                resp = resp.getNext();
            }
            return resp;
        } 
        

        return resp;
	}

	@Override
	public T buscar(T dato) 
	{
		T resp = buscarDato(dato).getItem();
		return resp;
	}
}
