package aula1;
import java.util.Scanner;


public class CriarMatriz {
	
	private int n;
	private int matriz[][];
	
	
	
	public void scan(Scanner teclado) {
		do {
			System.out.println("Digite um número inteiro: ");
			n = teclado.nextInt();
			if (n <= 0) {
				System.out.println("Digite um número maior que zero.");
			}
		} while(n <= 0);
	}
	
	public void gerarMatriz(Scanner teclado) {
		scan(teclado);
		int preenchimento = 0;
		matriz = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println("Digite um número para preencher a matriz: ");
				preenchimento = teclado.nextInt();
				matriz[i][j] = preenchimento;
			}
		}
		
	}
	
	public void diagonalPrincipal() {
		int soma = 0;
		
		for (int i = 0; i < n; i++) {
			soma += matriz[i][i];
		}
		
		System.out.println("Soma da Diagonal Principal: " + soma);
	}

	public void diagonalSecundaria() {
		int soma = 0;
		
		for (int i = 0; i < n; i++) {
			soma += matriz[i][n - i - 1];
		}
		
		System.out.println("Soma da Diagonal Secundária: " + soma);
	}
	
	public void multiplicarLinhas() {
        for (int i = 0; i < n; i++) {
            int multiplicacao = 1;

            for (int j = 0; j < n; j++) {
                multiplicacao *= matriz[i][j];
            }

            System.out.println("Multiplicação da " + (i + 1)+ "ª linha: " + multiplicacao);
        }
	}
	
	public void multiplicarColunas() {
        for (int j = 0; j < n; j++) {
            long multiplicacao = 1;

            for (int i = 0; i < n; i++) {
                multiplicacao *= matriz[i][j];
            }

            System.out.println("Multiplicação da " + (j + 1) + "ª coluna: " + multiplicacao);
        }
	}
	
	public static void main(String[] args) {
		CriarMatriz matriz = new CriarMatriz();
		Scanner teclado = new Scanner(System.in);
		matriz.gerarMatriz(teclado);
		matriz.diagonalPrincipal();
		matriz.diagonalSecundaria();
		matriz.multiplicarLinhas();
		matriz.multiplicarColunas();
		teclado.close();
	}

}
