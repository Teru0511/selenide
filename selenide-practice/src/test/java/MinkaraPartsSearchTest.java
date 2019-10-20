import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
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
		$(withText("車種を選択")).click();
		switchTo().frame("TB_iframeContent");
		$("#ddlMaker").selectOption("トヨタ");
		$("#ddlModel").selectOption("86");
		$("body > div.model_searchbtn > a:nth-child(2) > img").click();
		$("#chkModelGroup_3_10248").setSelected(true);
		$("#Form1 > div.modelcode_footer > div.modelcode_searchbtn > p > a > img").click();
		$(".content-title-20170809").shouldHave(text("パーツ・商品 - 86 ［ ZN6 ］"));
	}

	@Test
	public void categorySearchTest() {
		$(withText("カテゴリを選択")).click();
		switchTo().frame("TB_iframeContent");
		$(withText("タイヤ・ホイール")).click();
		$("#rAllCategories__ctl1_rSmallCategoryListList__ctl0_rSmallCategoryList__ctl3_lnkBtnCategory").click();

		ElementsCollection result = $$(".item-common");
		result.get(0).find("#_ctl0_CPH1_PartsCatalogList_Repeater_PartsList__ctl1_HyperLink_Category")
			.shouldHave(text("タイヤ"));
	}

	@Test
	public void makerSearchTest() {
		$(withText("パーツメーカーを選択")).click();
		switchTo().frame("TB_iframeContent");
		$("#txtPartsMaker").sendKeys("bridgestone");
		$(by("value", "144")).click();
		$(by("alt", "この条件で検索")).click();

		ElementsCollection result = $$(".item-common");
		result.get(0).find("#_ctl0_CPH1_PartsCatalogList_Repeater_PartsList__ctl1_HyperLink_Maker")
			.shouldHave(text("BRIDGESTONE"));
	}

}
