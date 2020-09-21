package com.alura.deadlock;

public class GerenciadordeTransacao {

    public void begin(){
        System.out.println("Comecar transacao");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
