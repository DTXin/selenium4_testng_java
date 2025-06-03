package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.fw.base.BaseTest;
import org.testng.TestNG;
import org.testng.annotations.Test;

public class FailedTestCase extends BaseTest {

    @Test(description = "Run failed test case")
    public void TC01_Login_GoodPassword() {
        TestNG runner = new TestNG();
        List<String> testFiles = new ArrayList<>();
        testFiles.add("target\\surefire-reports\\testng-failed.xml");
        runner.setTestSuites(testFiles);
        runner.run();
    }

}
