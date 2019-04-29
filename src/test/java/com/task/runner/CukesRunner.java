package com.task.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/task/step_def",
        tags =
                "@p",

        dryRun = true

)
public class CukesRunner {



}


