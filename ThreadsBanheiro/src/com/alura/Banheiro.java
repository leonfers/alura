package com.alura;

public class Banheiro {

    private boolean ehSujo = true;

    public void fazNumero1(){
        System.out.println(Thread.currentThread().getName() + " batendo na porta!");
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " entrando no banheiro!");

            while (ehSujo){
                esperar();
            }

            System.out.println(Thread.currentThread().getName() + "Fazendo numero 01!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "Dando descarga!");
            System.out.println(Thread.currentThread().getName() + "Lavando a mao!");
            System.out.println(Thread.currentThread().getName() + "Saindo do banheiro!");
            this.ehSujo = true;

        };
    }

    public void fazNumero2(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " batendo na porta!");
        while (ehSujo){
            esperar();
        };
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " entrando no banheiro!");
            System.out.println(Thread.currentThread().getName() + "Fazendo numero 02!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "Dando descarga!");
            System.out.println(Thread.currentThread().getName() + "Lavando a mao!");
            System.out.println(Thread.currentThread().getName() + "Saindo do banheiro!");
            this.ehSujo = true;
        }
    }

    private void esperar() {
        System.out.println(Thread.currentThread().getName() + " banheiro sujo!");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void limpa(){
        System.out.println(Thread.currentThread().getName() + " batendo na porta!");
        if(!this.ehSujo){
            System.out.println(Thread.currentThread().getName() + " viu que o banheiro esta limpo!");
            return;
        }
        synchronized (this) {
            this.ehSujo = false;
            System.out.println(Thread.currentThread().getName() + " limpando o banheiro!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + " banheiro limpo!");
        }
    }

}

