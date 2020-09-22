package com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

    private final ServerSocket socket;
    private final ExecutorService threadPool;
    private AtomicBoolean estaRodando;
    private BlockingQueue<String> filaComandos;

    public ServidorTarefas() throws IOException {
        System.out.println("---- Iniciando Servidor --------");
        socket = new ServerSocket(12345);
        threadPool =  Executors.newCachedThreadPool();
        estaRodando = new AtomicBoolean(true);
        this.filaComandos = new ArrayBlockingQueue<>(2);
        iniciarConsumidores();
    }

    private void iniciarConsumidores() {
        for (int qConsumidores = 0; qConsumidores < 2; qConsumidores++) {
            TarefaConsumir consumir = new TarefaConsumir(filaComandos);
            threadPool.execute(consumir);
        }
    }

    public void rodar() throws IOException {
        while (this.estaRodando.get()){
            try{
                Socket socket = this.socket.accept();
                System.out.println("-------- Aceitando novo cliente|"+socket.getPort()+" -----------");
                DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket, this, threadPool, filaComandos);
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
