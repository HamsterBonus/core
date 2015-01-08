package hamster.spring;

import hamster.module.SqlScriptResource;
import hamster.module.SqlScript;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Configuration
public class CoreCommonConfig implements InitializingBean {

    @Autowired
    DataSource dataSource;
    @Autowired(required = false)
    List<SqlScript> sqlScripts;

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator());
        return dataSourceInitializer;
    }

    @Bean
    @Profile("DB_H2")
    public SqlScript h2CoreCommonScript() {
        return new SqlScriptResource(0, "db/h2/common.sql");
    }

    @Bean
    public ResourceDatabasePopulator resourceDatabasePopulator() {
        return new ResourceDatabasePopulator();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(sqlScripts != null){
            Collections.sort(sqlScripts);
            for(SqlScript r : sqlScripts){
                resourceDatabasePopulator().addScript(r);
            }
        }
    }
}
