package dwbi.proiect_dwbi;

import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        tags = {"@cii"})

public class TestRunner {
}
