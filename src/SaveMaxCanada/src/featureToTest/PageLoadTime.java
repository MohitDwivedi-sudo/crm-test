package featureToTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import configuration.*;

public class PageLoadTime {

	public static void pageLoadTime() {
		ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);
		long start = System.currentTimeMillis();
		ActionKeyword.openURL(Constants.ProductionURL);
		long finish = System.currentTimeMillis();
		long totalTime = finish - start;
		totalTime = (long) (totalTime/1000F); // convert to seconds
		System.out.println(totalTime+ "Seconds");
		
//		FileWriter Writer = new FileWriter(System.getProperty("user.dir") + "\\PageLoadLog.txt", true);
//		BufferedWriter BufferedWriter = new BufferedWriter(Writer);
//		DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime currentTime = LocalDateTime.now();
//		BufferedWriter.write(formatDT.format(currentTime) + " Total time to load " + args[0] + " - "
//				+ totalTime / 1000F + " Seconds" + System.lineSeparator() + System.lineSeparator());
//		BufferedWriter.close();
		
//		
//		System.setProperty("webdriver.chrome.driver", Constants.ChromeDriver);
//		ChromeOptions opt = new ChromeOptions();
//		opt.setExperimentalOption("debuggerAddress", "localhost:9222 ");
//		WebDriver driver = new ChromeDriver(opt);
	}
}