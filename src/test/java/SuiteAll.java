import Controller.SuiteIntegrationTest;
import e2e.SuiteAcceptanceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SuiteIntegrationTest.class, SuiteAcceptanceTest.class})
public class SuiteAll {
}
