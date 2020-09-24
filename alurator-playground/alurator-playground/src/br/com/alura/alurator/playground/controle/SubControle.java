package br.com.alura.alurator.playground.controle;

import java.util.Arrays;
import java.util.List;

public class SubControle extends Controle{
	private List<String> lista = Arrays.asList("item 1", "item 2", "item 3");

	private SubControle() {
	}

	private SubControle(String s) {
		lista.add(s);
	}
}
