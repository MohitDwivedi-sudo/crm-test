package featureToTest;

import configuration.*;
import executionEngine.KeywordDriver;
import configuration.Constants;
import static executionEngine.KeywordDriver.properties;

public class SEOVerification {

	public static String ActualTitle = null, ActualHeading = null, PropertyCount = null;

	public static void seoVerification() throws InterruptedException {
		ActionKeyword.launchBrowser("HeadlessChrome", Constants.ChromeDriver);
		if (KeywordDriver.URL.isEmpty() == false) {
			ActionKeyword.openURL(KeywordDriver.URL);
			if (KeywordDriver.ExpectedTitle.isEmpty() == false) {
				PropertyCount = ActionKeyword.getText(properties.getProperty("PropertyCount")).replaceAll("[a-zA-Z:, ]",
						"");
				ActualTitle = ActionKeyword.getTitle();
				if (ActualTitle.contains(PropertyCount) || Integer.parseInt(PropertyCount) == 0) {
					if (!KeywordDriver.ExpectedTitle.equalsIgnoreCase(ActualTitle.replaceAll("[0-9]", "").trim())) {
						KeywordDriver.bResult = false;
					}
				} else {
					KeywordDriver.bResult = false;
				}
			} else {
				KeywordDriver.bResult = false;
			}
			if (KeywordDriver.ExpectedHeading.isEmpty() == false) {
				ActualHeading = ActionKeyword.getText(properties.getProperty("BuyH1"));
				if (!KeywordDriver.ExpectedHeading.equalsIgnoreCase(ActualHeading)) {
					KeywordDriver.bResult = false;
				}
			} else {
				KeywordDriver.bResult = false;
			}
		} else {
			KeywordDriver.bResult = false;
		}
		Thread.sleep(700);
		ActionKeyword.closeBrowser();
	}
}