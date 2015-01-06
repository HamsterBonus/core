package hamster.service;

import hamster.test.DataSets;
import hamster.test.IntegrationConfig;
import hamster.test.IntegrationExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ IntegrationExecutionListener.class, DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = {IntegrationConfig.class})
@ActiveProfiles("DB_H2")
@DataSets(setUpDataSet="/db/TestData.xls")
public abstract class AServiceTest{// extends AbstractTransactionalJUnit4SpringContextTests {

}
