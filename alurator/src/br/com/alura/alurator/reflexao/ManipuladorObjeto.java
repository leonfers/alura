package br.com.alura.alurator.reflexao;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorObjeto {

	private Object instancia;

	public ManipuladorObjeto(Object instancia) {
		this.instancia = instancia;
	}

	public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> queryParams) {
		
		try {
			Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());

			Method metodoSelecionado = metodos.filter(metodo -> metodo.getName().equals(nomeMetodo)
			&& metodo.getParameterCount() == queryParams.values().size()
			&& Stream.of(metodo.getParameters()).allMatch(arg ->
					queryParams.containsKey(arg.getName())
							&& queryParams.get(arg.getName()).getClass().equals(arg.getType())
					))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Metodo nao encontrado"));

			return new ManipuladorMetodo(instancia, metodoSelecionado, queryParams);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
