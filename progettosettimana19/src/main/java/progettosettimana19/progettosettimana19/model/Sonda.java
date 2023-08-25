package progettosettimana19.progettosettimana19.model;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Sonda {

	private double longitudine, latitudine;
	private int livelloDiFumo;
	private ScheduledThreadPoolExecutor t;
	private LuogoDiInstallazione luogo;

	public Sonda(double longitudine, double latitudine) {
		super();
		this.longitudine = longitudine;
		this.latitudine = latitudine;
		this.livelloDiFumo = 0;
	}

	public boolean isLimite() {
		return !(livelloDiFumo < 5);
	}

	public void increment() {
		if (isLimite()) {
			t.shutdown();
		}
		livelloDiFumo++;
		log.info("Aumento livello fumo a livello " + this.livelloDiFumo + " in luogo: " + this.luogo.getIndirizzo());
		log.info("next...");
	}

	public void attiva() {
		t = new ScheduledThreadPoolExecutor(2);
		t.scheduleAtFixedRate(this::increment, 1, 2, TimeUnit.SECONDS);
	}

	public void setLuogo(LuogoDiInstallazione luogo) {
		this.luogo = luogo;
	}

	@Override
	public String toString() {
		return "Sonda [longitudine=" + longitudine + ", latitudine=" + latitudine + ", livelloDiFumo=" + livelloDiFumo
				+ ", luogo=" + luogo.getIndirizzo() + "]";
	}

}
