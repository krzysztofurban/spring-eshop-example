package pl.krzysztofurban.springeshopexample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager",
    basePackages = {"pl.krzysztofurban.springeshopexample.repository.orders"})
@Slf4j
public class DbConfigPrimary {
  @Bean(name = "dataSource")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.orders")
  public DataSource dataSourcePrimary() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                         @Qualifier("dataSource") DataSource da) {
    return builder.dataSource(dataSourcePrimary()).packages("pl.krzysztofurban.springeshopexample.model.orders")
        .persistenceUnit("orders")
        .properties(jpaProperties())
        .build();
  }

  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Map<String, Object> jpaProperties() {
    Map<String, Object> props = new HashMap<>();
    props.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
    props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
    props.put("hibernate.hbm2ddl.auto", "create");
    return props;
  }
}
