package com.addrbook_backend.springconfig;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.addrbook_backend.dao.PersonDao;

/**
 * Bootstrap for service layer.
 * 
 * @author Trey
 */
@Configuration
@ComponentScan(basePackages = {"com.addrbook_backend.service"})
public class ServiceTestConfig {

	@Bean
	public PersonDao mockPersonDao() {
		return Mockito.mock(PersonDao.class);
	}
	
}