package featureToTest;

import configuration.ActionKeyword;
import configuration.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MHRera {
	static String filePath = "C:\\Users\\RohitKumarPandey\\Desktop\\certificates.txt";

	public static void mhRERA() throws InterruptedException {

		try {
			ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);
			ActionKeyword.pageLoadWait(5000);
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String certificateNumber;
			while ((certificateNumber = reader.readLine()) != null) {
				certificateNumber = certificateNumber.trim();
				String URL = "https://maharerait.mahaonline.gov.in/searchlist/search?CertficateNo=" + certificateNumber;
				ActionKeyword.openURL(URL);
				ActionKeyword.clickByXpath("//*[@data-docname=\"" + certificateNumber + "\"]");
				Thread.sleep(3000);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	ActionKeyword.quiteBrowser();
	}
}
