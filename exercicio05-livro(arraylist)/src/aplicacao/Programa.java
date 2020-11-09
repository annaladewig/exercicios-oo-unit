package aplicacao;

import java.util.ArrayList;
import java.util.List;

import entidades.Autor;
import entidades.Editora;
import entidades.Livro;

public class Programa {

	public static void main(String[] args) {

		Editora editora = new Editora(001, "Editora Real S/A", "Fulana", "9999-1234");

		List<Autor> listaDeAutores = new ArrayList<>();

		listaDeAutores.add(new Autor(025, "Fulano", "fulando@email.com"));
		listaDeAutores.add(new Autor(030, "Sicrano", "sicrano@email.com"));
		listaDeAutores.add(new Autor(070, "Beltrano", "beltrano@email.com"));

		Livro livro = new Livro(145, "Introdução a Orientação a Objetos", "12345", editora, listaDeAutores);

		System.out.println(livro);
		System.out.print(" Autores: ");
		for (Autor autor : livro.getAutores()) {
			if (autor == listaDeAutores.get(0)) {
				System.out.println(autor.getNome());
			} else {
				System.out.println("          " + autor.getNome());
			}
		}
	}
}
