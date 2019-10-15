import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;

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

	/**
	 *
	 */
	@Test
	public void keywordSearchTest() {
		$("#_ctl0_CPH1_Searcher_txtKeyword").sendKeys("bridgestone");
		$("#pr_search > dd:nth-child(8) > input").click();

		ElementsCollection results = $$(".item-common");
		results.shouldHaveSize(20);
		results.get(0).shouldHave(text("BRIDGESTONE"));
	}

	@Test
	public void carSearchTest() {
		$("#pr_search > dd:nth-child(2) > a.thickbox > div").click();
		switchTo().frame("TB_iframeContent");
		$("#ddlMaker").selectOption("トヨタ");
		$("#ddlModel").selectOption("86");
		$("body > div.model_searchbtn > a:nth-child(2) > img").click();
		$("#chkModelGroup_3_10248").setSelected(true);
		$("#Form1 > div.modelcode_footer > div.modelcode_searchbtn > p > a > img").click();
		$(".content-title-20170809").shouldHave(text("パーツ・商品 - 86 ［ ZN6 ］"));
	}

}
