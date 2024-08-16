package com.tictac.trial;

import java.util.Scanner;

public class ScannerInit {
	private static ScannerInit scannerInstance=null;
	Scanner sc;
	private ScannerInit() {
		sc=new Scanner(System.in);
	}
	
	public static synchronized ScannerInit getInstance() {
		if(scannerInstance==null) {
			scannerInstance=new ScannerInit();
			
		}
		return scannerInstance;
	}
	public Scanner getScanner() {
		return this.sc;
	}
}
