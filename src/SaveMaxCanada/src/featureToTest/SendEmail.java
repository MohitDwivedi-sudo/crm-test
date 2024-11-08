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

public class SendEmail {
	public static void sendEmail() {
		ActionKeyword.launchBrowser("Chrome", Constants.ChromeDriver);
		ActionKeyword.pageLoadWait(15);

		long refreshInterval = 120000; // milliseconds
		int refreshCount = 0;

		 while (true) {
	            try {
	                ActionKeyword.openURL(Constants.ProductionURL);
	                
	                ActionKeyword.getImgList();
					if (ActionKeyword.imageList.size() <= 5) {
						for (WebElement img : ActionKeyword.imageList) {
							if (img != null) {
								if (img.getAttribute("src").equalsIgnoreCase(
										"https://savemax.com/blogs/wp-content/uploads/2021/05/Group1543.png")) {
									sendEmail1();
				                    break;
								}
							}
						}
					}
	                
	            } catch (TimeoutException e) {
	                System.out.println("Page did not load within the specified time.");
	                refreshCount++;
	                if (refreshCount >= 3) {
	                    sendEmail1();
	                    break;
	                }
	            }
	        
			try {
				Thread.sleep(refreshInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ActionKeyword.quiteBrowser();
	}

	public static void sendEmail1() {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://uatapi.savemax.com:8081/authentication/sendEmail");

		httpPost.addHeader("Content-Type", "application/json");

		String jsonPayload = "{\"emailIds\":[\"rohit.pandey@savemax.com\"],\"message\":\"Hi Rohit, Production savemax.com is down!\",\"subject\":\"Site savemax.com is down!\"}";
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