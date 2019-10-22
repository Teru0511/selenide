import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

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

		//投稿が正常に完了したのかどうかを確認する
		$("#_ctl0_CPH2_MainThanks_HL_Nav1").click();
		$("h1.contentsTitle").shouldHave(text("Blog" + articleName));

		//投稿を削除する
		$(withText("編集")).click();
		$("#_ctl0_CPH2_MainEdit2_Edit1_Button_Delete").click();
		confirm();
		$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));
	}

	@Test
	public void testWritingParts() {
		$("#mypage_navi_parts").click();
		$("#_ctl0_CPH1_btnNewEditCarContents").click();
		$("#mainImageArea > div > div > div > input.inputImage")
			.sendKeys("/Users/teru/git/selenide-sample/selenide-practice/img/cat005.jpg");
		$("#partsSelect").click();
		$("div.category").click();
		$("#categoryLargeCategoryList > li:nth-child(1)").click();
		$("#categoryLargeCategoryList > li.large_category_item.active > ul > li:nth-child(2)").click();
		$("#txtMakerSearch").sendKeys("bridgestone");
		$$("#makerSearchResultList > li").find(text("BRIDGESTONE")).click();
		$("#txtPartsSearch").sendKeys("re070");
		$$("#partsSearchResultList > li").find(text("POTENZA RE070 225/40R18")).click();
		$("#btnPartsFinish").click();
		$("#mainText").sendKeys("パーツ投稿テスト");
		$("#appendImageArea > div > div > div > input.inputImage")
			.sendKeys("/Users/teru/git/selenide-sample/selenide-practice/img/cat006.jpg");
		$("#purchaseRouteType").selectOption(2);
		$("#purchaseRoute").selectOption(3);
		$("#purchaseRouteMemo").sendKeys("割引10%");
		$("#purchasePrice").sendKeys("900");
		$("#regularPrice").sendKeys("1000");
		$("#recommendAdd").click();
		$(".clipped").click();
		$("#recommendClipList > li:nth-child(2) > input").click();
		$(".ui-widget-content.ui-autocomplete-input").sendKeys("Test ");
		$("#post").click();
		confirm();

		//投稿が正常に完了したのかどうかを確認する
		$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));
		String partsURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		partsURL = partsURL.replace( "thanks.aspx?type=1", "parts.aspx");
		open(partsURL);
		$(".userCarPhotoMemo").shouldHave(text("パーツ投稿テスト"));

		//投稿を削除する
	  	$(withText("編集")).click();
	  	$("#delete").click();
	  	confirm();
	}

	@Test
	public void testWritingNote() {
		$("#mypage_navi_note").click();
		$("#_ctl0_CPH1_btnNewEditCarContents").click();
		$(withText("ホイール補修")).click();
		$("#_ctl0_CPH2_MainNoteEdit2_boxTitle").sendKeys("整備手帳" + articleName);
		switchTo().frame("_ctl0_CPH2_MainNoteEdit2_iframeImage_1");
		$("#fileupload > div.div_buttons > div > div > input")
			.sendKeys("/Users/teru/git/selenide-sample/selenide-practice/img/cat006.jpg");
		switchTo().parentFrame();
		$("#_ctl0_CPH2_MainNoteEdit2_boxComment_1").sendKeys("テスト用ネコ");
		$("#_ctl0_CPH2_MainNoteEdit2_btnCheck").click();
		$("#_ctl0_CPH2_MainNoteEdit2_But_PreviewEntry").click();

		//投稿が正常に完了したのかどうかを確認する
		$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));
		String noteURL = WebDriverRunner.getWebDriver().getCurrentUrl();
		noteURL = noteURL.replace( "thanks.aspx?type=2", "note.aspx");
		open(noteURL);
		$("h1.contentsTitle").shouldHave(text("整備手帳" + articleName));

		//投稿を削除する
	  	$(withText("編集")).click();
	  	$("#_ctl0_CPH2_MainNoteEdit2_btnDelete").click();
	  	confirm();
	  	$("#_ctl0_CPH2_MainNoteEdit2_btnFullDelete").click();
	  	$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));
	}

	@Test
	public void testWritingCarReview() {
		$("#mypage_navi_review").click();
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_TextBox_Title").sendKeys("Review" + articleName);
		$("#aSalesGradeList").click();
		switchTo().window(WebDriverRunner.getWebDriver().getWindowHandle());
		switchTo().frame("TB_SalesGradeList_iframeContent");
		$(withText("バイク")).click();
		$(withText("カワサキ")).click();
		$(withText("Ninja250R")).click();
		$("input.submit-button").click();
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_ddlModelYearNone").selectOption(1);
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_boxModelGradeNone").sendKeys("不明");
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_radRide_TestDrive").selectRadio("radRide_TestDrive");
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_TextBox_Satisfaction").sendKeys("視界が広く運転しやすい");
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_TextBox_Dissatisfaction").sendKeys("荷室が狭い");
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_TextBox_Overall").sendKeys("良いバイクだ");
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_FileUpLoad_ImageOldSupport1_FileUpload1")
			.sendKeys("/Users/teru/git/selenide-sample/selenide-practice/img/cat006.jpg");
		$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_Button_Commit").click();

		//投稿が正常に完了したのかどうかを確認する
		$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));
		$(withText("記事へ")).click();
		$("h1.contentsTitle").shouldHave(text("Review" + articleName));

		//投稿を削除する
	  	$(withText("編集")).click();
	  	$("#_ctl0_CPH2_MainReviewEdit2_ReviewEdit1_Button_Delete").click();
	  	confirm();
	  	$(".Message01").shouldHave(text("ありがとうございます。投稿を受け付けました。"));
	}

}
