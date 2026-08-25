package aula1;
import java.util.Scanner;

public class Pilha {
	
	private int[] dados;
	private int topo;
	private int capacidade;
	
	public Pilha(int capacidade) {
		this.capacidade = capacidade;
		dados = new int [capacidade];
		topo = -1;
	}
	
	public void insere(Scanner teclado) {
		if(!cheia()) {
			topo += 1;
			dados[topo] = 10;
		} else {
			System.out.println("Pilha Cheia!");
		}

	}
	
	public boolean cheia() {
		if (topo == capacidade - 1) {
			return true;
		} else {
			return false;
		}
			
	}
	
	public boolean vazia() {
		if (topo == -1) {
			return true;
		} else {
			return false;
		}
			
	}
	
	public void imprimir() {
		for (int i = 0; i < capacidade; i++) {
			System.out.println(dados[i]);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
