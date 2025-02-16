package org.ananya.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org/ananya/stepdefinitions"},
        tags = "not @skipped",
        plugin = {"html:target/cucumber-test-results.html", "json:target/cucumber-test-results.json"},
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
