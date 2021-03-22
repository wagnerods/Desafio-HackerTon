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
			
			// Chamando a fun��o passando a express�o como par�metro
			if (estaValido(line))
				System.out.println(line + " - V�lido ");
			else
				System.out.println(line + " - Inv�lido ");
		}
	}

	// Fun��o para checar se est� valido a express�o
	public static boolean estaValido(String expr) {

		Deque<Character> pilha = new ArrayDeque<Character>();

		// Lendo a express�o
		for (int i = 0; i < expr.length(); i++) {

			char x = expr.charAt(i);
			if (x == '(' || x == '[' || x == '{') {

				pilha.push(x);
				continue;
			}

			// SE o caractere atual atual n�o estiver abrindo
			// colchete, ent�o ele deve estar fechando. Ent�o empilhe
			// n�o pode estar vazio neste ponto.

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

		// verificar se a pilha est� vazia
		return (pilha.isEmpty());
	}
}
