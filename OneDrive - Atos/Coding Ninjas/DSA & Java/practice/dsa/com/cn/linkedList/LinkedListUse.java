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
		// LinkedListUse lu = new LinkedListUse();
		// lu.run();

		System.out.println("First LL");
		Node<Integer> firstLL = getInputForLL();
		// System.out.println("Second LL");
		// Node<Integer> secondLL = getInputForLL();
		// increment(head);
		// System.out.println("Length of linked list: " + getLength(headNode));
		/// printLinkedList(headNode);
		// System.out.println("Add Item to linked list(value)");
		// Scanner sc = new Scanner(System.in);
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
		// System.out.println("Enter last N nodes to cut: ");
		// int cutNode = sc.nextInt();
		// Node<Integer> outputNode = appendLastNToFirst(headNode, cutNode);
		// Node<Integer> uniqueLL = removeDuplicates(headNode);
		// System.out.println("List after removing duplicates");
		// printLinkedList(uniqueLL);
		// System.out.println("Reverse LL: ");
		// printReverse(headNode);
		// System.out.println("Is Palindrome? : " + isPalindrome(headNode));

		// Node<Integer> insertedNode = insertRecursive(headNode, 99, 3);
		// System.out.println("After Insert: ");
		// printLinkedList(headNode);
		// sc.close();
		// System.out.println("After deleting at idx 2");
		// Node<Integer> deletedNode = deleteNodeRec(headNode, 2);
		// Node<Integer> reversedLL = reverseRec(headNode);
		// Node<Integer> reversedI = reverse_I(headNode);
		// System.out.println("After reversed");
		// printLinkedList(reversedI);
		// Node<Integer> midPoint = midPoint(headNode);
		// System.out.println("Midpoint of given LL is: " + midPoint.data);
		Node<Integer> sorted = mergeSort(firstLL);
		System.out.println("Sorted LL");
		printLinkedList(sorted);
	}

	public static Node<Integer> mergeSort(Node<Integer> head) {
		if (head == null) {
			return head;
		}
		if (head.next == null) {
			return head;
		}

		Node<Integer> midPoint = midPoint(head);
		Node<Integer> secondPart = midPoint.next;
		midPoint.next = null;
		Node<Integer> sorted1 = mergeSort(head);
		Node<Integer> sorted2 = mergeSort(secondPart);
		return mergeTwoSorteds(sorted1, sorted2);

	}

	public static Node<Integer> mergeTwoSorteds(Node<Integer> head1, Node<Integer> head2) {
		Node<Integer> newHead = null;
		if (head1 == null && head2 != null) {
			return head2;
		} else if (head1 != null && head2 == null) {
			return head1;
		} else if (head1 == null && head2 == null) {
			return head1;
		}
		Node<Integer> mergedLL = null;
		int counter = 0;
		while (head1 != null && head2 != null) {
			if (counter == 0) {
				if (head1.data < head2.data) {
					mergedLL = new Node<Integer>(head1.data);
					newHead = mergedLL;
					head1 = head1.next;
				} else {
					mergedLL = new Node<Integer>(head2.data);
					newHead = mergedLL;
					head2 = head2.next;
				}
			} else {
				if (head1.data < head2.data) {
					mergedLL.next = new Node<Integer>(head1.data);
					mergedLL = mergedLL.next;
					head1 = head1.next;
				} else {
					mergedLL.next = new Node<Integer>(head2.data);
					mergedLL = mergedLL.next;
					head2 = head2.next;
				}
			}
			counter++;
		}
		if (head1 == null && head2 != null) {
			mergedLL.next = head2;
		}
		if (head1 != null && head2 == null) {
			mergedLL.next = head1;
		}
		return newHead;

	}

	public static Node<Integer> midPoint(Node<Integer> head) {
		if (head == null) {
			return head;
		}
		if (head.next == null) {
			return head;
		}
		if (head.next.next == null) {
			return head;
		}
		Node<Integer> slowPointer = head;
		Node<Integer> fastPointer = head;

		while (fastPointer.next != null && fastPointer.next.next != null) {
			fastPointer = fastPointer.next.next;
			if (fastPointer == null) {
				return slowPointer;
			}
			slowPointer = slowPointer.next;
		}
		return slowPointer;
	}

	public static Node<Integer> reverse_I(Node<Integer> head) {
		Node<Integer> prevNode = null;
		Node<Integer> currNode = head;
		Node<Integer> nextNode = head.next;

		while (nextNode != null) {
			currNode.next = prevNode;

			prevNode = currNode;
			currNode = nextNode;
			nextNode = currNode.next;
		}
		Node<Integer> newHead = currNode;
		newHead.next = prevNode;
		return newHead;
	}

	public static Node<Integer> reverseRec(Node<Integer> head) {
		if (head == null) {
			return head;
		}
		if (head.next == null) {
			return head;
		}
		Node<Integer> smallRev = reverseRec(head.next);
		head.next.next = head;
		head.next = null;
		return smallRev;
	}

	public static Node<Integer> deleteNodeRec(Node<Integer> head, int pos) {
		if (head == null) {
			return null;
		}
		if (pos == 0 && head.next != null) {
			Node<Integer> newHead = head.next;
			head.next = null;
			return newHead;
		}
		if (pos == 0 && head.next == null) {
			return null;
		}

		head.next = deleteNodeRec(head.next, pos - 1);
		return head;
	}

	public static Node<Integer> insertRecursive(Node<Integer> head, int item, int pos) {
		Node<Integer> newNode = new Node<Integer>(item);
		if (pos == 0) {
			newNode.next = head;
			return newNode;
		}
		head.next = insertRecursive(head.next, item, pos - 1);
		return head;
	}

	public static boolean isPalindrome1(Node<Integer> head) {
		if (head == null) {
			return true;
		}
		boolean isPalindrome = false;

		while (head != null) {
			if (head.next == null) {
				isPalindrome = true;
				break;
			}
			Node<Integer> tailData = findTail1(head);
			if (head.data == tailData.data) {
				isPalindrome = true;
			} else {
				isPalindrome = false;
			}
			head = head.next;
		}
		return isPalindrome;
	}

	public static Node<Integer> findTail1(Node<Integer> head) {
		Node<Integer> headBkp = head;
		Node<Integer> secondLastNode = null;
		Node<Integer> lastNode = null;
		while (head != null) {
			if (head.next != null) {
				if (head.next.next == null) {
					secondLastNode = head;
					lastNode = head.next;
				}
			}
			head = head.next;
		}
		secondLastNode.next = null;
		return lastNode;
	}

	public static boolean isPalindrome(Node<Integer> head) {
		// 1 2 3 2 1 = idx 2
		// 1 1 1 1 1 1 = idx 3
		int length = getLength(head);
		int mid = length / 2;
		int counter = 1;
		Node<Integer> midNode = null;
		Node<Integer> headBkp = head;
		Node<Integer> tail = null;
		while (head != null) {
			if (counter == mid) {
				midNode = head.next;
			}
			if (head.next == null) {
				tail = head;
			}
			counter++;
			head = head.next;
		}
		Node<Integer> reversedNode = reverseNode(midNode.next);
		System.out.println("Stop");
		return true;

	}

	public static Node<Integer> reverseNode(Node<Integer> head) {
		Node<Integer> currNode = null;
		Node<Integer> nextNode = null;
		if (head == null) {
			return head;
		}
		while (head != null) {
			currNode = head;
			nextNode = currNode.next;

			nextNode.next = head;
			currNode.next = null;
			head = head.next;
		}

		return nextNode;
	}

	public static void printReverse(Node<Integer> root) {
		if (root == null) {
			return;
		}
		printReverse(root.next);
		System.out.print(root.data + " ");
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

	public static Node<Integer> removeDuplicates(Node<Integer> head) {

		if (head == null) {
			return head;
		}
		// Traverse through LL
		Node<Integer> prevNode = null;
		Node<Integer> headBkp = head;

		while (head != null) {
			if (prevNode != null) {
				if (prevNode.data == head.data) {
					prevNode.next = head.next;
					// head = head.next;
				} else {
					prevNode = head;
				}

			} else {
				prevNode = head;
			}
			head = head.next;
		}
		return headBkp;
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

	// @Override
	// public void run() {
	// Runnable task = new LinkedListUse();
	// System.out.println("Thread created and run...");
	// Thread th = new Thread(task);
	// th.start();
	// // throw new UnsupportedOperationException("Unimplemented method 'run'");
	// }
}
