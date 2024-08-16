package com.cn.threads;

public class MyFirstThread2 extends Thread {

    public void run() {
        System.out.println("Inside Run...");
        go();
    }

    private void go() {
        System.out.println("Insde go...");
        more();
    }

    private void more() {
        System.out.println("Insde more...");
    }

    public static void main(String[] args) {
        Thread myThread = new MyFirstThread2();
        myThread.start();
        System.out.println("Inside main...");

    }
}
