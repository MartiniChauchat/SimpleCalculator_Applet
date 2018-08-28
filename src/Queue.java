//  Queue.java
//  Student ID: 260712639
//  Name: Yudi.Xie
//  Created by Yudi.Xie on 2017/10/29.
//  Copyright © 2017 Yudi.Xie. All rights reserved.

public class Queue<N>
{
	public listNode front;                      //node points to the front
	public listNode rear;                       //node points to the rear
	public int size = 0;                        //Number of elements stored

	public Queue(){                             //Initialize the queue, now the queue is empty
		listNode n = new listNode(null, null);
		n.next = null;
		front = rear = n;
	}

	public void enqueue(N data)                 //inset node at the rear
	{
		listNode s=new listNode(data, null);    //incoming node has no following node
		rear.next = s;                          //let the old rear node's next node points to the new node
		rear=s;                                 //let rear points to the last node
		size++;
	}

	public N dequeue()                          //remove node at the front
	{
		if(rear == front)                       //In case the queue is empty
		{                                       //then handle the wrong case
			try
			{
				throw new Exception("empty");
			} catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		else{
			listNode p = front.next;            //points to the first node in front
			N x = (N) p.data;                   
			front.next = p.next;
			if(p.next == null)
				rear=front;
			p=null;
			size--;
			return x;
		}
	}

	public boolean isEmpty()        //Determine if the stack is empty
	{
		return size==0;
	}

	public N getfront(){			//Peek the front element
		if(isEmpty()){
			throw new RuntimeException(("Empty!"));
		}
		return (N)front.next.data;
	}

	public String toString()        //Method to convert the elements into
	{                               //strings
		if(isEmpty())
		{
			return "empty";
		}
		else
		{
			StringBuilder sb = new StringBuilder("");
			for(listNode current=front.next;current!=null;current=current.next)
			{
				sb.append(current.data.toString() + " ");
			}
			int len = sb.length();
			return sb.delete(len, len).append("").toString();
		}
	}
}
