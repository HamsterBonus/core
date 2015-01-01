package hamster.bonus.service;

import static org.junit.Assert.*;


import javax.annotation.Resource;

import hamster.bonus.IntegrationTestConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class})
public class BonusServiceIntegrationTest {

	@Resource
	BonusService bonusService;
	
	@Test
	public void test(){
		
	}

}
