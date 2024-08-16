package com.cn.threads;

public class MyFirstThread {

    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task); // New State
        thread.start(); // Thread Started
        System.out.println("Inside main...");
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("Inside Run");
        go();
    }

    private void go() {
        System.out.println("Insde go...");
        more();
    }

    private void more() {
        System.out.println("Insde more...");
    }

}
