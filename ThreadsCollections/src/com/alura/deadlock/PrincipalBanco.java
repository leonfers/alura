package com.alura.deadlock;

public class PrincipalBanco {

    public static void main(String[] args) {
        GerenciadordeTransacao tx = new GerenciadordeTransacao();
        PoolDeConnexao pool = new PoolDeConnexao();

        new Thread(new TarefaAcessaBanco(pool, tx)).start();
        new Thread(new TarefaAcessaBancoProcedimento(pool, tx)).start();

    }

}
