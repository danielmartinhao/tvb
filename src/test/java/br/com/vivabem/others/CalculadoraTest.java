package br.com.vivabem.others;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.vivabem.tests.Calculadora;

@RunWith (Arquillian.class)
public class CalculadoraTest {

	private Calculadora calc = new Calculadora();
	private Calculadora calc2 = new Calculadora();
	private Calculadora calc3 = calc;
	private String teste1 = "Daniel";
	private String teste2 = "Daniel";
	private Integer teste3 = new Integer(1);
	private Integer teste4 = new Integer(1);
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class)
			.addClass(Calculadora.class)
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	@Test
	public void test() {
		assertTrue(calc.somar(2, 2).equals(Integer.valueOf(4)));
		assertTrue(calc.somar(2, 2) == Integer.valueOf(4));
		assertTrue(teste1.equals(teste2));
		assertTrue(teste3.equals(teste4));
		assertFalse(calc.equals(calc2));
		assertTrue(calc.equals(calc3));
	}

}
