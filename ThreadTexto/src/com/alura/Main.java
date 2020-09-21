package com.alura;

public class Main {

    public static void main(String[] args) {

        String nome = "a";

//        Thread thread1 = new Thread(new TarefaBuscaTextual("assinaturas1.txt", nome), "Assinatura 1");
//        Thread thread2 = new Thread(new TarefaBuscaTextual("assinaturas2.txt", nome), "Assinatura 2");
//        Thread thread3 = new Thread(new TarefaBuscaTextual("autores.txt", nome), "Assinatura 3");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();

        Thread thread = new Thread( new TarefaNumero100(),"1");
        Thread thread2 = new Thread( new TarefaNumero100(),"2");
        thread.start();
        thread2.start();
    }
}
