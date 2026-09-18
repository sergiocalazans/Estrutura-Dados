# Código Morse

## Descrição

Este projeto implementa um tradutor de Código Morse em Java utilizando uma árvore binária.

O programa permite converter textos para Código Morse, converter Código Morse para texto, buscar caracteres, buscar códigos Morse, inserir novos caracteres e visualizar a árvore.

## Funcionamento

O Código Morse é armazenado em uma árvore binária.

Cada símbolo indica o caminho que deve ser seguido:

* Ponto (`.`): esquerda
* Traço (`-`): direita

Por exemplo, a letra A possui o código `.-`. O programa parte da raiz, segue para a esquerda pelo ponto e depois para a direita pelo traço.

## Estrutura do código

A classe `Node` representa os nós da árvore. Cada nó possui um caractere e referências para os nós da esquerda e da direita.

O `HashMap` armazena os códigos Morse das letras de A até Z e dos números de 0 até 9. Quando o programa é iniciado, esses códigos são inseridos na árvore.

## Principais métodos

* `inicializar()`: adiciona os códigos Morse iniciais na árvore.
* `inserir()`: insere um caractere na árvore de acordo com seu Código Morse.
* `buscar(String)`: recebe um Código Morse e retorna o caractere correspondente.
* `buscar(Character)`: recebe um caractere e retorna seu Código Morse.
* `buscarCodigoMorse()`: percorre a árvore para localizar o código de um caractere.
* `textoParaMorse()`: converte um texto para Código Morse.
* `morseParaTexto()`: converte Código Morse para texto.
* `imprimirArvore()`: exibe a árvore no terminal.
* `menu()`: apresenta as opções disponíveis para o usuário.
* `main()`: inicia o programa.

## Conversão

Na conversão de texto para Morse, cada caractere é buscado na árvore e substituído pelo código correspondente.

Na conversão de Morse para texto, o programa percorre a árvore seguindo os pontos e traços até encontrar o caractere.

Os códigos das letras são separados por espaços e o caractere `/` representa um espaço entre palavras.

## Tecnologias utilizadas

* Java
* Árvore binária
* HashMap
* Recursividade
* Scanner
