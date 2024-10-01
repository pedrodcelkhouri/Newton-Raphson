package method;

public class NR {

    // Definindo os parâmetros da equação
    public static final double k = 50.0;          // Constante elástica da mola (N/m)
    static final double a_nl = 5e-3;       // Constante não-linear da mola (N/m³)
    public static final double m = 10.0;          // Massa do objeto (kg)
    public static final double g = 9.81;          // Aceleração gravitacional (m/s²)
    public static final double tolerance = 1e-6;  // Precisão desejada

    // Função F(x) = kx + ax³ - mg
    public static double F(double x) {
        return k * x + a_nl * Math.pow(x, 3) - m * g;
    }

    // Derivada de F(x) em relação a x: F'(x) = k + 3ax²
    public static double F_derivative(double x) {
        return k + 3 * a_nl * Math.pow(x, 2);
    }

    // Método de Newton-Raphson para encontrar a raiz de F(x)
    public static double newtonRaphson(double x0) {
        double x = x0;
        int maxIterations = 1000;
        int iteration = 0;

        while (Math.abs(F(x)) > tolerance && iteration < maxIterations) {
            x = x - F(x) / F_derivative(x);  // Atualiza a aproximação
            iteration++;
        }

        if (iteration == maxIterations) {
            System.out.println("O método não convergiu após " + maxIterations + " iterações.");
        }

        return x;
    }

    // Função para encontrar o valor inicial x0 com base nas condições dadas
    public static double findInitialGuess(double a, double b) {
        if (F(a) * F_derivative(a) > 0) {
            return a;
        } else if (F(a) * F(b) < 0) {
            return (a + b) / 2;  // Se F(a) * F(b) < 0, escolhemos o ponto médio do intervalo escolhido
        } else {
            throw new IllegalArgumentException("Intervalo [a, b] inválido para convergência.");
        }
    }

    // Função para verificar se F(a) e F(b) tem sinais opostos, sugerindo que existe uma raiz entre eles
    public static boolean hasRoot(double a, double b) {
        return F(a) * F(b) < 0;
    }

    public static void main(String[] args) {
        // Definindo intervalo [a, b]
        double a = -2.0;  // Intervalo inferior
        double b = 2.0;   // Intervalo superior

        // Verificando se há uma raiz no intervalo fornecido
        if (!hasRoot(a, b)) {
            throw new IllegalArgumentException("Não há raiz no intervalo fornecido [a, b].");
        }

        // Encontrando a aproximação inicial x0
        double x0 = findInitialGuess(a, b);

        // Aplicando o método de Newton-Raphson
        double equilibriumPosition = newtonRaphson(x0);

        // Exibindo o resultado
        System.out.printf("A posição de equilíbrio é x = %.6f m%n", equilibriumPosition);
    }
}