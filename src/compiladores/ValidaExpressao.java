package compiladores;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ValidaExpressao {

	static Scanner scan;
	static PrintWriter pWriter;
	static FileWriter fWriter;

	public static void main(String[] args) {
		try {
			fWriter = new FileWriter("./src/prog-check.txt");
			pWriter = new PrintWriter(fWriter);
			scan = new Scanner(new File("./src/prog.txt"), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			String line = scan.nextLine();

			if (estaValido(line)) {
				pWriter.write("\n" + line + " - OK" + "\n");
				System.out.println(line + " - OK ");
				pWriter.flush();

			} else {
				pWriter.append(line + " - Inválido");
				System.out.println("\n" + line + " - Inválido " + "\n");
				pWriter.flush();
			}
		}
	}

	public static boolean estaValido(String expr) {

		Deque<Character> pilha = new ArrayDeque<Character>();

		for (int i = 0; i < expr.length(); i++) {

			char x = expr.charAt(i);
			if (x == '(' || x == '[' || x == '{') {

				pilha.push(x);
				continue;
			}

			if (pilha.isEmpty()) {
				return false;
			}

			char check;
			switch (x) {
			case ')':
				check = pilha.pop();
				if (check == '{' || check == '[') {
					return false;
				}
				break;

			case '}':
				check = pilha.pop();
				if (check == '(' || check == '[') {
					return false;
				}
				break;

			case ']':
				check = pilha.pop();
				if (check == '(' || check == '{') {
					return false;
				}
				break;
			}
		}

		return (pilha.isEmpty());
	}
}
