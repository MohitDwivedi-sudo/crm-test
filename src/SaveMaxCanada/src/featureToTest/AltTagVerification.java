package featureToTest;

import org.openqa.selenium.WebElement;
import configuration.*;

public class AltTagVerification {

	public static String ImageAltText, ImageSrcURL;

	public static void altTagVerification() {
		ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);
		ActionKeyword.openURL(Constants.ProductionURL);

		ActionKeyword.getImgList();
		for (WebElement img : ActionKeyword.imageList) {
			ImageSrcURL = img.getAttribute("src");
			ImageAltText = img.getAttribute("alt");
			System.out.println(ImageSrcURL + "#" + ImageAltText);
		}
	}
}