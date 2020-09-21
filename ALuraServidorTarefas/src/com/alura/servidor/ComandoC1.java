package com.alura.servidor;

import java.io.PrintStream;

public class ComandoC1 implements Runnable{

    private final PrintStream saidaCliente;

    public ComandoC1(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public void run() {
        System.out.printf("Processando comando c1");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.saidaCliente.println("C1 executado com sucesso!");
    }
}
