//  Stack.java
//  Student ID: 260712639
//  Name: Yudi.Xie
//  Created by Yudi.Xie on 2017/10/29.
//  Copyright © 2017 Yudi.Xie. All rights reserved.

public class Stack<N>
{

	public listNode top;            //node points to the top
	public int size = 0;            //Number of elementss stored

	public Stack()                  //Create empty stack
	{
		top=null;
	}

	public void push(N key)           //push the element into the stack
	{                                 //let top points to the new node
		top = new listNode(key, top); //And new node points to oldtop
		size++;
	}

	public N pop()                     //pop element off the stack
	{
		if (size == 0)                 //return null if stack empty
		{
            System.out.println("Error: empty stack");
			return null;
		}

		listNode oldTop = top;         //let top points to the next
		top = top.next;                //node of the oldTop
        oldTop.next = null;            //Free the oldtop
		size--;
        return (N)oldTop.data;
	}

	public N getTop()                   //method to peek the top element
	{
		if (size == 0)                  //return null if stack empty
		{
			return null;
		}
		return (N)top.data;
	}

	public boolean isEmpty()            //Determine if the stack is empty
	{
		return size == 0;
	}

	public String toString()            //Method to convert the elements into
	{                                   //strings
		if (isEmpty())
		{
			return"empty";
		}
        else
		{
			StringBuilder sb = new StringBuilder("");
			for (listNode current = top; current !=null; current = current.next)
			{
				sb.append(current.data.toString() + " ");
			}
            sb.append("");
			int len = sb.length();
            return sb.delete(len, len ).toString();
		}
    }
}

