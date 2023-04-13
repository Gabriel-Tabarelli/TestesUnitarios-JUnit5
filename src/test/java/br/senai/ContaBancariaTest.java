package br.senai;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaBancariaTest {

    private ContaBancaria contaBancaria;

    @BeforeEach
    public void setUp() {
        contaBancaria = new ContaBancaria("2", "titular");
    }

    @Test
    public void testGetNumeroConta() {
        Assert.assertEquals(2, Integer.parseInt(contaBancaria.getNumeroConta()));
    }

    @Test
    public void testGetTitular() {
        Assert.assertEquals("titular", contaBancaria.getTitular());
    }

    @Test
    public void testGetSaldo() {
        contaBancaria.depositar(500);
        Assert.assertEquals(500.0, contaBancaria.getSaldo());
    }

    @Test
    public void testGetTaxaJuros() {
        Assert.assertEquals(0.0, contaBancaria.getTaxaJuros());
    }

    @Test
    public void testSetTaxaJuros() {
        contaBancaria.setTaxaJuros(0.02);
        Assert.assertEquals(0.02, contaBancaria.getTaxaJuros(), 0.001);
        contaBancaria.setTaxaJuros(0);
        Assert.assertEquals(0.0, contaBancaria.getTaxaJuros());
    }

    @Test
    public void testDepositar() {
        Assert.assertFalse(contaBancaria.depositar(-200));
        Assert.assertTrue(contaBancaria.depositar(100));
        Assert.assertEquals(100.0, contaBancaria.getSaldo());
    }

    @Test
    public void testSacar() {
        Assert.assertFalse(contaBancaria.sacar(100));
        contaBancaria.depositar(100);
        Assert.assertFalse(contaBancaria.sacar(0));
        Assert.assertTrue(contaBancaria.sacar(50));
        Assert.assertEquals(50.0, contaBancaria.getSaldo());
    }

    @Test
    public void testTransferir() {
        ContaBancaria contaBancaria2 = new ContaBancaria("3", "titular2");
        contaBancaria.depositar(100);
        Assert.assertTrue(contaBancaria.transferir(contaBancaria2, 50));
        Assert.assertEquals(50.0, contaBancaria2.getSaldo());
    }

    @Test
    public void testAplicarJuros() {
        contaBancaria.depositar(100);
        contaBancaria.setTaxaJuros(1);
        contaBancaria.aplicarJuros();
        Assert.assertEquals(200.0, contaBancaria.getSaldo());
        contaBancaria.setTaxaJuros(0);
        contaBancaria.aplicarJuros();
        Assert.assertEquals(200.0, contaBancaria.getSaldo(), 0.001);
    }

    @Test
    public void testAlterarTitular() {
        Assert.assertFalse(contaBancaria.alterarTitular(null));
        Assert.assertFalse(contaBancaria.alterarTitular("       "));
        Assert.assertFalse(contaBancaria.alterarTitular(" "));
        Assert.assertTrue(contaBancaria.alterarTitular("Pedro"));
        Assert.assertEquals("Pedro", contaBancaria.getTitular());
    }
}
