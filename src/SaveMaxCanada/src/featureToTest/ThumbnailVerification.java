package featureToTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import configuration.*;
import static executionEngine.KeywordDriver.properties;

public class ThumbnailVerification {
	public static String PropertyDetails;
	public static String PropertyMLS;

	public static void thumbnailVerification() {
		try {
			ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);
			ActionKeyword.openURL(Constants.ProductionURL);
			ActionKeyword.sendByXpath(properties.getProperty("HomeSearch"), "Toronto"); // Caledon, Windsor
			Thread.sleep(2000);
			ActionKeyword.pressEnter();
			Thread.sleep(20000);
			home();
			buy();
			rent();
		} catch (Exception e) {
			System.out.println("Exception: imageVerification.java : " + e);
		}
	}

	public static void home() throws InterruptedException {
		try {
			removeChatbotDOMElements();
			ActionKeyword.scrollToFooter();
			Thread.sleep(1000);
			ActionKeyword.scrollToHeader();

			ActionKeyword.getElementList(properties.getProperty("NextArrow"));
			for (WebElement ele : ActionKeyword.elementList) {
				ActionKeyword.multiClick(ele);
			}

			ActionKeyword.getImgList(properties.getProperty("HomeItems"));
			for (WebElement img : ActionKeyword.imageList) {
				if (img != null) {
					if (img.findElement(By.tagName("img")).getAttribute("src")
							.equalsIgnoreCase(Constants.ImgCmgSoonThumb)) {
						PropertyDetails = img.findElement(By.tagName("img")).getAttribute("alt");
						PropertyMLS = img.findElement(By.tagName("a")).getAttribute("href");
						System.out.println("HREF: Home: " + PropertyMLS);

						ActionKeyword.openNewTab();
						ActionKeyword.getListWinHandle();
						ActionKeyword.switchWinHandle(1);
						ActionKeyword.openURL(PropertyMLS);
						removeChatbotDOMElements();
						if (!ActionKeyword.getAttributeByXpath(properties.getProperty("PropDesImage"), "alt")
								.equalsIgnoreCase("error-detail")) {
							PropertyMLS = PropertyMLS.substring(PropertyMLS.lastIndexOf("/") + 1, PropertyMLS.length());
							System.out.println("PropertyDetails: Home: " + PropertyDetails + " " + PropertyMLS);
						}
						ActionKeyword.closeNewTab();
						ActionKeyword.switchWinHandle(0);

					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: home: " + e);
		}
	}

	public static void buy() throws InterruptedException {
		try {
			ActionKeyword.clickByXpath(properties.getProperty("Buy"));
			Thread.sleep(20000);
			removeChatbotDOMElements();
			ActionKeyword.scrollByPixel();
			Thread.sleep(1000);
			ActionKeyword.scrollToHeader();

			for (int i = 0; i < 4; i++) {
				ActionKeyword.getImgList(properties.getProperty("BuyItems"));
				for (WebElement img : ActionKeyword.imageList) {
					if (img != null) {
						if (img.findElement(By.tagName("img")).getAttribute("src")
								.equalsIgnoreCase(Constants.ImgCmgSoonThumb)) {
							PropertyDetails = img.findElement(By.tagName("img")).getAttribute("alt");
							PropertyMLS = img.findElement(By.tagName("a")).getAttribute("href");
							System.out.println("HREF: Sale: " + PropertyMLS);

							ActionKeyword.openNewTab();
							ActionKeyword.getListWinHandle();
							ActionKeyword.switchWinHandle(1);
							ActionKeyword.openURL(PropertyMLS);
							removeChatbotDOMElements();
							if (!ActionKeyword.getAttributeByXpath(properties.getProperty("PropDesImage"), "alt")
									.equalsIgnoreCase("error-detail")) {
								PropertyMLS = PropertyMLS.substring(PropertyMLS.lastIndexOf("/") + 1,
										PropertyMLS.length());
								System.out.println("PropertyDetails: Home: " + PropertyDetails + " " + PropertyMLS);
							}
							ActionKeyword.closeNewTab();
							ActionKeyword.switchWinHandle(0);

						}
					}
				}
				if (ActionKeyword.elementDisplayed(properties.getProperty("NextEnabled"))) {
					ActionKeyword.clickByXpath(properties.getProperty("NextEnabled"));
					Thread.sleep(20000);
					ActionKeyword.scrollByPixel();
					Thread.sleep(1000);
					ActionKeyword.scrollToHeader();
				} else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: buy: " + e);
		}
	}

	public static void rent() throws InterruptedException {
		try {
			ActionKeyword.clickByXpath(properties.getProperty("Rent"));
			ActionKeyword.clickByXpath(properties.getProperty("FindaHouse"));
			Thread.sleep(20000);
			removeChatbotDOMElements();
			ActionKeyword.scrollToFooter();
			Thread.sleep(1000);
			ActionKeyword.scrollToHeader();

			for (int i = 0; i < 4; i++) {
				ActionKeyword.getImgList(properties.getProperty("RentItems"));
				for (WebElement img : ActionKeyword.imageList) {
					if (img != null) {
						if (img.findElement(By.tagName("img")).getAttribute("src")
								.equalsIgnoreCase(Constants.ImgCmgSoonThumb)) {
							PropertyDetails = img.findElement(By.tagName("img")).getAttribute("alt");
							PropertyMLS = img.findElement(By.tagName("a")).getAttribute("href");
							System.out.println("HREF: Rent: " + PropertyMLS);

							ActionKeyword.openNewTab();
							ActionKeyword.getListWinHandle();
							ActionKeyword.switchWinHandle(1);
							ActionKeyword.openURL(PropertyMLS);
							removeChatbotDOMElements();
							if (!ActionKeyword.getAttributeByXpath(properties.getProperty("PropDesImage"), "alt")
									.equalsIgnoreCase("error-detail")) {
								PropertyMLS = PropertyMLS.substring(PropertyMLS.lastIndexOf("/") + 1,
										PropertyMLS.length());
								System.out.println("PropertyDetails: Home: " + PropertyDetails + " " + PropertyMLS);
							}
							ActionKeyword.closeNewTab();
							ActionKeyword.switchWinHandle(0);

						}
					}
				}
				if (ActionKeyword.elementDisplayed(properties.getProperty("NextEnabled"))) {
					ActionKeyword.clickByXpath(properties.getProperty("NextEnabled"));
					Thread.sleep(20000);
					ActionKeyword.scrollByPixel();
					Thread.sleep(1000);
					ActionKeyword.scrollToHeader();
				} else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: rent: " + e);
		}
	}

	public static void removeChatbotDOMElements() {
		ActionKeyword.removeElementFromDOMByID(properties.getProperty("ChatbotElementID01"));
		ActionKeyword.removeElementFromDOMByID(properties.getProperty("ChatbotElementID02"));
		ActionKeyword.removeElementFromDOMByID(properties.getProperty("ChatbotElementID03"));
		ActionKeyword.removeElementFromDOMByID(properties.getProperty("ChatbotElementID04"));
	}
}