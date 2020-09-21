package com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {

    Socket socket;

    public DistribuirTarefas(Socket socket) {
        this.socket = socket;
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
                        System.out.print(" C1");
                        break;
                    }
                    case "c2":{
                        saidaCliente.println("Comando "+ commando +"recebido!");
                        System.out.print(" C2");
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
