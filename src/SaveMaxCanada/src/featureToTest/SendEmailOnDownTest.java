package featureToTest;


import configuration.ActionKeyword;
import configuration.Constants;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class SendEmailOnDownTest {
	public static long startTime, endTime, pageLoadTime;

	public static void siteDownTest() {
		ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);
		long refreshInterval = 300000;// milliseconds
//		int refreshCount = 0;

		while (true) {
			try {
				System.out.println(java.time.LocalDateTime.now());
				ActionKeyword.pageLoadWait(60);
				startTime = System.currentTimeMillis();
				ActionKeyword.openURL(Constants.ProductionURL);
				//ActionKeyword.expectedWait(60, Constants.ProductionURL);
				ActionKeyword.getImgList();
				if (ActionKeyword.imageList.size() <= 5) {
					for (WebElement img : ActionKeyword.imageList) {
						if (img != null) {
							if (img.getAttribute("src").equalsIgnoreCase(
									"https://savemax.com/blogs/wp-content/uploads/2021/05/Group1543.png")) {
								sendEmail();
								// break;
							}
						}
					}
				}
			} catch (TimeoutException e) {
				System.out.println("Page did not load within the specified time.");
				sendEmail();
//				refreshCount++;
//				if (refreshCount >= 3) {
//					sendEmail();
//					break;
//				}
			}
			endTime = System.currentTimeMillis();
			pageLoadTime = endTime - startTime;
			System.out.println("Page load time: " + (long) (pageLoadTime/1000F) + " seconds");

			try {
				Thread.sleep(refreshInterval);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException: " + e);
				e.printStackTrace();
			}
		}
//		ActionKeyword.quiteBrowser();
	}

	public static void sendEmail() {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://api.savemax.com:9091/authentication/sendEmail");

		httpPost.addHeader("Content-Type", "application/json");

		//String jsonPayload = "{\"emailIds\":[\"rohit.pandey@savemax.com\",\"nishant.bugalia@savemax.com\",\"kuldeep.singh@savemax.com\"],\"message\":\"ServerDown: savemax.com\",\"subject\":\"ServerDown\"}";
		String jsonPayload = "{\"emailIds\":[\"rohit.pandey@savemax.com\"],\"message\":\"ServerDown: savemax.com\",\"subject\":\"ServerDown\"}";
		StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON);
		httpPost.setEntity(entity);

		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			String responseBody = EntityUtils.toString(responseEntity);
			System.out.println("Response status code: " + response.getStatusLine().getStatusCode());
			System.out.println("Response body: " + responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
