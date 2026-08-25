package aula3;

public class ListaCircular {

	class No {
		int dado;
		No proximo;

		No(int elemento) {
			this.dado = elemento;
			this.proximo = null;
		}
	}

	No inicio = null;

	public void insereElemento(int elemento) {
		No novoNo = new No(elemento);
		if (inicio == null) {
			inicio = novoNo;
			inicio.proximo = novoNo;
		} else {
			No atual = inicio;
			while (atual.proximo != inicio) {
				atual = atual.proximo;
			}

			atual.proximo = novoNo;
			novoNo.proximo = inicio;
		}
		System.out.println("\nNovo elemento: " + elemento);
	}

	public void apagarLista() {
		if (vazia()) {
			return;
		}
		inicio = null;
		System.out.println("Lista apagada!");
	}

	public void removeInicio() {
		if (vazia()) {
			return;
		}
		if (inicio.proximo == inicio) {
			inicio = null;
			return;
		}

		No ultimo = inicio;

		while (ultimo.proximo != inicio) {
			ultimo = ultimo.proximo;
		}

		inicio = inicio.proximo;
		ultimo.proximo = inicio;
	}

	public void removeUltimo() {
		if (vazia()) {
			return;
		}

		if (inicio.proximo == inicio) {
			inicio = null;
			return;
		}

		No ultimo = inicio;
		No anterior = inicio;

		while (ultimo.proximo != inicio) {
			anterior = ultimo;
			ultimo = ultimo.proximo;
		}

		ultimo = null;
		anterior.proximo = inicio;
	}

	public void remove(int elemento) {

		if (vazia()) {
			return;
		}

		if (inicio.dado == elemento) {
			removeInicio();
			return;
		}

		No atual = inicio.proximo;
		No anterior = inicio;

		while (atual != inicio) {
			if (atual.dado == elemento) {
				System.out.println("\nElemento removido: " + atual.dado);
				anterior.proximo = atual.proximo;
				return;
			}
			anterior = atual;
			atual = atual.proximo;
		}

		System.out.println("\nNão há o elemento: " + elemento + " na lista.");
	}

	public boolean vazia() {
		if (inicio == null) {
			System.out.println("\nLista vazia!");
			return true;
		} else {
			return false;
		}
	}

	public void exibeLista() {
		if (vazia()) {
			return;
		}
		No atual = inicio;
		System.out.print("\nLista: ");
		do {
			System.out.print(atual.dado + " ");
			atual = atual.proximo;
		} while (atual != inicio);
		System.out.println("\n");
	}

	public void verificarLista() {
		if (vazia()) {
			return;
		}

		No atual = inicio;

		while (atual.proximo != inicio) {
			atual = atual.proximo;
		}

		System.out.println("\nInício da lista: " + inicio.dado);
		System.out.println("Final da lista: " + atual.dado);
		System.out.println("Final aponta para: " + atual.proximo.dado);

	}

	public static void main(String[] args) {
		ListaCircular lista = new ListaCircular();

		lista.insereElemento(10);
		lista.insereElemento(20);
		lista.insereElemento(30);

		lista.exibeLista();
		
		lista.verificarLista();

		lista.remove(30);

		lista.exibeLista();
		
		lista.verificarLista();
	}
}
