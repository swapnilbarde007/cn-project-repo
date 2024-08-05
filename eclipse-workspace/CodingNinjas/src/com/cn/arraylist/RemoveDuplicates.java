package com.cn.arraylist;

import java.util.ArrayList;

public class RemoveDuplicates {
	
	public static ArrayList<Integer> removeConsecutiveDuplicates(int[] arr){
		ArrayList<Integer> result=new ArrayList<>();
		result.add(arr[0]);
		for(int i=1;i<arr.length;i++) {
			if(!(arr[i-1]==arr[i])) {
				result.add(arr[i]);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] inputArr= {10,20,20,30,30,30,10,60,40,40,90};
		System.out.println("Input Arr");
		for(int x:inputArr) {
			System.out.print(x+" ");
		}
		System.out.println();
		ArrayList<Integer> output=removeConsecutiveDuplicates(inputArr);
		System.out.println("Output values");
		for(int item:output) {
			System.out.print(item+" ");
		}
	}

}
