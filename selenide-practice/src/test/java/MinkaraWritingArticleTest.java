import static com.codeborne.selenide.Selenide.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;

import com.codeborne.selenide.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MinkaraWritingArticleTest {

	@BeforeClass
	public static void beforeClass() {
		WebDriverManager.chromedriver().setup();
		Configuration.browser = BrowserType.CHROME;
		//Configuration.headless = true;
	}

	@Before
	public void setUp() {
		open("https://minkara.carview.co.jp");
		$("#head_top_login").click();
		$("#ctl00_ContentPlaceHolder1_NewLoginBox1_Text_Accont").sendKeys("mint01");
		$("#ctl00_ContentPlaceHolder1_NewLoginBox1_Text_Password").sendKeys("qatest1");
		$("#ctl00_ContentPlaceHolder1_NewLoginBox1_ImageButton_Login").click();
		$("#head_top_btn_mypage").click();
	}

	@Test
	public void testWritingBlog() {
		$("#mypage_navi_blog").click();

	}


}
