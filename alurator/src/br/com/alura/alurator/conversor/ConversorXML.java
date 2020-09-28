package br.com.alura.alurator.conversor;

import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {

    public String converte(Object objeto) {
        try {
            Class<?> classeObjeto = objeto.getClass();
            StringBuilder xmlBuilder = new StringBuilder();

            if (objeto instanceof Collection) {
                Collection<?> colecao = (Collection<?>) objeto;
                xmlBuilder.append("<lista>");
                for (Object o : colecao) {
                    String xml = converte(o);
                    xmlBuilder.append(xml);
                }
                xmlBuilder.append("</lista>");
                return xmlBuilder.toString();
            } else {
                String nomeClasse = classeObjeto.getSimpleName().toLowerCase();
                xmlBuilder.append("<" + nomeClasse + ">");
                for (Field atributo : classeObjeto.getDeclaredFields()) {
                    atributo.setAccessible(true);
                    String nomeAtributo = atributo.getName();
                    xmlBuilder.append("<" + nomeAtributo + ">");
                    xmlBuilder.append(atributo.get(objeto));
                    xmlBuilder.append("</" + nomeAtributo + ">");
                }
                xmlBuilder.append("</" + nomeClasse + ">");
                return xmlBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
