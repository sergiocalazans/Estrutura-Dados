package aula3;

public class ListaDuplaEncadeadaCircular {

	class No {
		int dado;
		No proximo;
		No anterior;

		No(int elemento) {
			this.dado = elemento;
			this.proximo = null;
			this.anterior = null;
		}
	}

	No cabeca = null;
	No cauda = null;

	public void insereElemento(int elemento) {
		No novoNo = new No(elemento);
		if (cabeca == null) {
		    cabeca = novoNo;
		    cauda = novoNo;
		    cabeca.proximo = cabeca;
		    cabeca.anterior = cabeca;
		} else {
			novoNo.anterior = cauda;
			novoNo.proximo = cabeca;
			cauda.proximo = novoNo;
			cabeca.anterior = novoNo;
			cauda = novoNo;
		}
		System.out.println("\nNovo elemento: " + elemento);
	}

	public void apagarLista() {
		if (vazia()) {
			return;
		}
		cabeca = null;
		cauda = null;
		System.out.println("Lista apagada!");
	}

	public void removeInicio() {
		if (cabeca == cauda) {
			apagarLista();
			System.out.println("A lista contava com 1 elemento.");
			return;
		}
		System.out.println("\nElemento inicial removido: " + cabeca.dado);
		cabeca = cabeca.proximo;
		cauda.proximo = cabeca;
		cabeca.anterior = cauda;
	}

	public void removeUltimo() {
		if (cabeca == cauda) {
			apagarLista();
			System.out.println("A lista contava com 1 elemento.");
			return;
		}
		System.out.println("\nÚltimo elemento removido: " + cauda.dado);
		cauda = cauda.anterior;
		cauda.proximo = cabeca;
		cabeca.anterior = cauda;
	}

	public void remove(int elemento) {
	    if (vazia()) {
	        return;
	    }
	    
		if (cabeca.dado == elemento) {
			removeInicio();
			return;
		} else if (cauda.dado == elemento) {
			removeUltimo();
			return;
		} 
		
		No atual = cabeca.proximo;
		while (atual != cauda && atual != cabeca) {
			if (atual.dado == elemento) {
				System.out.println("\nElemento removido: " + atual.dado);
				atual.anterior.proximo = atual.proximo;
				atual.proximo.anterior = atual.anterior;
				return;
			}
			atual = atual.proximo;
		}
		System.out.println("\nNão há o elemento: " + elemento + " na lista.");
	}

	public boolean vazia() {
		if (cabeca == null) {
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
		No atual = cabeca;
		System.out.print("\nLista: ");
		do {
			System.out.print(atual.dado + " ");
			atual = atual.proximo;
		} while (atual != cabeca);
		System.out.println();
	}

	public void verificarLista() {
		if (vazia()) {
			return;
		}
		System.out.println("\nInício da lista: " + cabeca.dado);
		System.out.println("Final da lista: " + cauda.dado);
		System.out.println("Final aponta para: " + cauda.proximo.dado);
	}

	public static void main(String[] args) {
		
		ListaDuplaEncadeadaCircular lista = new ListaDuplaEncadeadaCircular();
		
		for (int i = 1; i < 5; i++) {
			int j = 100 * i;
			lista.insereElemento(j);
		}
		
		lista.exibeLista();
		
		lista.verificarLista();
		
		lista.remove(200);
		
		lista.exibeLista();
		
		lista.verificarLista();
	}
}
