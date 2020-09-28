package br.com.alura.alurator;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.ManipuladorMetodo;
import br.com.alura.alurator.reflexao.Reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class Alurator {
	
	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao
		
		// Produto lista
		
		// produto -> roduto
		
		Request request = new Request(url);

		Map<String, Object> queryParams = request.getQueryParams();

		String nomeControle = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();

		
		Object retorno = new Reflexao()
			                .refleteClasse( pacoteBase + nomeControle )
			                .criaInstancia()
			                .getMetodo(nomeMetodo, queryParams)
							.comTratamentoDeExcecoes(this::tratamento)
			                .invoca();
			
		System.out.println(retorno);

		retorno = new ConversorXML().converte(retorno);

		return retorno;
	}

	public ManipuladorMetodo tratamento(Method method, InvocationTargetException e){
		throw new RuntimeException("Deu error no metodo "+method.getName()+" : "+ e.getTargetException().getMessage());
	}
}
