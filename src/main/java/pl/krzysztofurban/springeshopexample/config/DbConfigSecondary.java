package pl.krzysztofurban.springeshopexample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactoryHistory",
    transactionManagerRef = "transactionManagerHistory",
    basePackages = {"pl.krzysztofurban.springeshopexample.repository.history"}
)
@Slf4j
public class DbConfigSecondary {
  @Bean(name = "dataSourceHistory")
  @ConfigurationProperties(prefix = "spring.datasource.history")
  public DataSource dataSourceSecondary() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "entityManagerFactoryHistory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("dataSourceHistory") DataSource dataSource) {
    log.info("Secondary entity manager initialized");
    return builder.dataSource(dataSource)
        .packages("pl.krzysztofurban.springeshopexample.model.history")
        .persistenceUnit("history")
        .properties(jpaProperties()).build();
  }

  @Bean(name = "transactionManagerHistory")
  public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactoryHistory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Map<String, Object> jpaProperties() {
    Map<String, Object> props = new HashMap<>();
    props.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
    props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    props.put("hibernate.hbm2ddl.auto", "create");
    return props;
  }
}
