package com.teladoc.automation;



import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources", tags = "@regression")
public class TestRunner {
}
