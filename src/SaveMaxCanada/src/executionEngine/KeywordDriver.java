package executionEngine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import configuration.Constants;
import externalUtility.ExcelUtils;
import featureToTest.*;

public class KeywordDriver {
	public static Properties properties;
	public static String URL, ExpectedTitle, ExpectedHeading;
	public static boolean bResult;

	public static void ObjectRepositoryReader() throws IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(Constants.PathOR));
			properties = new Properties();
			properties.load(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("ObjectRepositoryReader: " + e);
		}
	}

	public static void main(String[] args) throws Exception {
		ObjectRepositoryReader();
		ExcelUtils.setExcelFile(Constants.PathTestData);
		// KeywordDriver startExecution = new KeywordDriver();
		// startExecution.executeSEOVerification();
		// SiteDownTest.siteDownTest();
		//SiteDownMail.siteDownMail();
		// PageLoadTime.pageLoadTime();
		// ThumbnailVerification.imageVerification();
		// AltTagVerification.altTagVerification();
		// SendEmail.sendEmail();
		MHRera.mhRERA();
		
		
	}

	private void executeSEOVerification() throws Exception {
		int totalTestURLs = ExcelUtils.getRowCount(Constants.SheetTestCases);
		for (int testcase = 1; testcase <= totalTestURLs; testcase++) {
			bResult = true;
			URL = ExcelUtils.getCellData(testcase, Constants.URLs, Constants.SheetTestCases);
			ExpectedTitle = ExcelUtils.getCellData(testcase, Constants.ExpectedTitle, Constants.SheetTestCases);
			ExpectedHeading = ExcelUtils.getCellData(testcase, Constants.ExpectedHeading, Constants.SheetTestCases);
			SEOVerification.seoVerification();
			if (bResult == true) {
				ExcelUtils.setCellData(SEOVerification.ActualTitle, testcase, Constants.ActualTitle,
						Constants.SheetTestCases);
				ExcelUtils.setCellData(SEOVerification.ActualHeading, testcase, Constants.ActualHeading,
						Constants.SheetTestCases);
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, testcase, Constants.Result, Constants.SheetTestCases);
			} else {
				ExcelUtils.setCellData(SEOVerification.ActualTitle, testcase, Constants.ActualTitle,
						Constants.SheetTestCases);
				ExcelUtils.setCellData(SEOVerification.ActualHeading, testcase, Constants.ActualHeading,
						Constants.SheetTestCases);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, testcase, Constants.Result, Constants.SheetTestCases);
				ExcelUtils.setStyleFailed(testcase, Constants.Result, Constants.SheetTestCases);
			}
		}
	}
}