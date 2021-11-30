package it.biglietti;

// import java.util.InputMismatchException;
import java.util.Scanner;

//Stanza Zoom 4

public class Biglietteria {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean flessibile = true;
		
		System.out.println("Inserire km: ");
		int km = scanner.nextInt();
		System.out.println("Inserire età: ");
		int eta = scanner.nextInt();
		
		Biglietto b = new Biglietto(km, eta, flessibile);
		
			do {
				System.out.println("Scrivere 'true' per biglietto flessibile o 'false' per biglietto normale");
				flessibile = scanner.nextBoolean();
				b.setFlessibile(flessibile);
				b.isValidFlessibile(); 
				}
			while (flessibile != true && flessibile != false); 
		
			System.out.println("Data odierna: " + b.getDataFormattata());
		System.out.println("Scadenza biglietto: " + b.calcolaDataScadenza());
		
		try {
			System.out.println("Prezzo finale del biglietto: " + b.CalcolaPrezzo());
		} catch (Exception c) {
			System.out.println("Errore" + c.getMessage());
		}
		
		scanner.close();
		
	}

}
