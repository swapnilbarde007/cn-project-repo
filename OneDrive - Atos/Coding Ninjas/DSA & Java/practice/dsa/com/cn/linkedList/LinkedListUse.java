package com.cn.linkedList;

import java.util.ArrayList;
import java.util.Scanner;

public class LinkedListUse {
	static Node<Integer> headNode;
	static Node<Integer> tailNode;

	public static void printLinkedList(Node head) {
		while (head != null) {
			System.out.println(head.data);
			head = head.next;
		}
		// if(head.next==null) {
		// System.out.println(head.data);
		// }

	}

	public static void increment(Node<Integer> head) {
		Node<Integer> tmp = head;
		while (tmp != null) {
			tmp.data++;
			tmp = tmp.next;
		}
	}

	public static Node<Integer> createLinkedList() {
		Node head;

		// Node 1
		Node<Integer> n1 = new Node<Integer>(20);

		// Node 2
		Node<Integer> n2 = new Node<Integer>(30);

		// Node 3
		Node<Integer> n3 = new Node<Integer>(40);

		// Node 4
		Node<Integer> n4 = new Node<Integer>(50);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;

		return n1;

	}

	public static Node<Integer> getInputForLL() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter value: ");
		int ip = sc.nextInt();
		Node<Integer> head = new Node<Integer>(ip);
		Node<Integer> headBkp = head;
		Boolean ind = false;
		while (ind == false) {
			ip = sc.nextInt();
			if (ip == -1) {
				ind = true;
			} else {
				Node<Integer> node = new Node<Integer>(ip);
				head.next = node;
				head = node;
			}
		}
		headNode = headBkp;
		tailNode = head;
		// sc.close();
		return headBkp;
	}

	public static void main(String[] args) {
		// Node<Integer> head=createLinkedList();
		headNode = getInputForLL();
		// increment(head);
		System.out.println("Length of linked list: " + getLength(headNode));
		printLinkedList(headNode);
		// System.out.println("Add Item to linked list(value)");
		Scanner sc = new Scanner(System.in);
		// int ip=sc.nextInt();
		//
		// System.out.println("Add Item to linked list(pos)");
		// int pos=sc.nextInt();
		// Node<Integer> n1=insertNode(headNode, pos, ip);
		// addNodeToLLEnd(n1);

		// printLinkedList(n1);
		// System.out.println("Enter idx for node to be deleted");
		// int idx=sc.nextInt();
		// Node<Integer> deletedNodeHead=deleteNode(headNode,idx);
		// printLinkedList(deletedNodeHead);

		// System.out.println("Enter item to be searched");
		// int itemSearch = sc.nextInt();
		// int foundIdx = findNode(headNode, itemSearch);
		// System.out.println("Found at idx: " + foundIdx);
		// printIthNode(head,2);
		// System.out.println(head);
		System.out.println("Enter seq no. to cut from: ");
		int cutNode = sc.nextInt();
		Node<Integer> outputNode = appendLastNToFirst(headNode, cutNode);
		printLinkedList(outputNode);
		sc.close();

	}

	public static int findNode(Node<Integer> head, int n) {
		int counter = 0;
		while (head != null) {
			if (head.data == n) {
				return counter;
			}
			head = head.next;
			counter++;
		}
		return -1;
	}

	public static Node<Integer> appendLastNToFirst(Node<Integer> head, int n) {
		int intersect = (getLength(head) - n) + 1;
		if (head == null) {
			return head;
		}
		if (n > getLength(head)) {
			return head;
		}
		if (n == 0) {
			return head;
		}
		Node<Integer> headBkp = head;
		Node<Integer> newHead = null;
		Node<Integer> lastNode = null;
		Node<Integer> prevNode = null;

		int counter = 1;
		while (head != null) {
			// Traverse and reach the index of n mark the node in start var
			if (counter == (intersect - 1)) {
				prevNode = head;
			}
			if (intersect == counter) {
				newHead = head;
			}
			if (head.next == null) {
				lastNode = head;
			}
			head = head.next;
			counter++;
			// Continue traverse and reach till end
		}
		lastNode.next = headBkp;
		prevNode.next = null;

		// prev head
		// Consider start as head
		// start.next=prev head
		return newHead;
	}

	public static void printIthNode(Node<Integer> head, int i) {
		if (head == null) {
			return;
		}
		int counter = 0;
		Node<Integer> temp = head;
		while (temp != null) {
			if (counter == i) {
				System.out.println(temp.data);
			}
			temp = temp.next;
			counter++;
		}

	}

	public static Node<Integer> insertNode(Node<Integer> head, int pos, int data) {
		if (head == null) {
			return head;
		}
		// Transform inputdata into new node that should be inserted
		Node<Integer> newNode = new Node<Integer>(data);

		// Traverse through LL to get desired position
		Node<Integer> headBkp = head;
		Node<Integer> prevNode = null;
		int counter = 0;
		while (head != null) {
			if (counter == pos) {
				if (prevNode == null) {
					newNode.next = head;
					headBkp = newNode;
					break;
				} else {
					prevNode.next = newNode;
					newNode.next = head;
				}

			}
			prevNode = head;
			head = head.next;

			counter++;
		}
		return headBkp;
	}

	public static boolean addNodeToLLEnd(Node<Integer> input) {
		if (input != null) {
			if (headNode == null) {
				headNode = input;
				tailNode = input;
			} else {
				tailNode.next = input;
			}
			return true;
		} else {
			return false;
		}
	}

	public static int getLength(Node<Integer> head) {
		Node<Integer> tmp = head;
		int length = 0;
		while (tmp != null) {
			length++;
			tmp = tmp.next;
		}
		return length;
	}

	public static Node<Integer> deleteNode(Node<Integer> head, int pos) {
		if (head == null) {
			return head;
		}
		if ((pos) >= getLength(head)) {
			return head;
		}
		// Taverse through LL to find desired pos
		Node<Integer> prevNode = null;
		Node<Integer> headBkp = head;
		int counter = 0;
		while (head != null) {
			if (pos == counter) {
				if (prevNode == null) {
					headBkp = head.next;
					break;
				} else {
					// Prev node's next should be current node's next
					prevNode.next = head.next;
					break;
				}

			}
			counter++;
			prevNode = head;
			head = head.next;
		}
		return headBkp;
	}
}
