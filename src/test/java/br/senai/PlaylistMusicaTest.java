package br.senai;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaylistMusicaTest {

    PlaylistMusica playlistMusica;

    @BeforeEach
    public void setUp() {
        playlistMusica = new PlaylistMusica("nome");
    }

    @Test
    public void testGetNome() {
        Assert.assertEquals("nome", playlistMusica.getNome());
    }

    @Test
    public void testSetNome() {
        Assert.assertEquals("nome", playlistMusica.getNome());
        playlistMusica.setNome("nomeNovo");
        Assert.assertEquals("nomeNovo", playlistMusica.getNome());
    }

    @Test
    public void testGetMusicas() {
        Assert.assertEquals(0, playlistMusica.getMusicas().size());
        playlistMusica.adicionarMusica(new Musica("titulo", "artista", 2000));
        Assert.assertEquals(1, playlistMusica.getMusicas().size());
        Assert.assertNotNull(playlistMusica.getMusicas());
        playlistMusica.adicionarMusica(new Musica("titulo", "artista", 2000));
        Assert.assertEquals(2, playlistMusica.getMusicas().size());
    }

    @Test
    public void testAdicionarmusica() {
        Assert.assertFalse(playlistMusica.adicionarMusica(null));
        Assert.assertTrue(playlistMusica.adicionarMusica(new Musica("titulo", "artista", 2000)));
    }

    @Test
    public void testRemovermusica() {
        Musica musica = new Musica("titulo", "autor", 2000);
        playlistMusica.adicionarMusica(musica);
        Assert.assertEquals(1, playlistMusica.getMusicas().size());
        Assert.assertEquals(musica, playlistMusica.getMusicas().get(0));
        playlistMusica.removerMusica(musica);
        Assert.assertEquals(0, playlistMusica.getMusicas().size());
    }

    @Test
    public void testBuscarmusicaPorTitulo() {
        Musica musica1 = new Musica("titulo1", "autor", 2000);
        Musica musica2 = new Musica("titulo2", "autor", 2000);
        playlistMusica.adicionarMusica(musica1);
        playlistMusica.adicionarMusica(musica2);
        Assert.assertEquals(musica1, playlistMusica.buscarMusicaPorTitulo("titulo1"));
        Assert.assertEquals(musica2, playlistMusica.buscarMusicaPorTitulo("titulo2"));
    }

    @Test
    public void testBuscarMusicasPorArtista() {
        Musica musica1 = new Musica("titulo", "artista1", 2000);
        Musica musica2 = new Musica("titulo", "artista2", 2000);
        playlistMusica.adicionarMusica(musica1);
        playlistMusica.adicionarMusica(musica2);
        Assert.assertEquals(musica1, playlistMusica.buscarMusicasPorArtista("artista1").get(0));
        Assert.assertEquals(1, playlistMusica.buscarMusicasPorArtista("artista1").size());
        Musica musica3 = new Musica("titulo", "artista1", 2000);
        playlistMusica.adicionarMusica(musica3);
        Assert.assertEquals(musica3, playlistMusica.buscarMusicasPorArtista("artista1").get(1));
        Assert.assertEquals(2, playlistMusica.buscarMusicasPorArtista("artista1").size());
    }

    @Test
    public void testOrdenarPorTitulo() {
        Musica musica2 = new Musica("titulo2", "artista2", 2000);
        Musica musica1 = new Musica("titulo1", "artista1", 2000);
        playlistMusica.adicionarMusica(musica2);
        playlistMusica.adicionarMusica(musica1);
        Assert.assertEquals("titulo2", playlistMusica.getMusicas().get(0).getTitulo());
        playlistMusica.ordenarPorTitulo();
        Assert.assertEquals("titulo1", playlistMusica.getMusicas().get(0).getTitulo());
        Musica musica3 = new Musica("a", "artista2", 2000);
        Musica musica4 = new Musica("b", "artista1", 2000);
        playlistMusica.adicionarMusica(musica3);
        playlistMusica.adicionarMusica(musica4);
        playlistMusica.ordenarPorTitulo();
        Assert.assertEquals("a", playlistMusica.getMusicas().get(0).getTitulo());
        Assert.assertEquals("titulo2", playlistMusica.getMusicas().get(3).getTitulo());
    }

    @Test
    public void TestOrdenarPorArtista() {
        Musica musica2 = new Musica("titulo", "artista2", 2000);
        Musica musica1 = new Musica("titulo", "artista1", 2000);
        playlistMusica.adicionarMusica(musica2);
        playlistMusica.adicionarMusica(musica1);
        Assert.assertEquals("artista2", playlistMusica.getMusicas().get(0).getArtista());
        playlistMusica.ordenarPorArtista();
        Assert.assertEquals("artista1", playlistMusica.getMusicas().get(0).getArtista());
        Musica musica3 = new Musica("titulo", "a", 2000);
        Musica musica4 = new Musica("titulo", "aa", 2000);
        playlistMusica.adicionarMusica(musica3);
        playlistMusica.adicionarMusica(musica4);
        playlistMusica.ordenarPorArtista();
        Assert.assertEquals("a", playlistMusica.getMusicas().get(0).getArtista());
        Assert.assertEquals("artista2", playlistMusica.getMusicas().get(3).getArtista());
    }

    @Test
    public void testGetQuantidadeMusicas() {
        Musica musica1 = new Musica("titulo1", "autor", 2000);
        Musica musica2 = new Musica("titulo2", "autor", 2000);
        playlistMusica.adicionarMusica(musica1);
        Assert.assertEquals(1, playlistMusica.getQuantidadeMusicas());
        playlistMusica.adicionarMusica(musica2);
        Assert.assertEquals(2, playlistMusica.getQuantidadeMusicas());
        playlistMusica.removerMusica(musica1);
        Assert.assertEquals(1, playlistMusica.getQuantidadeMusicas());
    }

}
