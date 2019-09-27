package com.example;

public class CheckBST {

	public void check(TreeNode node) throws Exception {
		if (node == null) {
			return;
		}
		System.out.println(node.getValue());
		if (node.getLeft() != null) {
			if (node.getValue() < node.getLeft().getValue()) {
				throw new Exception();
			}

			if (node.getLeft().getRight() != null) {
				if (node.getValue() < node.getLeft().getRight().getValue()) {
					throw new Exception();
				}
			}

		}
		if (node.getRight() != null) {
			if (node.getValue() > node.getRight().getValue()) {
				throw new Exception();
			}
			if (node.getRight().getLeft() != null) {
				if (node.getValue() > node.getRight().getLeft().getValue()) {
					throw new Exception();
				}
			}
		}
		
		check(node.getLeft());
		check(node.getRight());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckBST bst = new CheckBST();
		TreeNode node1 = new TreeNode(15);
		TreeNode node2 = new TreeNode(12);
		TreeNode node3 = new TreeNode(17);
		TreeNode node4 = new TreeNode(10);
		TreeNode node5 = new TreeNode(14);
		TreeNode node6 = new TreeNode(16);
		TreeNode node7 = new TreeNode(19);
		TreeNode node8 = new TreeNode(13);
		TreeNode node9 = new TreeNode(18);

		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		node3.setRight(node7);
		node5.setLeft(node8);
		node6.setRight(node9);
		
		try {
			bst.check(node1);
			System.out.println("Binary Tree is BST");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Binary Tree is not BST");
			}
	}

}
