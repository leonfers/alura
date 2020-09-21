package com.alura;


public class Main {

    public static void main(String[] args) {
        Lista lista = new Lista();

        for (int i = 0; i < 100; i++) {
            new Thread(new TarefaAdicionarElemento(lista, i)).start();
        }

        new Thread(new TarefaImprimir(lista)).start();
    }
}
