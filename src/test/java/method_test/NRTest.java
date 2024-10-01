package method_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import method.NR;
import org.junit.Test;

public class NRTest{

    // Tolerância usada para os testes
    static final double DELTA = 1e-6;

    @Test
    public void testFunctionF() {
        // Teste básico para a função F(x)
        // Em x = 0, F(x) deve ser aproximadamente -mg, pois o termo linear e cúbico são 0
        double x = 0.0;
        double expected = -NR.m * NR.g;
        assertEquals(expected, NR.F(x), DELTA);
    }

    @Test
    public void testFunctionFPrime() {
        // Testando a derivada de F em um ponto simples, por exemplo, em x = 0
        double x = 0.0;
        double expected = NR.k;  // Derivada de F em x=0 é apenas k, pois o termo cúbico desaparece
        assertEquals(expected, NR.F_derivative(x), DELTA);
    }

    @Test
    public void testNewtonRaphsonConverges() {
        // Teste para verificar se o método Newton-Raphson converge para uma raiz
        double x0 = 1.0;  // Aproximação inicial razoável
        double result = NR.newtonRaphson(x0);

        // O valor da função F(result) deve ser próximo de zero se a raiz foi encontrada corretamente
        assertTrue(Math.abs(NR.F(result)) < NR.tolerance);
    }

    @Test
    public void testInitialGuess() {
        // Verificando se o método findInitialGuess retorna um valor dentro do intervalo adequado
        double a = -2.0;
        double b = 2.0;
        double initialGuess = NR.findInitialGuess(a, b);

        // O valor inicial x0 deve estar no intervalo [a, b]
        assertTrue(initialGuess >= a && initialGuess <= b);
    }

    @Test
    public void testRootExistsInInterval() {
        // Verificando se a função hasRoot detecta corretamente a existência de uma raiz no intervalo
        double a = -2.0;
        double b = 2.0;

        // Deve retornar verdadeiro, pois há uma raiz entre -2 e 2
        assertTrue(NR.hasRoot(a, b));
    }
}
