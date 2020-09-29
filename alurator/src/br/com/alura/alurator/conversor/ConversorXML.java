package br.com.alura.alurator.conversor;

import br.com.alura.alurator.conversor.anotacao.NomeTagXml;

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
                NomeTagXml anotacaoClasse = classeObjeto.getDeclaredAnnotation(NomeTagXml.class);
                xmlBuilder.append("<").append(anotacaoClasse != null ? anotacaoClasse.value() : classeObjeto.getSimpleName().toLowerCase()).append(">");
                for (Field atributo : classeObjeto.getDeclaredFields()) {
                    atributo.setAccessible(true);
                    NomeTagXml anotacaoAtributo = atributo.getDeclaredAnnotation(NomeTagXml.class);
                    xmlBuilder.append("<").append(anotacaoAtributo != null ? anotacaoAtributo.value() : atributo).append(">");
                    xmlBuilder.append(atributo.get(objeto));
                    xmlBuilder.append("</").append(anotacaoAtributo != null ? anotacaoAtributo.value() : atributo).append(">");
                }
                xmlBuilder.append("</" + (anotacaoClasse != null ? anotacaoClasse.value() : classeObjeto.getSimpleName().toLowerCase()) + ">");
                return xmlBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
