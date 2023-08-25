package progettosettimana19.progettosettimana19.model;

import java.util.HashSet;
import java.util.Set;

public class CentroDiControllo {

	private Set<Subscriber> subscribers = new HashSet<>();

	public void subscribe(Subscriber s) {
		this.subscribers.add(s);
	}

	public void unsubscribe(Subscriber s) {
		this.subscribers.remove(s);
	}

	public void notifySubscribers(Sonda s) {
		this.subscribers.forEach(su -> su.intervento(s));
	}
}
