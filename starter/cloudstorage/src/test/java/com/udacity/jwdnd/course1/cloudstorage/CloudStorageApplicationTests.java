package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private String baseUrl;

	private String firstname = "Rohan", lastname = "Gupta", username = "DemonDaddy22", password = "pass6789";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.driver.get(baseUrl = "http://localhost:" + this.port);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void unauthorisedAccess() {
		// anyone can access these
		this.driver.get(this.baseUrl + "/");
		Assertions.assertEquals("Docloud", this.driver.getTitle());

		this.driver.get(this.baseUrl + "/login");
		Assertions.assertEquals("Login", this.driver.getTitle());

		this.driver.get(this.baseUrl + "/signup");
		Assertions.assertEquals("Sign Up", this.driver.getTitle());

		// accessible only by authenticated users
		this.driver.get(this.baseUrl + "/home");
		Assertions.assertEquals("Login", this.driver.getTitle());

		this.driver.get(this.baseUrl + "/files/upload");
		Assertions.assertEquals("Login", this.driver.getTitle());

		this.driver.get(this.baseUrl + "/notes/upload");
		Assertions.assertEquals("Login", this.driver.getTitle());

		this.driver.get(this.baseUrl + "/credentials/upload");
		Assertions.assertEquals("Login", this.driver.getTitle());

		this.driver.get(this.baseUrl + "/result");
		Assertions.assertEquals("Login", this.driver.getTitle());
	}

	@Test
	@Order(2)
	public void testLandingPage() throws InterruptedException {
		this.driver.get(this.baseUrl + "/");
		Assertions.assertEquals("Docloud", this.driver.getTitle());
		// Thread.sleep(1500);

		LandingPage landingPage = new LandingPage(this.driver);

		landingPage.gotoSignup(this.driver);
		Assertions.assertEquals("Sign Up", this.driver.getTitle());
		// Thread.sleep(1500);

		this.driver.get(this.baseUrl + "/");

		landingPage.gotoLogin(this.driver);
		Assertions.assertEquals("Login", this.driver.getTitle());
		// Thread.sleep(1500);
	}

}
