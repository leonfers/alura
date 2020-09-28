package br.com.alura.alurator.conversor.anotacao;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface NomeTagXml {

    public  String value();

}
