package progettosettimana19.progettosettimana19.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AbstractProcesso {

	private CentroDiControllo centro;
	private Set<Sonda> sonde = new HashSet<>();

	public AbstractProcesso(CentroDiControllo centro) {
		super();
		this.centro = centro;
	}

	public void register(Sonda s) {
		this.sonde.add(s);
	}

	private void proceede() {
		timeout();
		sonde.forEach(s -> {
			if (s.isLimite()) {
				centro.notifySubscribers(s);
			}
		});
	}

	public void start() {
		ScheduledThreadPoolExecutor t = new ScheduledThreadPoolExecutor(2);
		t.scheduleAtFixedRate(this::proceede, 2, 1, TimeUnit.SECONDS);
	}

	abstract void timeout();

}
