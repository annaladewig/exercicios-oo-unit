package entidades;

import java.util.ArrayList;
import java.util.List;

public class Revista {
	
	private int codigo;
	private String titulo;
	private int reciclagemProduzida;
	
	private List<Edicao> edicoes = new ArrayList<>();;

	public Revista() {	
	}
	
	public Revista(int codigo, String titulo) {
		this.codigo = codigo;
		this.titulo = titulo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getReciclagemProduzida() {
		return reciclagemProduzida;
	}

	public void setReciclagemProduzida(int reciclagemProduzida) {
		this.reciclagemProduzida = reciclagemProduzida;
	}

	public List<Edicao> getEdicoes() {
		return edicoes;
	}

	public void adicionarEdicao(Edicao ed) {
		edicoes.add(ed);
		reciclagemProduzida += ed.qtdeRecicladas();
		ed.setReciclagemAtualizada(true);
	}
	
	public void removerEdicao(Edicao ed) {
		edicoes.remove(ed);
		reciclagemProduzida -= ed.qtdeRecicladas();
		ed.setReciclagemAtualizada(false);
	}

	@Override
	public String toString() {
		return " Revista " + codigo 
				+ "\n Título: " + titulo;
	}
}
