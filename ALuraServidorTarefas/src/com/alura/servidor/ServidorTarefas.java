package com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

    private final ServerSocket socket;
    private final ExecutorService threadPool;
    private AtomicBoolean estaRodando;

    public ServidorTarefas() throws IOException {
        System.out.println("---- Iniciando Servidor --------");
        socket = new ServerSocket(12345);
        threadPool =  Executors.newFixedThreadPool(4);
        estaRodando = new AtomicBoolean(true);
    }

    public void rodar() throws IOException {
        while (this.estaRodando.get()){
            try{
                Socket socket = this.socket.accept();
                System.out.println("-------- Aceitando novo cliente|"+socket.getPort()+" -----------");
                DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket, this, threadPool);
                threadPool.execute(distribuirTarefas);
            }catch (SocketException ignored){
                System.out.printf(""+this.estaRodando);
            }

        }
    }

    public void parar() throws IOException {
        estaRodando.set(false);
        socket.close();
        threadPool.shutdown();
    }

    public static void main(String[] args) throws Exception {
        ServidorTarefas servidor = new ServidorTarefas();
        servidor.rodar();
        servidor.parar();
    }


}
