package com.alura;

public class TarefaImprimir implements Runnable {
    Lista lista;

    public TarefaImprimir(Lista lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (lista) {
            if(!lista.estaCheia())
            try {
                lista.wait();
                for (int i = 0; i < lista.tamanho(); i++) {
                    System.out.println(i + " - " + lista.pegaElementos(i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
