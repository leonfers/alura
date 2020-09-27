package br.com.alura.alurator.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorMetodo {

	private Object instancia;
	private Method metodo;
	private Map<String, Object> queryParams;
	private BiFunction<Method, InvocationTargetException, Object> tratamentoExcessao;

	public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> queryParams) {
		this.instancia = instancia;
		this.metodo = metodo;
		this.queryParams = queryParams;
	}
	
	public Object invoca() {
		try {
			List<Object> parametros = new ArrayList<>();
			Stream.of(this.metodo.getParameters()).forEach(args -> {
				parametros.add(queryParams.get(args.getName()));
			});
			return metodo.invoke(instancia, parametros.toArray());
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			if(tratamentoExcessao !=null){
				tratamentoExcessao.apply(metodo,e);
			}
			throw new RuntimeException("Erro dentro do método!", e);
		}
	}

	public ManipuladorMetodo comTratamentoDeExcecoes(BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao){
		this.tratamentoExcessao = tratamentoExcecao;
		return this;
	}

}
