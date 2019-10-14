import static com.codeborne.selenide.Selenide.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;

import com.codeborne.selenide.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelenideSampleTest {

    @BeforeClass
    public static void beforeClass() {
    	WebDriverManager.chromedriver().setup();
        Configuration.browser = BrowserType.CHROME;
        //Configuration.headless = true;
    }

    @Test
    public void testGoogleSearch() {
        open("http://google.com");
        $("[name=q]").val("ChromeDriver").submit();
    }

}