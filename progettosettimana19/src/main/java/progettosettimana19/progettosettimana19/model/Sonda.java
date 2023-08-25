package progettosettimana19.progettosettimana19.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sonda {

	private double longitudine, latitudine;
	private int livelloDiFumo;

	public Sonda(double longitudine, double latitudine) {
		super();
		this.longitudine = longitudine;
		this.latitudine = latitudine;
		this.livelloDiFumo = 0;
	}

	public boolean isLimite() {
		return !(livelloDiFumo < 5);
	}

}
