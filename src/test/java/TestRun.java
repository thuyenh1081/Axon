import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(	plugin = {
//        "json:target/Reports/cucumber-reports/reports.json",
//        "junit:target/Reports/cucumber-reports/Cucumber.xml",
        "html:target/Reports/cucumber-reports/report.html"
},
        features={"src/test/resources/features/Weather.feature"}
        ,glue = {"stepDefinitions"})
public class TestRun extends AbstractTestNGCucumberTests {
}
