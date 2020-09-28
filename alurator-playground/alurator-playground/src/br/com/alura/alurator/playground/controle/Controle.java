package br.com.alura.alurator.playground.controle;

import java.util.Arrays;
import java.util.List;

@NomeTagXml("produto")
public class Controle {

	@NomeTagXml("lista")
	private List<String> lista = Arrays.asList("item 1", "item 2", "item 3");
	
	public List<String> getLista() {
		return lista;
	}

	public Controle()  {
	}

	public Controle(String s, String s2) {
		lista.add(s);
		lista.add(s2);
	}

	private Controle(String s, String s2,String s3) {
		lista.add(s);
		lista.add(s2);
	}
}
