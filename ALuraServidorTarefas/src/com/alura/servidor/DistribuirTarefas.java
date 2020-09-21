package com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistribuirTarefas implements Runnable {

    public final ServidorTarefas servidor;
    private final ExecutorService threadPool;
    Socket socket;

    public DistribuirTarefas(Socket socket, ServidorTarefas servidorTarefas, ExecutorService threadPool) {
        this.socket = socket;
        this.servidor = servidorTarefas;
        this.threadPool = threadPool;
    }


    @Override
    public void run() {
        try {
            System.out.println("------ Distribuindo tarefas|" + socket.getPort() + "-----------");
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (scanner.hasNextLine()) {
                String commando = scanner.nextLine();
                System.out.println("Comando Recebido");
                switch (commando){
                    case "c1":{
                        saidaCliente.println("Comando "+ commando +"recebido!");
                        ComandoC1 comandoC1 = new ComandoC1(saidaCliente);
                        threadPool.execute(comandoC1);
                        break;
                    }
                    case "c2":{
                        saidaCliente.println("Comando "+ commando +"recebido!");
                        ComandoC2 comandoC2 = new ComandoC2(saidaCliente);
                        threadPool.execute(comandoC2);
                        break;
                    }
                    case "fim":{
                        saidaCliente.println("Desligando o servidor!");
                        servidor.parar();
                        System.out.print(" fim");
                        break;
                    }
                    default:{
                        saidaCliente.println("Comando desconhecido!");
                        System.out.print(" C3");
                        break;
                    }
                }
            }

            saidaCliente.close();
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
