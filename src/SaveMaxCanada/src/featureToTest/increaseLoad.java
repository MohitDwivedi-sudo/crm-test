package featureToTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import configuration.Constants;

import java.util.ArrayList;
import java.util.List;

public class increaseLoad {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Constants.ChromeDriver);

        List<WebDriver> drivers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            switch (i) {
                case 0:
                    driver.get("https://savemax.com/?cn=in");
                    break;
                case 1:
                    driver.get("https://savemax.com/?cn=in");
                    break;
                case 2:
                    driver.get("https://savemax.com/?cn=in");
                    break;
                case 3:
                    driver.get("https://savemax.com/?cn=in");
                    break;
                case 4:
                    driver.get("https://savemax.com/?cn=in");
                    break;
            }

            drivers.add(driver);
        }
        while (true) {
            try {
                Thread.sleep(500);
                for (WebDriver driver : drivers) {
                    driver.navigate().refresh();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

