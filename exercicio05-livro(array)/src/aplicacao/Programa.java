package aplicacao;

import entidades.Autor;
import entidades.Editora;
import entidades.Livro;

public class Programa {

	public static void main(String[] args) {

		Editora editora = new Editora(001, "Editora Real S/A", "Fulana", "9999-1234");

		Autor autores[] = new Autor[3];
		for (int i = 0; i < args.length; i++) {
			autores[i] = null;
		}
		autores[0] = new Autor(025, "Fulano", "fulando@email.com");
		autores[1] = new Autor(030, "Sicrano", "sicrano@email.com");
		autores[2] = new Autor(070, "Beltrano", "beltrano@email.com");

		Livro livro = new Livro(145, "Introdução a Orientação a Objetos", "12345", editora, autores);

		System.out.println(livro);
		System.out.print(" Autores: ");
		for (Autor autor : livro.getAutores()) {
			if (autor != null) {
				if (autor == autores[0]) {
					System.out.println(autor.getNome());
				} else {
					System.out.println("          " + autor.getNome());
				}
			}
		}
	}
}
