package compiladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ValidaExpressao {

	public static void main(String[] args) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File("teste.txt"));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			
			// Chamando a função passando a expressão como parâmetro
			if (estaValido(line))
				System.out.println(line + " - Válido ");
			else
				System.out.println(line + " - Inválido ");
		}
	}

	// Função para checar se está valido a expressão
	public static boolean estaValido(String expr) {

		Deque<Character> pilha = new ArrayDeque<Character>();

		// Lendo a expressão
		for (int i = 0; i < expr.length(); i++) {

			char x = expr.charAt(i);
			if (x == '(' || x == '[' || x == '{') {

				pilha.push(x);
				continue;
			}

			// SE o caractere atual atual não estiver abrindo
			// colchete, então ele deve estar fechando. Então empilhe
			// não pode estar vazio neste ponto.

			if (pilha.isEmpty())
				return false;

			char check;
			switch (x) {
			case ')':
				check = pilha.pop();
				if (check == '{' || check == '[')
					return false;
				break;

			case '}':
				check = pilha.pop();
				if (check == '(' || check == '[')
					return false;
				break;

			case ']':
				check = pilha.pop();
				if (check == '(' || check == '{')
					return false;
				break;
			}
		}

		// verificar se a pilha está vazia
		return (pilha.isEmpty());
	}
}
