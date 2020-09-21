package com.alura;

import java.util.List;

public class TarefaAdicionarElemento implements Runnable {

    private Lista lista;
    private int numero;

    public TarefaAdicionarElemento(Lista lista, int numero) {
        this.lista = lista;
        this.numero = numero;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lista.adicionaElementos("Thread " + numero + " - " + i);
        }
    }
}
