package aula3;

public class ListaDuplaEncadeada {
	class No {
		int dado;
		No proximo;
		No anterior;

		No(int elemento) {
			this.dado = elemento;
			this.anterior = null;
			this.proximo = null;
		}
	}

	private No cabeca = null;
	private No cauda = null;

	public void insereElemento(int elemento) {
		No novoNo = new No(elemento);
		if (cabeca == null) {
			cabeca = novoNo;
			cauda = cabeca;
		} else {
			No atual = cabeca;
			while (atual.proximo != null) {
				atual = atual.proximo;
			}
			novoNo.anterior = atual;
			atual.proximo = novoNo;
			cauda = novoNo;
		}
		System.out.println("\nNovo elemento: " + elemento);
	}

	public boolean vazia() {
		if (cabeca == null) {
			System.out.println("\nLista vazia!");
			return true;
		} else {
			return false;
		}
	}

	public void apagarLista() {
		if (vazia()) {
			return;
		}
		cabeca = null;
		cauda = null;
		System.out.println("\nLista apagada!");
	}

	public void removeInicio() {
		if (vazia()) {
			return;
		}

		if (cabeca == cauda) {
			apagarLista();
		}

		System.out.println("\nElemento inicial removido: " + cabeca.dado);

		cabeca = cabeca.proximo;
		cabeca.anterior = null;
	}

	public void removeUltimo() {
		if (vazia()) {
			return;
		}

		if (cabeca == cauda) {
			apagarLista();
			return;
		}

		System.out.println("\nÚltimo elemento removido: " + cauda.dado);
		cauda = cauda.anterior;
		cauda.proximo = null;
	}

	public void remove(int elemento) {

		if (elemento == cabeca.dado) {
			removeInicio();
			return;
		} else if (elemento == cauda.dado) {
			removeUltimo();
			return;
		}

		No atual = cabeca.proximo;

		while (atual != null && atual != cauda && atual != cabeca) {
			if (atual.dado == elemento) {
				atual.anterior.proximo = atual.proximo;
				atual.proximo.anterior = atual.anterior;
				System.out.println("\nElemento removido: " + atual.dado);
				return;
			}
			atual = atual.proximo;
		}

		System.out.println("\nNão há o elemento: " + elemento + " na lista.");
	}

	public void cabeca() {
		System.out.println("\nCabeça -> " + cabeca.dado);
	}

	public void cauda() {
		System.out.println("\nCauda -> " + cauda.dado);
	}

	public void exibeLista() {
		if (vazia()) {
			return;
		}
		No atual = cabeca;
		System.out.print("\nLista: ");
		while (atual != null) {
			System.out.print(atual.dado + " ");
			atual = atual.proximo;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ListaDuplaEncadeada lista = new ListaDuplaEncadeada();

		for (int i = 1; i < 5; i++) {
			int j = 100 * i;
			lista.insereElemento(j);
		}

		lista.exibeLista();

		lista.remove(200);

		lista.exibeLista();

		lista.cabeca();
		lista.cauda();

	}
}
