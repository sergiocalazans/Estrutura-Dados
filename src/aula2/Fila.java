package aula2;

public class Fila {
	
	private int[] dados;
	private int primeiro;
	private int ultimo;
	private int capacidade;
	private int tamanho;

	public Fila(int tamanho) {
		this.tamanho = tamanho;
		this.dados = new int[tamanho];
		this.primeiro = 0;
		this.ultimo = 0;
		this.capacidade = 0;
	}
	
	public boolean vazia() {
		if (capacidade == 0) {
			System.out.println("Fila vazia!");
			return true;
		}
		return false;
		
	}
	
	public boolean cheia() {
		if (capacidade == tamanho) {
			System.out.println("Fila cheia!");
			return true;
		}
		return false;
		
	}
	
	public void insere(int elemento) {
		if(!cheia()) {
			capacidade++;
			if (ultimo == tamanho) {
				ultimo = 0;
			}
			dados[ultimo] = elemento;
			ultimo++;
			System.out.println("\nInserido: " + elemento);
		}	
	}
	
	public void remove() {
		if(!vazia()) {
			int valorRemovido = dados[primeiro];
			dados[primeiro] = 0;
			primeiro++;
			if (primeiro == tamanho) {
				primeiro = 0;
			}
			capacidade--;
			System.out.println("\nRemovido: " + valorRemovido);
		}	
	}
	
	public void imprime() {
		
		System.out.println("");
		
		if (!vazia()) {
			int indice = primeiro;
			int contador = 0;
			while (contador < capacidade) {
				System.out.println((contador+1) + "° Elemento da fila: " + dados[indice]);
				indice++;
				if (indice == tamanho) {
					indice = 0;
				}
				contador++;
			}
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		Fila fila = new Fila(5);
		fila.insere(2);
		fila.insere(6);
		fila.insere(9);
		fila.insere(12);
		fila.insere(14);
		fila.imprime();
		fila.remove();
		fila.imprime();
	}
}
