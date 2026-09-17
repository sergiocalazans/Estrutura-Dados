package projeto_codigo_morse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodigoMorse {

    class Node {

        Character caractere;
        Node esquerda;
        Node direita;

        Node() {
            this.caractere = null;
            this.esquerda = null;
            this.direita = null;
        }

        Node(Character caractere) {
            this.caractere = caractere;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private Node raiz;
    private static final Map<Character, String> codigosMorse = new HashMap<>();

    static {

        codigosMorse.put('A', ".-");
        codigosMorse.put('B', "-...");
        codigosMorse.put('C', "-.-.");
        codigosMorse.put('D', "-..");
        codigosMorse.put('E', ".");
        codigosMorse.put('F', "..-.");
        codigosMorse.put('G', "--.");
        codigosMorse.put('H', "....");
        codigosMorse.put('I', "..");
        codigosMorse.put('J', ".---");
        codigosMorse.put('K', "-.-");
        codigosMorse.put('L', ".-..");
        codigosMorse.put('M', "--");
        codigosMorse.put('N', "-.");
        codigosMorse.put('O', "---");
        codigosMorse.put('P', ".--.");
        codigosMorse.put('Q', "--.-");
        codigosMorse.put('R', ".-.");
        codigosMorse.put('S', "...");
        codigosMorse.put('T', "-");
        codigosMorse.put('U', "..-");
        codigosMorse.put('V', "...-");
        codigosMorse.put('W', ".--");
        codigosMorse.put('X', "-..-");
        codigosMorse.put('Y', "-.--");
        codigosMorse.put('Z', "--..");
        codigosMorse.put('0', "-----");
        codigosMorse.put('1', ".----");
        codigosMorse.put('2', "..---");
        codigosMorse.put('3', "...--");
        codigosMorse.put('4', "....-");
        codigosMorse.put('5', ".....");
        codigosMorse.put('6', "-....");
        codigosMorse.put('7', "--...");
        codigosMorse.put('8', "---..");
        codigosMorse.put('9', "----.");
    }

    public CodigoMorse() {
        raiz = new Node();
        inicializar();
    }


    private void inicializar() {

        for (Map.Entry<Character, String> entrada : codigosMorse.entrySet()) {
            Character caractere = entrada.getKey();
            String morse = entrada.getValue();
            inserir(morse, caractere);
        }
    }



    public void inserir(String codigoMorse, Character caractere) {

        Node atual = raiz;
        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);
            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new Node();
                }
                atual = atual.esquerda;
            }

            else if (simbolo == '-') {

                if (atual.direita == null) {
                    atual.direita = new Node();
                }

                atual = atual.direita;

            }

            else {
                System.out.println("Código Morse inválido: " + codigoMorse);
                return;
            }
        }

        atual.caractere = Character.toUpperCase(caractere);
    }

    public Character buscar(String codigoMorse) {

        Node atual = raiz;

        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);
            if (simbolo == '.') {
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                atual = atual.direita;
            } else {
                return null;
            }
            if (atual == null) {
                return null;
            }
        }

        return atual.caractere;
    }

    public String buscar(Character caractere) {
        caractere = Character.toUpperCase(caractere);
        return buscarCodigoMorse(raiz, caractere, "");
    }

    private String buscarCodigoMorse(Node atual, Character procurado, String caminho) {

        if (atual == null) {
            return null;
        }

        if (atual.caractere != null &&
                atual.caractere.equals(procurado)) {
            return caminho;
        }

        String resultadoEsquerda = buscarCodigoMorse(atual.esquerda, procurado,caminho + ".");

        if (resultadoEsquerda != null) {
            return resultadoEsquerda;
        }

        return buscarCodigoMorse(atual.direita, procurado, caminho + "-");
    }

    public String textoParaMorse(String texto) {
        StringBuilder resultado = new StringBuilder();
        texto = texto.toUpperCase();

        for (int i = 0; i < texto.length(); i++) {

            char caractere = texto.charAt(i);

            if (caractere == ' ') {
                resultado.append("/ ");
                continue;
            }

            String morse = buscar(caractere);

            if (morse != null) {
                resultado.append(morse);
                resultado.append(" ");

            } else {
                resultado.append("? ");
            }
        }

        return resultado.toString().trim();
    }


    public String morseParaTexto(String mensagem) {

        StringBuilder resultado = new StringBuilder();

        String[] codigos = mensagem.trim().split("\\s+");

        for (String codigo : codigos) {

            if (codigo.equals("/")) {
                resultado.append(" ");
                continue;
            }

            Character caractere = buscar(codigo);

            if (caractere != null) {
                resultado.append(caractere);

            } else {
                resultado.append('?');
            }
        }

        return resultado.toString();
    }

    public void imprimirArvore() {

        System.out.println();
        System.out.println("========== ÁRVORE MORSE ==========");
        System.out.println();

        System.out.println("[RAIZ]");

        imprimirArvore(raiz.esquerda, "", ".", false);

        imprimirArvore(raiz.direita, "", "-", true);
    }


    private void imprimirArvore(Node node, String prefixo, String caminho, boolean ultimo) {

        if (node == null) {
            return;
        }

        System.out.print(prefixo);

        if (ultimo) {
            System.out.print("└── ");
        } else {
            System.out.print("├── ");
        }

        if (node.caractere != null) {
            System.out.println(node.caractere + " [" + caminho + "]");
        } else {

            System.out.println("○ [" + caminho + "]");
        }

        String novoPrefixo = prefixo + (ultimo ? "    " : "│   ");

        boolean temEsquerda = node.esquerda != null;
        boolean temDireita = node.direita != null;

        if (temEsquerda) {
            imprimirArvore(node.esquerda, novoPrefixo, caminho + ".", !temDireita);
        }

        if (temDireita) {

            imprimirArvore(node.direita, novoPrefixo, caminho + "-", true);
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println();
            System.out.println("========== CÓDIGO MORSE ==========");
            System.out.println("1 - Converter texto para Morse");
            System.out.println("2 - Converter Morse para texto");
            System.out.println("3 - Buscar caractere");
            System.out.println("4 - Buscar código Morse");
            System.out.println("5 - Inserir novo caractere");
            System.out.println("6 - Exibir árvore");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Digite uma mensagem: ");
                    String texto = scanner.nextLine();
                    System.out.println("Morse: " + textoParaMorse(texto));
                    break;
                case 2:
                    System.out.println("Digite o código Morse.");
                    System.out.println("Separe letras por espaço e palavras por /");
                    System.out.print("Morse: ");
                    
                    String morse = scanner.nextLine();

                    System.out.println("Texto: " + morseParaTexto(morse));
                    break;

                case 3:
                    System.out.print("Digite o caractere: ");
                    char caractere = scanner.nextLine().toUpperCase().charAt(0);
                    String codigo = buscar(caractere);

                    if (codigo != null) {
                        System.out.println( caractere + " = " + codigo);
                    } else {
                        System.out.println("Caractere não encontrado.");
                    }

                    break;

                case 4:

                    System.out.print("Digite o código Morse: ");
                    String codigoBusca = scanner.nextLine();
                    Character resultado =buscar(codigoBusca);

                    if (resultado != null) {
                        System.out.println(codigoBusca + " = " + resultado);

                    } else {
                        System.out.println("Código Morse não encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Digite o caractere: ");
                    char novoCaractere = scanner.nextLine().toUpperCase().charAt(0);
                    System.out.print("Digite o código Morse: ");
                    String novoCodigo = scanner.nextLine();
                    inserir(novoCodigo, novoCaractere);
                    System.out.println("Caractere inserido.");
                    break;
                case 6:
                    imprimirArvore();
                    break;
                case 0:
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static void main(String[] args) {
        CodigoMorse arvore = new CodigoMorse();
        arvore.menu();
    }
}