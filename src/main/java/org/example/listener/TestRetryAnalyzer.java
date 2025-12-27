package org.example.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.example.Config;

public class TestRetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int retryMaxLimit = Config.RETRY_LIMIT;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryMaxLimit) {
            counter++;
            return true;
        }
        return false;
    }
}
