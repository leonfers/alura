package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerioC {

    public Object getInstancia(Class<?> tipoFonte) {
        Stream<Constructor<?>> construtores = Stream.of(tipoFonte.getDeclaredConstructors());

        Optional<Constructor<?>> construtoPadrao = construtores.filter(constructor ->
                constructor.getParameterCount() == 0).findFirst();

        try {
            if (construtoPadrao.isPresent()) {
                Object instancia = construtoPadrao.get().newInstance();
                return instancia;
            } else {
               Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];
                List<Object> params = new ArrayList<>();
                for (Parameter param:construtor.getParameters()){
                    Class<?> tipoParametro = param.getType();
                    params.add(getInstancia(tipoParametro));
                }
                return construtor.newInstance(params.toArray());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
