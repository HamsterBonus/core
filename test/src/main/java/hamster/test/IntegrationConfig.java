package hamster.test;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"hamster.spring"})
public class IntegrationConfig {

    public static final String DATABASE_TESTER_BEAN = "databaseTester";
    public static final String XLS_DATA_FILE_LOADER_BEAN = "xlsDataFileLoader";

    @Autowired
    DataSource dataSource;

    @Bean(name = DATABASE_TESTER_BEAN)
    public DataSourceDatabaseTester dataSourceDatabaseTester() {
        return new DataSourceDatabaseTester(dataSource);
    }

    @Bean(name = XLS_DATA_FILE_LOADER_BEAN)
    public XlsDataFileLoader xlsDataFileLoader() {
        return new XlsDataFileLoader();
    }

}
