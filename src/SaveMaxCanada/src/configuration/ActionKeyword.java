package SaveMaxCanada.src.configuration;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import executionEngine.KeywordDriver;

import org.openqa.selenium.JavascriptExecutor;

public class ActionKeyword {
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static String Title = null, Text = null, Attribute = null, currentWinHandle = null;
	public static WebElement webElementSpan;
	public static JavascriptExecutor js = null;
	public static List<WebElement> imageList = new ArrayList<WebElement>();
	public static List<WebElement> elementList = new ArrayList<WebElement>();
	
	public static ArrayList<String> winHandleList;

	public static void launchBrowser(String BrowserName, String DriverPath) {
		try {
			switch (BrowserName) {
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", DriverPath);
				driver = new ChromeDriver();
				break;
			case "Firefox": // Driver and Browser compatibility issue
				System.setProperty("webdriver.gecko.driver", DriverPath);
				driver = new FirefoxDriver();
				break;
			case "Edge": // Driver issue
				System.setProperty("webdriver.edge.driver", DriverPath);
				driver = new EdgeDriver();
				break;
			case "HeadlessChrome":
				System.setProperty("webdriver.chrome.driver", DriverPath);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				driver = new ChromeDriver(options);
				break;
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			System.out.println("openBrowserURL: " + e);
		}
	}

	
	
	public static void openURL(String URL) {
		try {
			driver.get(URL);
		} catch (Exception e) {
			KeywordDriver.bResult = false;
			System.out.println("openURL: " + e);
		}
	}
	
	public static void pageLoadWait(int waitTimeInSeconds) {
		try {       
			driver.manage().timeouts().pageLoadTimeout(waitTimeInSeconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			KeywordDriver.bResult = false;
			System.out.println("pageLoadWait: " + e);
		}
	}

//	public static void expectedWait(int waitTimeInSecond, String URL) {
//		
//		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecond));
//		wait.until(ExpectedConditions.urlToBe(URL));
//	}
	
	public static void refreshPage() {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("refreshPage: " + e);
		}
	}

	public static void backPage() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			System.out.println("backPage: " + e);
		}
	}

	public static void openNewTab() {
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("window.open()");
		} catch (Exception e) {
			System.out.println("openNewTab: " + e);
		}
	}
	
	public static void closeNewTab() {
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("window.close()");
		} catch (Exception e) {
			System.out.println("openNewTab: " + e);
		}
	}

	public static void getCurrentWinHandle() {
		try {
			currentWinHandle = driver.getWindowHandle();
		} catch (Exception e) {
			System.out.println("getCurrentWinHandle: " + e);
		}
	}

	public static void getListWinHandle() {
		try {
			winHandleList = new ArrayList<String>(driver.getWindowHandles());
		} catch (Exception e) {
			System.out.println("getListWinHandle: " + e);
		}
	}

	public static void switchWinHandle(int wHandle) {
		try {
			driver.switchTo().window(winHandleList.get(wHandle));
		} catch (Exception e) {
			System.out.println("getListWinHandle: " + e);
		}
	}

	public static String getTitle() {
		try {
			Title = driver.getTitle();
		} catch (Exception e) {
			KeywordDriver.bResult = false;
			System.out.println("getTitle: " + e);
		}
		return Title;
	}

	public static String getText(String Object) {
		try {
			Text = driver.findElement(By.xpath(Object)).getText();
		} catch (Exception e) {
			System.out.println("getText: " + e);
		}
		return Text;
	}

	public static String getAttributeByXpath(String Object, String Data) {
		try {
			Attribute = driver.findElement(By.xpath(Object)).getAttribute(Data);
		} catch (Exception e) {
			KeywordDriver.bResult = false;
			System.out.println("getAttribute: " + e);
		}
		return Attribute;
	}

	public static String getAttributeByTagName(String Object, String Data) {
		try {
			Attribute = driver.findElement(By.tagName(Object)).getAttribute(Data);
		} catch (Exception e) {
			KeywordDriver.bResult = false;
			System.out.println("getAttribute: " + e);
		}
		return Attribute;
	}

	public static void clickByXpath(String Object) {
		try {
			driver.findElement(By.xpath(Object)).click();
		} catch (Exception e) {
			System.out.println("clickByXpath: " + e);
		}
	}

	public static void clickByID(String Object) {
		try {
			driver.findElement(By.id(Object)).click();
		} catch (Exception e) {
			System.out.println("clickByID: " + e);
		}
	}

	public static void clickByName(String Object) {
		try {
			driver.findElement(By.name(Object)).click();
		} catch (Exception e) {
			System.out.println("clickByName: " + e);
		}
	}

	public static void clearByXpath(String Object) {
		try {
			driver.findElement(By.xpath(Object)).clear();
		} catch (Exception e) {
			System.out.println("clearByXpath: " + e);
		}
	}

	public static void clearByID(String Object) {
		try {
			driver.findElement(By.id(Object)).clear();
		} catch (Exception e) {
			System.out.println("clearByID: " + e);
		}
	}

	public static void clearByName(String Object) {
		try {
			driver.findElement(By.name(Object)).clear();
		} catch (Exception e) {
			System.out.println("clearByName: " + e);
		}
	}

	public static void sendByXpath(String Object, String Data) {
		try {
			driver.findElement(By.xpath(Object)).sendKeys(Data);
		} catch (Exception e) {
			System.out.println("sendByXpath: " + e);
		}
	}

	public static void sendByID(String Object, String Data) {
		try {
			driver.findElement(By.id(Object)).sendKeys(Data);
		} catch (Exception e) {
			System.out.println("sendByID: " + e);
		}
	}

	public static void sendByName(String Object, String Data) {
		try {
			driver.findElement(By.name(Object)).sendKeys(Data);
		} catch (Exception e) {
			System.out.println("sendByName: " + e);
		}
	}

	public static void selectByVisibleText(String Object, String Data) {
		try {
			Select dropdownobj = new Select(driver.findElement(By.xpath(Object)));
			dropdownobj.selectByVisibleText(Data);
		} catch (Exception e) {
			System.out.println("selectByVisibleText: " + e);
		}
	}

	public static void selectByValue(String Object, String Data) {
		try {
			Select dropdownobj = new Select(driver.findElement(By.xpath(Object)));
			dropdownobj.selectByValue(Data);
		} catch (Exception e) {
			System.out.println("selectByValue: " + e);
		}
	}

	public static void selectByIndex(String Object, int Data) {
		try {
			Select dropdownobj = new Select(driver.findElement(By.xpath(Object)));
			dropdownobj.selectByIndex(Data);
		} catch (Exception e) {
			System.out.println("selectByIndex: " + e);
		}
	}

	public static boolean elementDisplayed(String Object) {
		boolean isPresent = false;
		try {
			isPresent = driver.findElement(By.xpath(Object)).isDisplayed();
		} catch (Exception e) {
			System.out.println("elementDisplayed: " + e);
		}
		return isPresent;
	}

	public static void getElementList(String Object) {
		try {
			elementList.clear();
			elementList = driver.findElements(By.xpath(Object));
		} catch (Exception e) {
			System.out.println("getElementList: " + e);
		}
	}

	public static void getImgList() {
		try {
			imageList.clear();
			imageList = driver.findElements(By.tagName("img"));
		} catch (Exception e) {
			System.out.println("getImgList: " + e);
		}
	}

	public static void getImgList(String Object) {
		try {
			imageList.clear();
			imageList = driver.findElements(By.xpath(Object));
		} catch (Exception e) {
			System.out.println("getImgListbyClass: " + e);
		}
	}

	public static void pressEnter() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			System.out.println("pressEnter: " + e);
		}
	}

	public static void pressEsc() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (Exception e) {
			System.out.println("pressEsc: " + e);
		}
	}

	public static void pressTab() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
		} catch (Exception e) {
			System.out.println("pressEsc: " + e);
		}
	}

	public static void scrollToView(WebElement Object) {
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Object);
		} catch (Exception e) {
			System.out.println("scrollToView: " + e);
		}
	}

	public static void scrollByPixel() {
		try {
			js = (JavascriptExecutor) driver;
			for (int second = 0;; second++) {
				if (second >= 60) {
					break;
				}
				js.executeScript("window.scrollBy(0,400)");
			}
		} catch (Exception e) {
			System.out.println("scrollByPixel: " + e);
		}
	}

	public static void scrollToFooter() {
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
			System.out.println("scrollToFooter: " + e);
		}
	}

	public static void scrollToHeader() {
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		} catch (Exception e) {
			System.out.println("scrollToHeader: " + e);
		}
	}

	public static void multiClick(WebElement element) {
		try {
			while (element.isDisplayed()) {
				element.click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("multiClick: " + e);
		}
	}

	public static void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("closeBrowser: " + e);
		}
	}

	public static void quiteBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("quiteBrowser: " + e);
		}
	}

	public static void removeElementFromDOMByID(String Object) {
		try {
			if (driver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) driver;
			}
			js.executeScript("return document.getElementById(" + Object + ").remove();");
		} catch (Exception e) {
			System.out.println("removeElementFromDOM: " + e);
		}
	}
}