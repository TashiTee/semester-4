package sample;

import java.util.*;

class thread extends Thread {
    String hello = "";
    String[] words;
    thread(String hello){
        words = hello.split(" ");
        for(int i=words.length-1; i>=0; --i) {
            this.hello += words[i] + " ";
        }
    }
    public void run() {
        System.out.println(this.hello);
    }
}
public class ReverseThread {
    public static void main(String[] args) throws InterruptedException {
        thread[] t = new thread[50];
        for (int i=0; i<50; ++i) {
            t[i] = new thread("Hello from Thread <"+(i+1)+">!");
            t[i].start();
            t[i].join();
        }
    }
}
