//  listNode.java
//  Student ID: 260712639
//  Name: Yudi.Xie
//  Created by Yudi.Xie on 2017/10/29.
//  Copyright © 2017 Yudi.Xie. All rights reserved.

public class listNode<N>{
	public N data;             //data element to store node
	public listNode next;      //reference to store next node

	public listNode(N data, listNode next)
	{
		this.data = data;       //Reference
		this.next = next;
	}
}
