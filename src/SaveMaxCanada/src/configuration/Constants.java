package SaveMaxCanada.src.configuration;

public class Constants {

	public static final String ProjectDir = System.getProperty("user.dir"); // System.getProperty("user.home");
	public static final String ProductionURL = "https://uat.savemax.com/?cn=in";
	public static final String ChromeDriver = ProjectDir + "\\Execution Drivers\\chromedriver.exe";
//	public static final String ChromeDriver = ProjectDir + "\\Resources\\chromedriver.exe";
	public static final String FirefoxDriver = ProjectDir + "\\Execution Drivers\\geckodriver.exe";
	public static final String PathOR = ProjectDir + "\\src\\configuration\\ObjectRepository.properties";
//	public static final String PathOR = ProjectDir + "\\Resources\\ObjectRepository.properties";

	public static final String ImgCmgSoonThumb = "https://savemax.com/assets/images/error-2.jpg";
	public static final String ImgCmgSoonPropDes = "https://api.savemax.com/imageservice/getImage/error-detail-2.jpg";
	public static final String ImgComingSoonClass = "errorImgModified imgtile lazyload";

	public static final String KEYWORD_FAIL = "FAILED";
	public static final String KEYWORD_PASS = "PASSED";

	// SEO
	public static final String PathTestData = ProjectDir + "\\src\\dataEngine\\SEO_Verification.xlsx";
//	public static final String PathTestData = ProjectDir + "\\SEO_Verification.xlsx";

	public static final String SheetTestCases = "seo_Verification";

	// Data Sheets Column Numbers
	public static final int URLs = 1;
	public static final int ExpectedTitle = 2;
	public static final int ExpectedHeading = 3;
	public static final int ActualTitle = 4;
	public static final int ActualHeading = 5;
	public static final int Result = 6;
}