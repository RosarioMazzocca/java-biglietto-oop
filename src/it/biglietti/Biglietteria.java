package it.biglietti;

import java.util.Scanner;

//Stanza Zoom 4

public class Biglietteria {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Inserire km: ");
		int km = scanner.nextInt();
		System.out.println("Inserire età: ");
		int eta = scanner.nextInt();
		
		Biglietto b = new Biglietto(km, eta);
		
		try {
			System.out.println("Prezzo finale del biglietto: " + b.CalcolaPrezzo());
		} catch (Exception c) {
			System.out.println("Errore" + c.getMessage());
			
		}
		
		scanner.close();
		
	}

}
