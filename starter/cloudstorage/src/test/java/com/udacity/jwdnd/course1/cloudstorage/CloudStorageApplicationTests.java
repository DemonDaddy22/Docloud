package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

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

		this.driver.get(this.baseUrl + "/signup");
		Assertions.assertEquals("Sign Up", this.driver.getTitle());

		this.driver.get(this.baseUrl + "/login");
		Assertions.assertEquals("Login", this.driver.getTitle());

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

	@Test
	@Order(3)
	public void testAuthentication() {
		// check if user is redirected to login page on successful signup
		this.driver.get(this.baseUrl + "/signup");
		SignupPage signupPage = new SignupPage(this.driver);
		signupPage.registerUser(this.driver, this.firstname, this.lastname, this.username, this.password);
		Assertions.assertEquals("Login", this.driver.getTitle());

		// check if user is redirected to home page on successful login
		this.driver.get(this.baseUrl + "/login");
		LoginPage loginPage = new LoginPage(this.driver);
		loginPage.loginUser(this.driver, this.username, this.password);
		Assertions.assertEquals("Home", this.driver.getTitle());

		// check if user is successfully logged out on clicking logout button and redirected to login page
		this.driver.get(this.baseUrl + "/home");
		HomePage homePage = new HomePage(this.driver);
		homePage.logout(this.driver);
		Assertions.assertEquals("Login", this.driver.getTitle());

		// check if home page cannot be accessed after logout
		this.driver.get(this.baseUrl + "/home");
		Assertions.assertEquals("Login", this.driver.getTitle());
	}

	@Test
	@Order(4)
	public void testCreateNote() {
		String noteTitle = "Get groceries";
		String noteDescription = "Buy fruits, milk and bread.";

		this.driver.get(this.baseUrl + "/signup");
		SignupPage signupPage = new SignupPage(this.driver);
		signupPage.registerUser(this.driver, this.firstname, this.lastname, this.username, this.password);

		this.driver.get(this.baseUrl + "/login");
		LoginPage loginPage = new LoginPage(this.driver);

		loginPage.loginUser(this.driver, this.username, this.password);

		this.driver.get(this.baseUrl + "/home");
		HomePage homePage = new HomePage(this.driver);
		homePage.createNewNote(this.driver, noteTitle, noteDescription);

		this.driver.get(this.baseUrl + "/home");

		List<String> createdNote =  homePage.getNote(this.driver);

		Assertions.assertEquals(noteTitle, createdNote.get(0));
		Assertions.assertEquals(noteDescription, createdNote.get(1));
	}

	@Test
	@Order(5)
	public void testEditNote() {
		String noteTitle = "Get groceries";
		String noteDescription = "Buy fruits, milk and bread";
		String editedNoteTitle = " and water plants";
		String editedNoteDescription = ", and water plants twice each day.";

		this.driver.get(this.baseUrl + "/signup");
		SignupPage signupPage = new SignupPage(this.driver);
		signupPage.registerUser(this.driver, this.firstname, this.lastname, this.username, this.password);

		this.driver.get(this.baseUrl + "/login");
		LoginPage loginPage = new LoginPage(this.driver);

		loginPage.loginUser(this.driver, this.username, this.password);

		this.driver.get(this.baseUrl + "/home");
		HomePage homePage = new HomePage(this.driver);
		homePage.createNewNote(this.driver, noteTitle, noteDescription);

		this.driver.get(this.baseUrl + "/home");
		homePage.editNote(this.driver, editedNoteTitle, editedNoteDescription);

		this.driver.get(this.baseUrl + "/home");

		List<String> editedNote =  homePage.getNote(this.driver);

		Assertions.assertEquals(noteTitle + editedNoteTitle, editedNote.get(0));
		Assertions.assertEquals(noteDescription + editedNoteDescription, editedNote.get(1));
	}

}
