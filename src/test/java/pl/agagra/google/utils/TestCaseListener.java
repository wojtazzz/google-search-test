package pl.agagra.google.utils;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


/**
 * Created with IntelliJ IDEA.
 * User: Wojtas
 * Date: 20.12.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class TestCaseListener implements ITestListener {

    Logger LOG = Logger.getLogger(TestCaseListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOG.info("Test case started: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOG.info("Test case passed! " + iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOG.info("Test case failed! " + iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOG.info("Test case skipped! " + iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOG.info("=====Test suite started!======");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOG.info("=====Test suite finished!======");
    }
}