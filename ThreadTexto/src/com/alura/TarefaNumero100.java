package com.alura;

public class TarefaNumero100 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 1000 ; i++) {
            System.out.println(Thread.currentThread().getId()+" - "+ i);
        }


    }
}
