package br.com.alura.alurator.playground.controle;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Deprecated
@Documented
public @interface NomeTagXml {

    public  String value();

}
