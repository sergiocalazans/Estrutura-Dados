package aula3;

public class ListaEncadeada {
    // Classe interna para representar um nó da lista
    class No {
        int dado; // Valor armazenado no nó
        No proximo; // Referência para o próximo nó

        // Construtor do nó
        No(int elemento) {
            this.dado = elemento;
            this.proximo = null;
        }
    }

    No inicio = null; // Referência para o primeiro nó da lista

    // Método para inserir um elemento no final da lista
    public void insereElemento(int elemento) {
        No novoNo = new No(elemento); // Cria um novo nó com o elemento fornecido

        if (inicio == null) {
            // Caso a lista esteja vazia, o início aponta para o novo nó
            inicio = novoNo;
        } else {
            // Caso contrário, percorre a lista até o final
            No atual = inicio;
            while (atual.proximo != null) {
                
                atual = atual.proximo; // Avança para o próximo nó
            }
            atual.proximo = novoNo; // Insere o novo nó no final da lista
        }
        System.out.println("Novo elemento: " + elemento);
    }
    
    // 1° Caso - Implementação atividade
    public void apagarLista() {
    	if(vazia()) {
    	} else {
    		inicio = null;
    		System.out.println("Lista apagada!");
    	}
    }
    
    // 2° Caso - Implementação atividade
    public void removeSeProximoNull() {
    	if (vazia()) {
    	} else {
    		if(inicio.proximo == null) {
        		System.out.println("Próximo é null");
        		
        		inicio = null;
        	} else {
        		System.out.println("O tamanho da lista é maior do que 1.");
        	}
    	}
    }
    
    // 3° Caso - Implementação atividade
    public void removeInicio() {
    	if (vazia()) {	
    	} else {
        	System.out.println("Elemento inicial removido: " + inicio.dado);
        	inicio = inicio.proximo;
    	}
    	
    }
    
    public void remove(int elemento) {
    	No atual = inicio;
    	No anterior = inicio;
    
    	if (vazia()) {
    	} else {
    		while (true) {
            	if (atual.dado == elemento) {
            		System.out.println("Elemento removido: " + atual.dado);
            		anterior.proximo = atual.proximo;
            		atual = null;
            		break;
            	} 
            	if (atual.proximo == null) {
            		System.out.println("Não há o elemento: " + elemento + " na lista.");
            		break;
            	}
            	anterior = atual;
            	atual = atual.proximo;
            }
    	}
        
    }
    
    public boolean vazia() {
    	if(inicio == null) {
    		System.out.println("Lista vazia!");
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void removeUltimo() {
    	No atual = inicio;
    	No anterior = inicio;
    	if(vazia()) {
    	} else {
    		if (atual.proximo == null) {
    			removeInicio();
    		} else {
    			while (true) {
                	if (atual.proximo == null) {
                		System.out.println("Último elemento removido: " + atual.dado);
                		atual = null;
                		anterior.proximo = null;
                		break;
                	}
                	anterior = atual;
                	atual = atual.proximo;
                }
    		}
    	}
    }
    
    // Método para exibir os elementos da lista (para teste)
    public void exibeLista() {   
        if (vazia()) {
        } else {
            No atual = inicio;
            System.out.print("Lista: ");
        	while (atual != null) {
                System.out.print(atual.dado + " ");
                atual = atual.proximo; // Move para o próximo nó
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();

        // Inserindo elementos na lista
        lista.insereElemento(10);
        lista.insereElemento(20);
        lista.insereElemento(30);
        
        lista.exibeLista(); // Exibe: 10, 20, 30

        lista.removeInicio(); 
        
        lista.exibeLista(); // Exibe: 20, 30
        
        lista.insereElemento(40);
        
        lista.exibeLista(); // Exibe: 20, 30, 40
        
        lista.removeUltimo();
        
        lista.exibeLista(); // Exibe: 20, 30
        
        lista.insereElemento(50);
        
        lista.exibeLista(); // Exibe: 20, 30, 50
        
        lista.remove(30);
        
        lista.exibeLista(); // Exibe: 20, 50
        
        lista.apagarLista();
        
        lista.exibeLista();

    }
}
