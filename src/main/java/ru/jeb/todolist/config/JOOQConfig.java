package ru.jeb.todolist.config;

import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * @author Jeb
 */
@Configuration
public class JOOQConfig {
    private final DataSource dataSource;

    @Autowired
    public JOOQConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }

    @Bean
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }

    public DefaultConfiguration configuration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider());
        jooqConfiguration.setSettings(settings());
        jooqConfiguration.setSQLDialect(SQLDialect.POSTGRES);
        return jooqConfiguration;
    }

    public Settings settings() {
        return new Settings()
                .withRenderQuotedNames(RenderQuotedNames.NEVER)
                .withRenderNameCase(RenderNameCase.LOWER);
    }
}
