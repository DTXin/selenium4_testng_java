package org.example.listener;

import java.io.ByteArrayInputStream;

import org.example.fw_ui.manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;

public class AllureListener extends DriverManager implements TestLifecycleListener {

    @Override
    public void afterTestSchedule(TestResult result) {
    }

    @Override
    public void afterTestStart(TestResult result) {
    }

    @Override
    public void afterTestStop(TestResult result) {
    }

    @Override
    public void afterTestUpdate(TestResult result) {
    }

    @Override
    public void afterTestWrite(TestResult result) {
    }

    @Override
    public void beforeTestSchedule(TestResult result) {
    }

    @Override
    public void beforeTestStart(TestResult result) {
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            if (getDriver() != null) {
                Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(
                        ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES)));
            }
        }
    }

    @Override
    public void beforeTestUpdate(TestResult result) {
    }

    @Override
    public void beforeTestWrite(TestResult result) {
    }

}
