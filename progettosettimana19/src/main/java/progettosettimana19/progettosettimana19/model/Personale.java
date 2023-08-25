package progettosettimana19.progettosettimana19.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Getter
public class Personale implements Subscriber {

	private String nomeCompleto;
	private String ruolo;

	@Override
	public void intervento(Sonda s) {
		log.info("intervento: " + getNomeCompleto() + " di ruolo " + getRuolo() + "intervenuto sulla sonda "
				+ s.toString());

	}

}
