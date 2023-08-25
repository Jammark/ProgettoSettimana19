package progettosettimana19.progettosettimana19.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LuogoDiInstallazione {

	private String indirizzo;
	private String città;
	private Sonda s;
	private AbstractProcesso processo;

}
