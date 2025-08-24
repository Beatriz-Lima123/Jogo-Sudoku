import java.util.Random;
import java.util.Scanner;

public class Sudoku {

    // Códigos ANSI para cores
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";

    public static void main(String[] args) {
        Celula[][] tabuleiro = new Celula[9][9];
        boolean[][] erros = new boolean[9][9]; // marca onde o jogador errou

        // Solução completa
        int[][] solucao = {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };

        int fixasPorQuadrante = 2;
        inicializarTabuleiroAleatorio(tabuleiro, solucao, fixasPorQuadrante);

        Scanner scanner = new Scanner(System.in);

        while (!verificarCompleto(tabuleiro)) {
            imprimirTabuleiro(tabuleiro, erros);

            System.out.println("Digite uma jogada:");
            System.out.print("Linha (0-8): ");
            int linha = scanner.nextInt();
            System.out.print("Coluna (0-8): ");
            int coluna = scanner.nextInt();
            System.out.print("Valor (1-9): ");
            int valor = scanner.nextInt();

            if (tabuleiro[linha][coluna].isFixo()) {
                System.out.println("Essa célula é fixa. Tente outra.");
            } else if (valor == solucao[linha][coluna]) {
                tabuleiro[linha][coluna].setValor(valor);
                erros[linha][coluna] = false;
                System.out.println("Acertou!");
            } else {
                tabuleiro[linha][coluna].setValor(valor);
                erros[linha][coluna] = true;
                System.out.println("Errado!");
            }

            System.out.println();
        }

        System.out.println("Parabéns!Sudoku completo corretamente:");
        imprimirTabuleiro(tabuleiro, erros);
        scanner.close();
    }

    public static void inicializarTabuleiroAleatorio(Celula[][] tabuleiro, int[][] solucao, int fixasPorQuadrante) {
        Random random = new Random();

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                tabuleiro[i][j] = new Celula(0, false);

        for (int blocoLinha = 0; blocoLinha < 3; blocoLinha++) {
            for (int blocoColuna = 0; blocoColuna < 3; blocoColuna++) {
                int colocadas = 0;
                while (colocadas < fixasPorQuadrante) {
                    int linhaAleatoria = blocoLinha * 3 + random.nextInt(3);
                    int colunaAleatoria = blocoColuna * 3 + random.nextInt(3);

                    if (!tabuleiro[linhaAleatoria][colunaAleatoria].isFixo()) {
                        int valor = solucao[linhaAleatoria][colunaAleatoria];
                        tabuleiro[linhaAleatoria][colunaAleatoria] = new Celula(valor, true);
                        colocadas++;
                    }
                }
            }
        }
    }

    public static void imprimirTabuleiro(Celula[][] tabuleiro, boolean[][] erros) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("------+-------+------");

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");

                int valor = tabuleiro[i][j].getValor();
                if (tabuleiro[i][j].isFixo()) {
                    System.out.print(AZUL + valor + RESET + " "); // célula fixa azul
                } else if (valor == 0) {
                    System.out.print(". "); // vazia
                } else if (erros[i][j]) {
                    System.out.print(VERMELHO + valor + RESET + " "); // erro vermelho
                } else {
                    System.out.print(VERDE + valor + RESET + " "); // correto verde
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean verificarCompleto(Celula[][] tabuleiro) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (!tabuleiro[i][j].isFixo() && tabuleiro[i][j].getValor() == 0)
                    return false;
        return true;
    }
}


