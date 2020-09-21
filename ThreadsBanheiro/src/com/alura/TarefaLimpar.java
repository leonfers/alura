package com.alura;

public class TarefaLimpar implements Runnable {

    Banheiro banheiro;

    public TarefaLimpar(Banheiro banheiro) {
        this.banheiro = banheiro;
    }


    @Override
    public void run() {
        while (true){
            banheiro.limpa();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
