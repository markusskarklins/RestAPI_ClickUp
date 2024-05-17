

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/main/resources/features",
  glue = "com.apitest",
  plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class CucumberTest {
}
