package br.com.ifpb.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.ifpb.livraria.dao.DAO;
import br.com.ifpb.livraria.modelo.Autor;
import br.com.ifpb.livraria.modelo.Livro;

@ManagedBean
public class LivroBean {

	private Livro livro = new Livro();
	
	private Autor novoAutor = new Autor();

	public Livro getLivro() {
		return livro;
	}
	
	public Autor getNovoAutor () {
		return novoAutor;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (novoAutor == null) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}
		adicionAutor();
		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}
		
		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

	private void adicionAutor() {
		DAO<Autor> daoAutor = new DAO<Autor>(Autor.class);
		for (Autor autor : daoAutor.listaTodos()) {
			if (autor.getNome().equals(novoAutor.getNome()) ) {
				livro.adicionaAutor(autor);
			}
		}
	}

}
