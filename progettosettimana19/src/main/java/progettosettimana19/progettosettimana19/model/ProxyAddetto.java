package progettosettimana19.progettosettimana19.model;

import java.util.function.BiFunction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProxyAddetto implements Personale, Subscriber {

	private Addetto addetto;
	private BiFunction<Personale, Sonda, Boolean> check;

	public boolean checkAccess(Sonda s) {
		return this.check.apply(this, s);
	}

	@Override
	public void operazione(Sonda s) {
		if (!checkAccess(s)) {
			return;
		}
		this.addetto.operazione(s);

	}

	@Override
	public void intervento(Sonda s) {
		this.operazione(s);

	}

	@Override
	public double distance(double x, double y) {

		return this.addetto.distance(x, y);
	}

	@Override
	public String toString() {
		return "ProxyAddetto [addetto=" + addetto + "l:" + addetto.getAddress().latitude() + " lon: "
				+ addetto.getAddress().longitude() + "]";
	}


}
