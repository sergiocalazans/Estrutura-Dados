package aula8;

public class ArvoreAVL {

	// Classe que armazena cada nó da árvore
	class Node {

		int valor;
		Node esquerda;
		Node direita;

		// Construtor: cria nó com valor
		Node(int v) {
			valor = v;
			esquerda = null;
			direita = null;
		}
	}

	// Raiz da árvore
	Node raiz = null;

	// Calcula altura com recursão
	int altura(Node n) {

		if (n == null) {
			return -1;
		}

		int altEsq = altura(n.esquerda);
		int altDir = altura(n.direita);

		if (altEsq > altDir) {
			return altEsq + 1;
		} else {
			return altDir + 1;
		}
	}

	// Rotação Simples à Direita
	Node rotacaoDireita(Node n) {
		Node temp = n.esquerda;
		n.esquerda = temp.direita;
		temp.direita = n;
		return temp;
	}

	// Rotação Simples à Esquerda
	Node rotacaoEsquerda(Node n) {
		Node temp = n.direita;
		n.direita = temp.esquerda;
		temp.esquerda = n;
		return temp;
	}

	// Rotação Dupla Esquerda-Direita
	Node rotacaoDuplaLR(Node n) {
		n.esquerda = rotacaoEsquerda(n.esquerda);
		return rotacaoDireita(n);
	}

	// Rotação Dupla Direita-Esquerda
	Node rotacaoDuplaRL(Node n) {
		n.direita = rotacaoDireita(n.direita);
		return rotacaoEsquerda(n);
	}

	// Inserção
	// Menor -> esquerda
	// Maior ou igual -> direita
	Node inserir(Node n, int valor) {

		// Encontrou posição vazia
		if (n == null) {
			return new Node(valor);
		}

		// Menores ficam à esquerda
		if (valor < n.valor) {

			n.esquerda = inserir(n.esquerda, valor);

		} else {

			// Maiores ou iguais ficam à direita
			n.direita = inserir(n.direita, valor);
		}

		// Calcula fator de balanceamento
		int fb = altura(n.esquerda) - altura(n.direita);

		// Caso LL ou LR
		if (fb > 1) {

			int fbFilho = altura(n.esquerda.esquerda) - altura(n.esquerda.direita);

			// Caso LL
			if (fbFilho >= 0) {
				return rotacaoDireita(n);

			} else {
				// Caso LR
				return rotacaoDuplaLR(n);
			}
		}

		// Caso RR ou RL
		if (fb < -1) {

			int fbFilho = altura(n.direita.esquerda) - altura(n.direita.direita);

			// Caso RR
			if (fbFilho <= 0) {
				return rotacaoEsquerda(n);

			} else {
				// Caso RL
				return rotacaoDuplaRL(n);
			}
		}

		return n;
	}

	// Método para inserir começando pela raiz
	void inserir(int valor) {
		raiz = inserir(raiz, valor);
	}


	// Busca baseada no valor
	Node buscar(Node n, int valor) {

		// Chegou ao final da árvore sem encontrar
		if (n == null) {
			return null;
		}

		// Encontrou o elemento
		if (valor == n.valor) {
			return n;
		}

		// Valor procurado é menor
		if (valor < n.valor) {
			return buscar(n.esquerda, valor);
		}

		// Valor procurado é maior
		return buscar(n.direita, valor);
	}

	// Método de busca começando pela raiz
	boolean buscar(int valor) {

		Node resultado = buscar(raiz, valor);

		if (resultado == null) {
			System.out.println("Elemento " + valor + " não encontrado.");
			return false;
		}

		System.out.println("Elemento " + valor + " encontrado.");
		return true;
	}

	// Encontrar menor elemento 
	Node menorElemento(Node n) {

		Node atual = n;

		// O menor elemento sempre será o nó
		// mais à esquerda
		while (atual.esquerda != null) {
			atual = atual.esquerda;
		}

		return atual;
	}

	// Remoção com critério do menor elemento da subárvore direita
	Node remover(Node n, int valor) {

		// Elemento não encontrado
		if (n == null) {
			return null;
		}

		// Procura o elemento
		if (valor < n.valor) {
			n.esquerda = remover(n.esquerda, valor);
		} else if (valor > n.valor) {
			n.direita = remover(n.direita, valor);
		} else {

			// Encontrou o nó que será removido

			// Nó sem filhos
			if (n.esquerda == null && n.direita == null) {
				return null;
			}

			// Possui somente filho à direita
			if (n.esquerda == null) {
				return n.direita;
			}

			// Possui somente filho à esquerda
			if (n.direita == null) {
				return n.esquerda;
			}

			// Possui dois filhos
			// Busca o MENOR elemento da subárvore direita
			Node menor = menorElemento(n.direita);

			// Substitui o valor do nó
			n.valor = menor.valor;

			// Remove o elemento utilizado
			// da subárvore direita
			n.direita = remover(n.direita, menor.valor);
		}

		// Rebalanceamento após a remoção
		int fb = altura(n.esquerda) - altura(n.direita);

		// Desbalanceamento à esquerda
		if (fb > 1) {
			int fbFilho = altura(n.esquerda.esquerda) - altura(n.esquerda.direita);

			// Caso LL
			if (fbFilho >= 0) {
				return rotacaoDireita(n);

			} else {
				// Caso LR
				return rotacaoDuplaLR(n);
			}
		}

		// Desbalanceamento à direita
		if (fb < -1) {

			int fbFilho = altura(n.direita.esquerda) - altura(n.direita.direita);

			// Caso RR
			if (fbFilho <= 0) {
				return rotacaoEsquerda(n);

			} else {
				// Caso RL
				return rotacaoDuplaRL(n);
			}
		}

		return n;
	}

	// Método para remover começando pela raiz
	void remover(int valor) {

		// Primeiro verifica se existe
		if (buscar(raiz, valor) == null) {
			System.out.println("Não foi possível remover o elemento: " + valor);
			return;
		}

		raiz = remover(raiz, valor);
		System.out.println("Elemento " + valor + " removido.");
	}


	// Impressão em ordem
	void emOrdem(Node n) {

		if (n != null) {
			emOrdem(n.esquerda);
			System.out.print(n.valor + " ");
			emOrdem(n.direita);
		}
	}

	void imprimir() {
		System.out.print("Árvore: ");
		emOrdem(raiz);
		System.out.println();
	}


	public static void main(String[] args) {

		ArvoreAVL arvore = new ArvoreAVL();

		// Inserções
		arvore.inserir(50);
		arvore.inserir(30);
		arvore.inserir(70);
		arvore.inserir(20);
		arvore.inserir(40);
		arvore.inserir(60);
		arvore.inserir(80);

		// Imprimindo a árvore
		arvore.imprimir();

		// Busca de elemento existente
		arvore.buscar(40);

		// Busca de elemento inexistente
		arvore.buscar(100);

		// Remoção
		arvore.remover(50);

		// Imprimindo a árvore
		arvore.imprimir();

		// Tentativa de remover elemento inexistente
		arvore.remover(200);
	}
}