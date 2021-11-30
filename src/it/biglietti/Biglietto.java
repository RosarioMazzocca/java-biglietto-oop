package it.biglietti;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

// Stanza Zoom 4

public class Biglietto {
	
	// Attributi
	
	private int kmDaPercorrere;
	private int etaPasseggero;
	private LocalDate data;
	private boolean flessibile;
	private String dataFormattata;
	
	// Costanti
	
	private final BigDecimal COSTO_PER_KM = new BigDecimal("0.21"); // decimals vuole new per crearne uno nuovo
	private final BigDecimal SCONTO_OVER_65 = new BigDecimal("0.4");
	private final BigDecimal SCONTO_UNDER_18 = new BigDecimal("0.2");
	private final int DURATA_NORMALE = 30;
	private final int DURATA_FLESSIBILE = 90;
	private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	// Costruttore
	
	public Biglietto(int kmDaPercorrere, int etaPasseggero, boolean flessibile) {
		this.kmDaPercorrere = kmDaPercorrere;
		this.etaPasseggero = etaPasseggero;
		this.flessibile = flessibile;
		data = LocalDate.now();
		dataFormattata = data.format(FORMATTER);
	}
	
	// Getters e Setters
	
	public int getKmDaPercorrere() {
		return kmDaPercorrere;
	}

	public void setKmDaPercorrere(int kmDaPercorrere) {
		this.kmDaPercorrere = kmDaPercorrere;
	}

	public int getEtaPasseggero() {
		return etaPasseggero;
	}

	public void setEtaPasseggero(int etaPasseggero) {
		this.etaPasseggero = etaPasseggero;
	}
	
	public boolean isflessibile() {
		return flessibile;
	}
	
	public void setFlessibile(boolean flessibile) {
		this.flessibile = flessibile;
	}
	
	public String getDataFormattata() {
		return dataFormattata;
	}
	
	// Metodi
	
	private BigDecimal CalcolaSconto() throws Exception {
		if(isValidEta()) {
			
		
		if (etaPasseggero >=65) {
			return SCONTO_OVER_65;
		} else if (etaPasseggero < 18) {
			return SCONTO_UNDER_18; 
		} else {
			return new BigDecimal("0");
		}
	} else {
		throw new Exception(" Eta dev'essere compresa tra 0 e 120");
	}
}

	public BigDecimal CalcolaPrezzo() throws Exception {
		if (isValidKm()) {
			BigDecimal kmDaPercorrereBD = BigDecimal.valueOf(kmDaPercorrere); // traduce int in big decimal
			BigDecimal costoAggiuntivo = BigDecimal.valueOf(1.1);
			BigDecimal sconto = COSTO_PER_KM.multiply(CalcolaSconto().multiply(kmDaPercorrereBD)); // così sconto diventa 0 se ha un età compresa tra 18 e 64, in modo da non avere sconto
			if(flessibile) {
				return (COSTO_PER_KM.multiply(kmDaPercorrereBD).subtract(sconto)).multiply(costoAggiuntivo);
			} else {
				return COSTO_PER_KM.multiply(kmDaPercorrereBD).subtract(sconto);
			}
		} else {
			throw new Exception(" Km devono essere maggiore di 0");
		}
	}
	
	private boolean isValidKm() {
		if (kmDaPercorrere <= 0) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean isValidEta() {
		if (etaPasseggero <= 0 || etaPasseggero > 120) {
			return false;
		} else {
			return true;
		}
	}
	
	public String calcolaDataScadenza() {
		LocalDate dataScadenza;
		if(flessibile) {
			dataScadenza = data.plusDays(DURATA_FLESSIBILE);
		} else {
			dataScadenza = data.plusDays(DURATA_NORMALE);
		}
		return dataScadenza.format(FORMATTER);
	}
	
	// Errore dal dal compilatore se si inserisce un valore sbagliato al boolean
	
	public boolean isValidFlessibile() throws InputMismatchException {
		if(flessibile) {
			return true;
		} else if(!flessibile) {
			return false;
		} else {
			throw new InputMismatchException("Errore: input non valido. Inserire 'true' oppure 'false");
		}
	}

}
