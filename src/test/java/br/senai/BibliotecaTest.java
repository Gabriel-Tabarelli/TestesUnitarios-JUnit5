package br.senai;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BibliotecaTest {

    private Biblioteca biblioteca;

    @BeforeEach
    public void setUp() {
        biblioteca = new Biblioteca("biblioteca");
    }

    @Test
    public void testGetNome() {
        Assert.assertEquals("biblioteca", biblioteca.getNome());
    }

    @Test
    public void testSetNome() {
        biblioteca.setNome("nome");
        Assert.assertEquals("nome", biblioteca.getNome());
    }

    @Test
    public void testGetLivro() {
        Assert.assertEquals(0, biblioteca.getLivros().size());
        biblioteca.adicionarLivro(new Livro("titulo", "autor", "genero", 2000));
        Assert.assertEquals(1, biblioteca.getLivros().size());
        Assert.assertNotNull(biblioteca.getLivros());
        biblioteca.adicionarLivro(new Livro("titulo", "autor", "genero", 2000));
        Assert.assertEquals(2, biblioteca.getLivros().size());
    }

    @Test
    public void testAdicionarLivro() {
        Assert.assertFalse(biblioteca.adicionarLivro(null));
        Assert.assertTrue(biblioteca.adicionarLivro(new Livro("titulo", "autor", "genero", 2000)));
    }

    @Test
    public void testRemoverLivro() {
        // Configuração do teste
        Livro livro = new Livro("titulo", "autor", "genero", 2000);
        biblioteca.adicionarLivro(livro);
        // Executa a lógica do teste
        biblioteca.removerLivro(livro);
        // Verifica se o resultado esperado foi alcançado
        Assert.assertEquals(0, biblioteca.getLivros().size());
    }

    @Test
    public void testBuscarLivrosPorAutor() {
        Livro livro1 = new Livro("titulo", "autor1", "genero", 2000);
        Livro livro2 = new Livro("titulo", "autor2", "genero", 2000);
        Livro livro3 = new Livro("titulo", "autor1", "genero", 2000);
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);
        List<Livro> livrosDoAutor1 = biblioteca.buscarLivrosPorAutor("autor1");
        Assert.assertEquals("Deveriam ter sido encontrados 2 livros do autor1",
                2, livrosDoAutor1.size());
        Assert.assertTrue("O livro1 deveria estar na lista de livros do autor1",
                livrosDoAutor1.contains(livro1));
        Assert.assertTrue("O livro3 deveria estar na lista de livros do autor1",
                livrosDoAutor1.contains(livro3));
    }

    @Test
    public void testBuscarLivrosPorGenero() {
        Livro livro1 = new Livro("titulo", "autor", "genero1", 2000);
        Livro livro2 = new Livro("titulo", "autor", "genero2", 2000);
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        Assert.assertEquals(livro1, biblioteca.buscarLivrosPorGenero("genero1").get(0));
        Assert.assertEquals(1, biblioteca.buscarLivrosPorGenero("genero1").size());
        Livro livro3 = new Livro("titulo", "autor", "genero1", 2000);
        biblioteca.adicionarLivro(livro3);
        Assert.assertEquals(livro3, biblioteca.buscarLivrosPorGenero("genero1").get(1));
        Assert.assertEquals(2, biblioteca.buscarLivrosPorGenero("genero1").size());
    }

    @Test
    public void testBuscarLivroPorTitulo() {
        Livro livro1 = new Livro("titulo1", "autor", "genero", 2000);
        Livro livro2 = new Livro("titulo2", "autor", "genero", 2000);
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        Assert.assertEquals(livro1, biblioteca.buscarLivroPorTitulo("titulo1"));
        Assert.assertEquals(livro2, biblioteca.buscarLivroPorTitulo("titulo2"));
    }

    @Test
    public void testGetQuantidadeLivros() {
        Livro livro1 = new Livro("titulo1", "autor", "genero", 2000);
        Livro livro2 = new Livro("titulo2", "autor", "genero", 2000);
        biblioteca.adicionarLivro(livro1);
        Assert.assertEquals(1, biblioteca.getQuantidadeLivros());
        biblioteca.adicionarLivro(livro2);
        Assert.assertEquals(2, biblioteca.getQuantidadeLivros());
        biblioteca.removerLivro(livro1);
        Assert.assertEquals(1, biblioteca.getQuantidadeLivros());
    }

}
