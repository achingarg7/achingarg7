package com.example.java8.lamda;

public class Tree {

	static class Node{
		int key;
		Node left;
		Node right;
		
		Node(int key){
			this.key=key;
			this.left=null;
			this.right=null;
		}
	}
	
	public static boolean search(int key,Node root){
		if(root==null){
			return false;
		}else if(root.key==key){
			return true;
		}
		else if(search(key, root.left)){
			return true;
		}
		else if(search(key, root.right)){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node root=new Node(2);
		root.left=new Node(7);
		root.left.left=new Node(2);
		root.left.right=new Node(6);
		root.left.right.left=new Node(5);
		root.left.right.right=new Node(11);
		root.right=new Node(5);
		root.right.right=new Node(9);
		root.right.right.left=new Node(4);
		
		System.out.println(search(16, root));

	}

}
