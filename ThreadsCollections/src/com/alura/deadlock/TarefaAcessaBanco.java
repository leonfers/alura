package com.alura.deadlock;

public class TarefaAcessaBanco implements Runnable {

    private PoolDeConnexao pool;
    private GerenciadordeTransacao tx;

    public TarefaAcessaBanco(PoolDeConnexao pool, GerenciadordeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

    @Override
    public void run() {
        synchronized (tx){
            System.out.println("Pegando a conexao");
            pool.getConnexao();
            synchronized (pool){
                System.out.println("Gerenciando transacoes");
                tx.begin();
            }
        }
    }
}
