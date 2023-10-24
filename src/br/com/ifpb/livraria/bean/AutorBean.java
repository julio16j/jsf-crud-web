package br.com.ifpb.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.ifpb.livraria.dao.DAO;
import br.com.ifpb.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
	}
}
