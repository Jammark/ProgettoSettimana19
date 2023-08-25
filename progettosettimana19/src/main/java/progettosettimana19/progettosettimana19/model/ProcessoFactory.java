package progettosettimana19.progettosettimana19.model;

public class ProcessoFactory extends AbstractProcessoFactory {

	@Override
	public AbstractProcesso createObject(CentroDiControllo c) {

		return new ProcessoDiControllo(c);
	}

}
