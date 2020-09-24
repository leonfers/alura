package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.SubControle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TesteIntanciaObjetoCorretamente {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        Class<SubControle> subControleClass = SubControle.class;

        Class<?> subControleClass2 = Class.forName("br.com.alura.alurator.playground.controle.SubControle");

        Class<?> controleClass1 = Class.forName("br.com.alura.alurator.playground.controle.Controle");

        try {
            controleClass1.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            e.getTargetException().printStackTrace();
        }

//        Constructor<SubControle> constructorSubControle = subControleClass.getDeclaredConstructor();
//
//        System.out.println(constructorSubControle);
//
//        constructorSubControle.setAccessible(true);
//
//        Object subControle = constructorSubControle.newInstance();
//
//        System.out.println(subControle);

    }
}
