package model.data_structures;

public class Node<E> 
{
	private Node<E> next;

	private E item;

	public Node (E item) 
	{
		next = null;
		this.item = item;
	}
	public Node<E> getNext() 
	{
		return next;
	}
	
	public void setNextNode ( Node<E> next) 
	{
		this.next = next;
	}
	
	public E getItem()
	{
		return item;
	}
	
	public void setItem (E item) 
	{
		this.item = item;
	}
	
	public boolean hasNext()
	{
		if(this.next != null)
			return true;
		return false;		
	}

}
