package com.vvopaa.universalsite.spring;


import com.vvopaa.universalsite.model.Client;
import com.vvopaa.universalsite.model.ClientScopes;
import com.vvopaa.universalsite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vvopaa.universalsite.model.UserRoles;

@Configuration
@EnableTransactionManagement
@ComponentScans(value = { 
		@ComponentScan("com.vvopaa.universalsite") 
	}
)
public class AppConfig {
	
	@Autowired
	private ApplicationContext context;
	
	
	@Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setAnnotatedClasses(User.class, UserRoles.class, Client.class, ClientScopes.class);
        return factoryBean;
    }
 
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
    
}
