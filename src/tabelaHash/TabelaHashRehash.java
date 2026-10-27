package tabelaHash;

import java.util.Random;

public class TabelaHashRehash {

    public static void main(String[] args) {

        // Tabela com 100 posições.
        int[] tabela = new int[100];

        // Indica se cada posição da tabela está ocupada.
        boolean[] ocupada = new boolean[100];

        // Gerador de números aleatórios com semente fixa.
        Random random = new Random(1090);

        // Armazena os três elementos com mais colisões.
        int[] elementosMaisColisoes = new int[3];
        int[] quantidadesColisoes = new int[3];

        int quantidadeRanking = 0;
        int posicoesOcupadas = 0;
        int totalColisoes = 0;

        /*
         * O programa pode tentar inserir até 100 elementos,
         * mas para quando houver 90 posições ocupadas.
         */
        for (int tentativa = 0;
             tentativa < 100 && posicoesOcupadas < 90;
             tentativa++) {

            // Gera um número de 0 até 100.000, inclusive.
            int valor = random.nextInt(100001);

            // Função hash: calcula a posição inicial.
            int posicao = valor % 100;

            // Conta as colisões deste elemento.
            int colisoesElemento = 0;

            /*
             * Enquanto a posição estiver ocupada, conta uma
             * colisão e procura a próxima posição.
             */
            while (ocupada[posicao]) {

                colisoesElemento++;

                // Rehash: avança e retorna ao início após a posição 99.
                posicao = (posicao + 1) % 100;
            }

            // Insere o valor na posição livre encontrada.
            tabela[posicao] = valor;
            ocupada[posicao] = true;
            posicoesOcupadas++;

            // Soma as colisões deste elemento ao total.
            totalColisoes = totalColisoes + colisoesElemento;

            /*
             * Atualiza o ranking somente para elementos que
             * sofreram pelo menos uma colisão.
             */
            if (colisoesElemento > 0) {

                int lugar = 0;

                /*
                 * Procura onde o elemento deve entrar no ranking.
                 * Em caso de empate, mantém primeiro o elemento
                 * que foi inserido anteriormente.
                 */
                while (lugar < quantidadeRanking
                        && quantidadesColisoes[lugar] >= colisoesElemento) {

                    lugar++;
                }

                if (lugar < 3) {

                    // Desloca os elementos para abrir espaço.
                    int j = 2;

                    while (j > lugar) {

                        elementosMaisColisoes[j] =
                                elementosMaisColisoes[j - 1];

                        quantidadesColisoes[j] =
                                quantidadesColisoes[j - 1];

                        j--;
                    }

                    elementosMaisColisoes[lugar] = valor;
                    quantidadesColisoes[lugar] = colisoesElemento;

                    if (quantidadeRanking < 3) {
                        quantidadeRanking++;
                    }
                }
            }
        }

        // Apresentação dos resultados.
        System.out.println("Limite de 90% de ocupação atingido.");
        System.out.println("Elementos inseridos: " + posicoesOcupadas);
        System.out.println("Total de colisões: " + totalColisoes);

        System.out.println();
        System.out.println("Elementos que mais sofreram colisões:");

        for (int i = 0; i < quantidadeRanking; i++) {

            System.out.println(
                    (i + 1) + "º: "
                    + elementosMaisColisoes[i]
                    + " - "
                    + quantidadesColisoes[i]
                    + " colisões"
            );
        }

        System.out.println();
        System.out.print("Posições livres: ");

        for (int i = 0; i < 100; i++) {

            if (ocupada[i] == false) {
                System.out.print(i + " ");
            }
        }

        System.out.println();
    }
}