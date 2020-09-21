package com.alura;

public class Main {

    public static void main(String[] args) {

        Banheiro banheiro = new Banheiro();

        Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "Joao");
        Thread convidado2 = new Thread(new TarefaNumero1(banheiro), "Marcos");
        Thread faxineiro = new Thread(new TarefaLimpar(banheiro), "faxineiro");
        faxineiro.setDaemon(true);
        faxineiro.setPriority(Thread.MAX_PRIORITY);
//        Thread convidado3 = new Thread(new TarefaNumero2(banheiro), "Pedro");
//        Thread convidado4 = new Thread(new TarefaNumero2(banheiro), "Paulo");

        convidado1.start();
        faxineiro.start();
        convidado2.start();
//        convidado3.start();
//        convidado4.start();
    }
}
