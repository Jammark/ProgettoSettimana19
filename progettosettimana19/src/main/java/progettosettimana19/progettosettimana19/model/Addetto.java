package progettosettimana19.progettosettimana19.model;

import com.github.javafaker.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Getter
public class Addetto implements Subscriber, Personale {

	private String nomeCompleto;
	private String ruolo;
	private Address address;

	@Override
	public void intervento(Sonda s) {
		log.info("intervento: " + getNomeCompleto() + " di ruolo " + getRuolo() + " intervenuto sulla sonda "
				+ s.toString());
		s.reset();

	}

	@Override
	public void operazione(Sonda s) {
		intervento(s);

	}

	@Override
	public double distance(double x, double y) {

		return Double.valueOf(address.latitude().replace(',', '.')) - x
				+ Double.valueOf(address.longitude().replace(',', '.')) - y;
	}

}
