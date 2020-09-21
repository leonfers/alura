package com.alura;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TarefaBuscaTextual implements Runnable {

    String nome;
    String nomeArquivo;


    public TarefaBuscaTextual(String nomeArquivo, String nome) {
        this.nome = nome;
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new File(nomeArquivo));

            int count = 1;

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.toLowerCase().contains(nome.toLowerCase())) {
                    System.out.println(nomeArquivo + "-" + count + "-" + linha);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
