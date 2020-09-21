package com.alura.deadlock;

public class PoolDeConnexao {

    public String getConnexao (){
        System.out.println("Emprestando conexao");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "connection";
    }


}
