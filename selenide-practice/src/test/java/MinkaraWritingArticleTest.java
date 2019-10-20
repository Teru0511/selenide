import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;

import com.codeborne.selenide.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MinkaraWritingArticleTest {
	private String articleName = LocalDateTime.now().toString();

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
		$("#_ctl0_CPH2_MainEdit2_Edit1_TextBox_Title").sendKeys("Blog" + articleName);
		$("#_ctl0_CPH2_MainEdit2_Edit1_FileUpLoad_ImageOldSupport1_FileUpload1")
			.sendKeys("/Users/teru/git/selenide-sample/selenide-practice/img/cat005.jpg");
		$("#_ctl0_CPH2_MainEdit2_Edit1_TextBox_Body").sendKeys("明日もいい日でありますように");
		$("#_ctl0_CPH2_MainEdit2_Edit1_TagEntry1_boxTag").sendKeys("異世界 アニメ");
		$("#_ctl0_CPH2_MainEdit2_Edit1_ddlPublishScope").selectOption("みん友まで公開");
		$("#_ctl0_CPH2_MainEdit2_Edit1_Button_Commit").click();
		$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));

		//記事が投稿したか否かを確認する
		$("#_ctl0_CPH2_MainThanks_HL_Nav1").click();
		$("h1.contentsTitle").shouldHave(text("Blog" + articleName));

		//投稿を削除する
		//$(withText("編集")).click();
		//$("#_ctl0_CPH2_MainEdit2_Edit1_Button_Delete").click();
		//confirm();
		//$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));
	}


}
