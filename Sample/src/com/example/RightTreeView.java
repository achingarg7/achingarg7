package com.example;

public class RightTreeView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RightTreeView rightTreeView = new RightTreeView();
		Height height = new Height();
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);

		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		node3.setRight(node7);
		node4.setRight(node8);
		node6.setLeft(node9);
		node8.setRight(node10);

		rightTreeView.rightView(node1, height,1);
	}

	private void rightView(TreeNode node, Height height, int level) {
		if(node==null) {
			return;
		}
		
		if(height.getMaxHieght()<level) {
			System.out.print(" "+node.getValue());
			height.setMaxHieght(level);
		}
		rightView(node.getRight(), height, level+1);
		rightView(node.getLeft(), height, level+1);
		
	}

}
