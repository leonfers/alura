package br.com.alura.alurator;

import java.lang.reflect.InvocationTargetException;

public class Alurator {

	private final String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}

	public Object executa(String url) {
		String[] partesUrl = url.replaceFirst("/","")
		.split("/");

		String nomeControle = Character.toUpperCase(partesUrl[0].charAt(0)) + partesUrl[0].substring(1) + "Controller";

		try {
			Class<?> classeControle = Class.forName(pacoteBase + nomeControle);
			Object instanciaControle = classeControle.getDeclaredConstructor().newInstance();
			System.out.println(instanciaControle);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException  e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e){
			e.printStackTrace();
			System.out.println("Erro no construtor"+ e.getTargetException().toString());
		}
		return null;
	}
}
