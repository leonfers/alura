package com.alura.servidor.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12345);
        System.out.println(" ---------- Conexao Estabelecida ---------");


        Thread threadEnviaComando = new Thread(() -> {
            try {
                System.out.println("Enviando para servidor!");
                PrintStream saida = new PrintStream(socket.getOutputStream());
                Scanner teclado = new Scanner(System.in);
                while (teclado.hasNextLine()) {
                    String linha = teclado.nextLine();
                    if (linha.trim().toLowerCase().equals("")) {
                        break;
                    }
                    saida.println(linha);
                }
                saida.close();
                teclado.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadRecebeResposta = new Thread(() -> {
            try {
                System.out.println("Recebendo dados do servidor!");
                Scanner entrada = new Scanner(socket.getInputStream());
                while (entrada.hasNextLine()) {
                    String linha = entrada.nextLine();
                    System.out.println(linha);
                }
                entrada.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        threadEnviaComando.start();
        threadRecebeResposta.start();
        threadEnviaComando.join();

        System.out.println("Encerrando cliente");
        socket.close();


    }
}
