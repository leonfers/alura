package com.alura.deadlock;

public class TarefaAcessaBancoProcedimento implements Runnable {

    private final PoolDeConnexao pool;
    private final GerenciadordeTransacao tx;

    public TarefaAcessaBancoProcedimento(PoolDeConnexao pool, GerenciadordeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

    @Override
    public void run() {
        synchronized (pool){
            System.out.println("Pegando a conexao");
            pool.getConnexao();
            synchronized (tx){
                System.out.println("Gerenciando transacoes");
                tx.begin();
            }
        }
    }
}
