/*
 * Copyright (c) 2010-2011 Ardesco Solutions - http://www.ardescosolutions.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lazerycode.ebselen;

import java.util.*;

import org.junit.Ignore;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumJUnitRunner extends BlockJUnit4ClassRunner {

    //TODO work in test status to see if things should be run.

    public static TestReports reportData;
    public static final Logger logger = LoggerFactory.getLogger(SeleniumJUnitRunner.class);
    public static SeleniumTestAnnotations.TestSuiteStatus suiteStatus;

    public SeleniumJUnitRunner(Class<?> klass) throws InitializationError {
        super(klass);
        try {
            reportData = new TestReports();
        } catch (Exception ex) {
            throw new InitializationError(ex);
        }
    }

    @Override
    public void run(final RunNotifier notifier) {
        suiteStatus = getTestClass().getJavaClass().getAnnotation(SeleniumTestAnnotations.TestSuiteStatus.class);
        reportData.setTestSuiteName(getTestClass().getJavaClass().getSimpleName());
        reportData.setSuiteStatus(suiteStatus.value());
        reportData.setTestSuiteAuthor(getTestClass().getJavaClass().getAnnotation(SeleniumTestAnnotations.TestAuthor.class).value());
        reportData.setAssociatedStories(getTestClass().getJavaClass().getAnnotation(SeleniumTestAnnotations.TestStoriesCovered.class).value());
        long testSuiteStartTime = System.currentTimeMillis();
        SeleniumJUnitRunner.super.run(notifier);
        reportData.setTestSuiteTime(System.currentTimeMillis() - testSuiteStartTime);
        reportData.createReport();
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> classMethods = getTestClass().getAnnotatedMethods(SeleniumTestAnnotations.SeleniumTest.class);
        SortedMap<Integer, FrameworkMethod> sortedTestMethodList = new TreeMap<Integer, FrameworkMethod>();
        for (FrameworkMethod seleniumTest : classMethods) {
            if (seleniumTest.getAnnotation(SeleniumTestAnnotations.Order.class) != null) {
                sortedTestMethodList.put(seleniumTest.getAnnotation(SeleniumTestAnnotations.Order.class).value(), seleniumTest);
            }
        }
        return new ArrayList<FrameworkMethod>(sortedTestMethodList.values());
    }


    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        EachTestNotifier eachNotifier = makeNotifier(method, notifier);
        TestData seleniumTestData = new TestData();
        seleniumTestData.setTestName(method.getName());
        long startTime = System.currentTimeMillis();
        if (method.getAnnotation(Ignore.class) != null) {
            runIgnored(eachNotifier);
        } else {
            seleniumTestData.addFailure(runNotIgnored(method, eachNotifier));
        }
        seleniumTestData.setTimeTaken(System.currentTimeMillis() - startTime);
        logger.info("Test {} run in {}", method.getName(), seleniumTestData.getTimeTaken());
        reportData.addTestData(method.getAnnotation(SeleniumTestAnnotations.Order.class).value(), seleniumTestData);
    }

    private int runNotIgnored(FrameworkMethod method, EachTestNotifier eachNotifier) {
        int failures = 0;
        eachNotifier.fireTestStarted();
        try {
            methodBlock(method).evaluate();
        } catch (AssumptionViolatedException e) {
            eachNotifier.addFailedAssumption(e);
            logger.error("Test {} failed!", method.getName());
            failures++;
        } catch (Throwable e) {
            eachNotifier.addFailure(e);
            logger.error("Test {} failed!", method.getName());
            failures++;
        } finally {
            eachNotifier.fireTestFinished();
        }
        return failures;
    }

    private void runIgnored(EachTestNotifier eachNotifier) {
        eachNotifier.fireTestIgnored();
    }

    private EachTestNotifier makeNotifier(FrameworkMethod method,
                                          RunNotifier notifier) {
        Description description = describeChild(method);
        return new EachTestNotifier(notifier, description);
    }
}
