package br.com.ifpb.livraria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.ifpb.livraria.modelo.Autor;
import br.com.ifpb.livraria.modelo.Livro;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Autor assis = geraAutor("Machado de Assis");
		em.persist(assis);

		Autor amado = geraAutor("Jorge Amado");
		em.persist(amado);

		Autor coelho = geraAutor("Paulo Coelho");
		em.persist(coelho);

		Livro casmurro = geraLivro("978-8-52-504464-8", "Dom Casmurro",
				Calendar.getInstance(), 24.90, assis);
		Livro memorias = geraLivro("978-8-50-815415-9",
				"Memorias Postumas de Bras Cubas", Calendar.getInstance(), 19.90, assis);
		Livro quincas = geraLivro("978-8-50-804084-1", "Quincas Borba",
				Calendar.getInstance(), 16.90, assis);

		em.persist(casmurro);
		em.persist(memorias);
		em.persist(quincas);

		Livro alquemista = geraLivro("978-8-57-542758-3", "O Alquimista",
				Calendar.getInstance(), 19.90, coelho);
		Livro brida = geraLivro("978-8-50-567258-7", "Brida", Calendar.getInstance(),
				12.90, coelho);
		Livro valkirias = geraLivro("978-8-52-812458-8", "As Valkirias",
				Calendar.getInstance(), 29.90, coelho);
		Livro maao = geraLivro("978-8-51-892238-9", "O Diario de um Mago",
				Calendar.getInstance(), 9.90, coelho);

		em.persist(alquemista);
		em.persist(brida);
		em.persist(valkirias);
		em.persist(maao);

		Livro capitaes = geraLivro("978-8-50-831169-1", "Capitaes da Areia",
				Calendar.getInstance(), 6.90, amado);
		Livro flor = geraLivro("978-8-53-592569-9",
				"Dona Flor e Seus Dois Maridos", Calendar.getInstance(), 18.90, amado);

		em.persist(capitaes);
		em.persist(flor);

		em.getTransaction().commit();
		em.close();

	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private static Livro geraLivro(String isbn, String titulo, Calendar data,
			double preco, Autor autor) {
		Livro livro = new Livro();
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.setDataLancamento(data);
		livro.setPreco(preco);
		livro.adicionaAutor(autor);
		return livro;
	}

	@SuppressWarnings("unused")
	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
