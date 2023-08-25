package progettosettimana19.progettosettimana19.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessoDiControllo extends AbstractProcesso {

	public ProcessoDiControllo(CentroDiControllo centro) {
		super(centro);

	}

	@Override
	void timeout() {
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

	}

}
