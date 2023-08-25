package progettosettimana19.progettosettimana19;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import progettosettimana19.progettosettimana19.model.AbstractProcesso;
import progettosettimana19.progettosettimana19.model.CentroDiControllo;
import progettosettimana19.progettosettimana19.model.LuogoDiInstallazione;
import progettosettimana19.progettosettimana19.model.Personale;
import progettosettimana19.progettosettimana19.model.ProcessoFactory;
import progettosettimana19.progettosettimana19.model.Sonda;

@Component
@Order(1)
@Slf4j
public class MainRunner implements CommandLineRunner {

	private AbstractProcesso processo;

	@Override
	public void run(String... args) throws Exception {
		log.info("avvio");
		Faker faker = new Faker();
		CentroDiControllo centro = new CentroDiControllo();
		Personale personale = new Personale("Paolo Rossi", "Addetto sonde");
		centro.subscribe(personale);
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

}
