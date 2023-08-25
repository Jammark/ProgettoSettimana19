package progettosettimana19.progettosettimana19;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Progettosettimana19ApplicationTests {

	@Autowired
	private MainRunner runner;

	@Test
	void contextLoads() {
		try {
			Thread.sleep(15000);
			runner.getProcesso().getSonde().forEach(s -> assertFalse(s.getLivelloDiFumo() != 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
