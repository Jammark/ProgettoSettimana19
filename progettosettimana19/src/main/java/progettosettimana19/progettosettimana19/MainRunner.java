package progettosettimana19.progettosettimana19;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import progettosettimana19.progettosettimana19.model.AbstractProcesso;
import progettosettimana19.progettosettimana19.model.Addetto;
import progettosettimana19.progettosettimana19.model.CentroDiControllo;
import progettosettimana19.progettosettimana19.model.LuogoDiInstallazione;
import progettosettimana19.progettosettimana19.model.Personale;
import progettosettimana19.progettosettimana19.model.ProcessoFactory;
import progettosettimana19.progettosettimana19.model.ProxyAddetto;
import progettosettimana19.progettosettimana19.model.Sonda;

@Component
@Order(1)
@Slf4j
public class MainRunner implements CommandLineRunner {

	private AbstractProcesso processo;
	private Set<Personale> personale = new HashSet<>();

	@Override
	public void run(String... args) throws Exception {
		log.info("avvio");
		Faker faker = new Faker();
		CentroDiControllo centro = new CentroDiControllo();
		ProxyAddetto personale1 = new ProxyAddetto(new Addetto("Paolo Rossi", "Addetto sonde", faker.address()),
				this::isValid);
		centro.subscribe(personale1);
		this.personale.add(personale1);

		ProxyAddetto personale2 = new ProxyAddetto(new Addetto("Mario Rossi", "Addetto interventi", faker.address()),
				this::isValid);
		centro.subscribe(personale2);
		this.personale.add(personale2);
		this.personale.forEach(p -> log.info(p.toString()));
		processo = new ProcessoFactory().createObject(centro);
		processo.start();
		LuogoDiInstallazione luogo1 = getLuogoDiInstallazione(processo);
		LuogoDiInstallazione luogo2 = getLuogoDiInstallazione(processo);

	}

	private LuogoDiInstallazione getLuogoDiInstallazione(AbstractProcesso processo) {
		Faker faker = new Faker();
		Address address = faker.address();
		Sonda s = new Sonda(Double.valueOf(address.latitude().replace(',', '.')),
				Double.valueOf(address.longitude().replace(',', '.')));
		LuogoDiInstallazione luogo = new LuogoDiInstallazione(address.fullAddress(), address.city(), s, processo);
		s.setLuogo(luogo);
		processo.register(s);
		return luogo;
	}

	private boolean isValid(Personale p, Sonda s) {
		double x = s.getLatitudine();
		double y = s.getLongitudine();
		return this.personale.stream().min((p1, p2) -> p1.distance(x, y) - p2.distance(x, y) < 0 ? -1 : 1)
				.get()
				.equals(p);
	}

}
