package hamster.test;

import org.apache.commons.lang3.StringUtils;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class IntegrationExecutionListener implements TestExecutionListener {

    private IDataSet typeDataSet;
    private IDatabaseTester databaseTester;

    @Override
    public void afterTestClass(TestContext arg0) throws Exception {
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if (databaseTester != null) {
            databaseTester.onTearDown();
        }
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        DataSets dataSets = chooseClassDataSets(testContext.getTestClass(), testContext.getTestClass().getSuperclass());
        if (dataSets == null) {
            return;
        }
        typeDataSet = load(testContext, dataSets);
    }

    private DataSets chooseClassDataSets(Class<?> ... classes){
        for(Class<?> c : classes){
            DataSets dataSets = c.getAnnotation(DataSets.class);
            if (dataSets != null && !StringUtils.isEmpty(dataSets.setUpDataSet())) {
                return dataSets;
            }
        }
        return null;
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        IDataSet dataSet = compositeDataSet(testContext, typeDataSet, testContext.getTestMethod().getAnnotation(DataSets.class));
        if (dataSet == null) {
            return;
        }
        databaseTester = testContext.getApplicationContext().getBean(IntegrationConfig.DATABASE_TESTER_BEAN, IDatabaseTester.class);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    private IDataSet compositeDataSet(TestContext testContext, IDataSet typeDataSet, DataSets methodDataSets) throws DataSetException {
        if (methodDataSets == null || StringUtils.isEmpty(methodDataSets.setUpDataSet())) {
            return typeDataSet;
        }
        IDataSet methodDataSet = load(testContext, methodDataSets);
        if (typeDataSet == null) {
            return methodDataSet;
        }
        return new CompositeDataSet(typeDataSet, methodDataSet);
    }

    @Override
    public void prepareTestInstance(TestContext arg0) throws Exception {
    }

    private IDataSet load(TestContext testContext, DataSets dataSets) {
        XlsDataFileLoader loader = testContext.getApplicationContext().getBean(
                IntegrationConfig.XLS_DATA_FILE_LOADER_BEAN,
                XlsDataFileLoader.class);
        return loader.load(dataSets.setUpDataSet());

    }
}
