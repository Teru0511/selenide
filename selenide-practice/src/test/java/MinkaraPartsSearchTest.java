import static com.codeborne.selenide.Selenide.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MinkaraPartsSearchTest {

	@BeforeClass
	public static void beforeClass() {
		WebDriverManager.chromedriver().setup();
		Configuration.browser = BrowserType.CHROME;
		//Configuration.headless = true;
	}

	@Before
	public void iniPage() {
		open("https://minkara.carview.co.jp");
		$("#top_navi_parts").click();
	}

	@Test
	public void keywordSearchTest() {
		$("#_ctl0_CPH1_Searcher_txtKeyword").sendKeys("bridgestone");
		$("#pr_search > dd:nth-child(8) > input").click();

		ElementsCollection results = $$(".item-common");
		results.shouldHaveSize(20);
		results.get(0).shouldHave(Condition.text("BRIDGESTONE"));
	}

}
