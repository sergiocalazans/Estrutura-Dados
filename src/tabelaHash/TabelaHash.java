package tabelaHash;

import java.util.Random;

public class TabelaHash {
    static class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
        }
    }

    public static void main(String[] args) {
        No[] tabela = new No[100];
        Random random = new Random(1090);
        int[] ultimos = new int[3];
        int colisoes = 0;

        for (int i = 0; i < 1000; i++) {
            int valor = random.nextInt(100001); 
            int posicao = valor % tabela.length;
            No novo = new No(valor);

            if (tabela[posicao] == null) {
                tabela[posicao] = novo;
            } else {

                No anterior = tabela[posicao];
                No atual = anterior.proximo;

                while (atual != null && atual.valor <= valor) {
                    anterior = atual;
                    atual = atual.proximo;
                }

                novo.proximo = atual;
                anterior.proximo = novo;

                ultimos[0] = ultimos[1];
                ultimos[1] = ultimos[2];
                ultimos[2] = valor;
                colisoes++;
            }
        }

        int listas = 0;
        int vazias = 0;
        int maiorTamanho = 0;
        int maiorPosicao = -1;

        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] == null) {
                vazias++;
            } else {
                if (tabela[i].proximo != null) {
                    listas++;
                }

                int tamanho = 0;
                No atual = tabela[i].proximo;
                while (atual != null) {
                    tamanho++;
                    atual = atual.proximo;
                }

                if (tamanho > maiorTamanho) {
                    maiorTamanho = tamanho;
                    maiorPosicao = i;
                }
            }
        }

        System.out.println("Listas encadeadas geradas: " + listas);
        System.out.println("Posições vazias: " + vazias);
        System.out.println("Total de colisões: " + colisoes);

        if (maiorPosicao != -1) {
            System.out.println("Maior lista: posição " + maiorPosicao);
            System.out.println("Valor na tabela: " + tabela[maiorPosicao].valor);
            System.out.println("Elementos na lista de colisões: " + maiorTamanho);
            System.out.print("Lista: ");
            No atual = tabela[maiorPosicao].proximo;
            while (atual != null) {
                System.out.print(atual.valor + " -> ");
                atual = atual.proximo;
            }
            System.out.println("null");
        }

        System.out.print("Últimos valores inseridos em listas: ");
        int inicio = 0;
        if (colisoes < 3) {
            inicio = 3 - colisoes;
        }
        for (int i = inicio; i < 3; i++) {
            System.out.print(ultimos[i] + " ");
        }
        System.out.println();
    }
}

