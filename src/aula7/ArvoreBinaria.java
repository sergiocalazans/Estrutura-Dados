package aula7;

public class ArvoreBinaria {

	// Classe que representa cada nó da árvore
	class Node {

		int valor;
		Node esquerda;
		Node direita;

		Node(int valor) {

			this.valor = valor;
			this.esquerda = null;
			this.direita = null;
		}
	}

	// Raiz da árvore
	Node raiz = null;


	// Inserção
	// Menor -> esquerda
	// Maior ou igual -> direita

	Node inserir(Node n, int valor) {

		// Encontrou uma posição vazia
		if (n == null) {

			return new Node(valor);
		}

		// Valores menores ficam à esquerda
		if (valor < n.valor) {

			n.esquerda = inserir(n.esquerda, valor);

		} else {

			// Valores maiores ou iguais ficam à direita
			n.direita = inserir(n.direita, valor);
		}

		return n;
	}

	void inserir(int valor) {

		raiz = inserir(raiz, valor);
	}



	// Busca
	Node buscar(Node n, int valor) {

		// Chegou ao final sem encontrar
		if (n == null) {
			return null;
		}

		// Encontrou
		if (valor == n.valor) {
			return n;
		}

		// Se for menor, busca na esquerda
		if (valor < n.valor) {
			return buscar(n.esquerda, valor);
		}

		// Se for maior, busca na direita
		return buscar(n.direita, valor);
	}

	void buscar(int valor) {

		Node resultado = buscar(raiz, valor);

		if (resultado == null) {
			System.out.println("Elemento " + valor + " não encontrado.");
		} else {

			System.out.println("Elemento " + valor + " encontrado.");
		}
	}



	// Encontrar menor elemento
	Node menorElemento(Node n) {

		Node atual = n;

		// O menor elemento é o mais à esquerda
		while (atual.esquerda != null) {
			atual = atual.esquerda;
		}

		return atual;
	}

	// Remoção com critério do menor elemento da direita
	Node remover(Node n, int valor) {

		// Elemento não encontrado
		if (n == null) {
			return null;
		}

		// Procura o elemento na esquerda
		if (valor < n.valor) {
			n.esquerda = remover(n.esquerda, valor);
			
		// Procura o elemento na direita
		} else if (valor > n.valor) {
			n.direita = remover(n.direita, valor);

		// Encontrou o elemento
		} else {

			// Nó sem filhos
			if (n.esquerda == null &&
				n.direita == null) {
				return null;
			}

			// Nó possui somente filho à direita
			if (n.esquerda == null) {
				return n.direita;
			}

			// Nó possui somente filho à esquerda
			if (n.direita == null) {
				return n.esquerda;
			}

			// Nó possui dois filhos
			// Busca o menor elemento da direita
			Node menor = menorElemento(n.direita);

			// Copia o valor para o nó atual
			n.valor = menor.valor;

			// Remove o elemento que foi copiado
			n.direita = remover(n.direita, menor.valor);
		}

		return n;
	}

	void remover(int valor) {

		// Verifica primeiro se o elemento existe
		if (buscar(raiz, valor) == null) {
			System.out.println("Elemento " + valor + " não encontrado.");
			return;
		}

		raiz = remover(raiz, valor);

		System.out.println("Elemento " + valor + " removido.");
	}


	// Percurso em ordem
	void emOrdem(Node n) {

		if (n != null) {
			emOrdem(n.esquerda);
			System.out.print(n.valor + " ");
			emOrdem(n.direita);
		}
	}

	
	// Imprimir a árvore
	void imprimir() {
		System.out.print("Árvore: ");
		emOrdem(raiz);
		System.out.println();
	}

	public static void main(String[] args) {

		ArvoreBinaria arvore = new ArvoreBinaria();

		// Inserções
		arvore.inserir(50);
		arvore.inserir(30);
		arvore.inserir(70);
		arvore.inserir(20);
		arvore.inserir(40);
		arvore.inserir(60);
		arvore.inserir(80);

		arvore.imprimir();

		// Buscas
		arvore.buscar(40);
		arvore.buscar(100);

		// Remoção
		arvore.remover(50);

		arvore.imprimir();

		// Elemento inexistente
		arvore.remover(100);
	}
}