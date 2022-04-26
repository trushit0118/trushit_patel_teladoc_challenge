package com.teladoc.automation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.teladoc.core.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDef extends BaseClass {

	WebElement delUser;

	@Given("^Application is opened and on Home Page$")
	public void openHomePage() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.way2automation.com/angularjs-protractor/webtables/");
	}

	@When("^click on add user and provide details with user \"([^\"]*)\"$")
	public void addUser(String arg1) {
		driver.findElement(By.xpath("//button[@type='add']")).click();
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys(arg1);
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("auto");
		driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys(arg1);
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(arg1);

		List<WebElement> allOptions = driver.findElements(By.xpath("//label[@class='radio ng-scope ng-binding']"));

//		System.out.println("Radio Buttons count: " + allOptions.size());
		for (WebElement we : allOptions)
			if ("Customer AAA".contains(we.getText()))
				we.click();
		driver.findElement(By.xpath("//select[@name='RoleId']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(arg1 + "@gmail.com");
		driver.findElement(By.xpath("//input[@name='Mobilephone']")).sendKeys("9876543210");

		driver.findElement(By.xpath("//button[text()='Save']")).click();
	}

	@Then("^The \"([^\"]*)\" should be displayed in the home page$")
	public void verifyUser(String strUser) {
		Assert.assertEquals(strUser, driver.findElement(By.xpath("//td[text()='" + strUser + "']")).getText());
	}

	@Given("^The user \"([^\"]*)\" is in Home Page$")
	public void findUser(String arg1) {
		delUser = driver
				.findElement(By.xpath("//td[text()='" + arg1 + "']/following::i[@class='icon icon-remove'][1]"));
	}

	@When("^click on delete button of \"([^\"]*)\"$")
	public void performDelete(String arg1) {
		delUser.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//button[text()='OK']")).click();
	}

	@Then("^The user \"([^\"]*)\" should be deleted$")
	public void verifyDeletedUser(String arg1) {
		List<WebElement> users = driver.findElements(By.xpath("//td[text()='" + arg1 + "']"));
		Assert.assertEquals(0, users.size());
		tearDown();
	}

	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
