package featureToTest;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.openqa.selenium.WebElement;

import configuration.ActionKeyword;
import configuration.Constants;

public class SiteDownTest {

	public static void siteDownTest() throws InterruptedException {
		ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);
		try {
			ActionKeyword.openURL(Constants.ProductionURL);
			for (int i = 0; i >= 0; i++) {
				ActionKeyword.refreshPage();
				ActionKeyword.getImgList();
				if (ActionKeyword.imageList.size() <= 5) {
					for (WebElement img : ActionKeyword.imageList) {
						if (img != null) {
							if (img.getAttribute("src").equalsIgnoreCase(
									"https://savemax.com/blogs/wp-content/uploads/2021/05/Group1543.png")) {
								File audioFile = new File("C:\\Users\\RohitKumarPandey\\Downloads\\alarm-tone.wav");
								AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
								Clip audioClip = AudioSystem.getClip();
								audioClip.open(audioStream);
								audioClip.loop(Clip.LOOP_CONTINUOUSLY);
								Thread.sleep(3600000);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}