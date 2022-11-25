package com.healthcare.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;

/*
* Author : Khushal Abrol
* Date : 25/11/2022
* Bypass Testing of Admin Input Form Feature/Module by invalid Input
*
* In this testcase we are checking Add Doctor functionality.
*
* Add doctor form has the following constraints:
* 1. User Id: 12 Digits and unique
* 2. Passwords and Confirm Password fields should match
* 3. Email should be in the format username@mailServer.domain
* 4. Mobile number should be of 10 digits
* 5. Pincode should be of 6 digits
*
*   Here we are providing following invalid inputs:
* 1. Mobile number: providing 11 digits number instead of 10 digits number.
* 2. Email: Not in required format.
* 3. Pincode: 5 digits instead of 6 digits.
*
* */
public class AdminInvalidInput {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Initilized WebDriver object as ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4200/login");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Logging in into the admin module
        // Filling UserId
        WebElement userID = driver.findElement(By.name("userId"));
        userID.sendKeys("365473499562");

        // Filling Password
        WebElement password = driver.findElement(By.name("userPassword"));
        password.sendKeys("hello world");

        // Clicking of "login" button
        WebElement login_button = driver.findElement(By.id("login"));
        login_button.click();

        // Wait for 1 sec
        Thread.sleep(1000);

        // Selecting "doctor" from the dropdown menu
        Select dropdown = new Select(driver.findElement(By.name("dropdown")));
        dropdown.selectByValue("DOCTOR");

        // Clicking on "add" option form the given options
        WebElement add = driver.findElement(By.name("add"));
        add.click();

        // Wait for 1 sec
        Thread.sleep(1000);

        //Entering details of doctor to be added (Where some details are invalid)
        WebElement userId2 = driver.findElement(By.name("userId"));
        userId2.sendKeys("123456789106");

        WebElement password2 = driver.findElement(By.name("password"));
        password2.sendKeys("123abcABC@");

        WebElement confirmPassword = driver.findElement(By.name("confirmPassword"));
        confirmPassword.sendKeys("123abcABC@");

        WebElement firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys("Testing");

        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("Doctor");

        Select gender = new Select(driver.findElement(By.name("gender")));
        gender.selectByIndex(0);

        Select roleName = new Select(driver.findElement(By.name("roleName")));
        roleName.selectByValue("secondary specialist");

        WebElement mobileNo = driver.findElement(By.name("mobileNo"));
        js.executeScript("document.getElementsByName('mobileNo')[0].setAttribute('pattern', '^[0-9]*$')");
        mobileNo.sendKeys("99152963412");

        WebElement email = driver.findElement(By.name("email"));
        js.executeScript("document.getElementsByName('email')[0].setAttribute('type', 'text')");
        js.executeScript("document.getElementsByName('email')[0].setAttribute('pattern', '(.*?)')");
        email.sendKeys("testing");

        Select hospitalId = new Select(driver.findElement(By.name("hospitalId")));
        hospitalId.selectByIndex(0);

        WebElement city = driver.findElement(By.name("city"));
        city.sendKeys("Bangalore");

        WebElement district = driver.findElement(By.name("district"));
        district.sendKeys("Bangalore");

        WebElement state = driver.findElement(By.name("state"));
        state.sendKeys("Karnataka");

        WebElement pincode = driver.findElement(By.name("pincode"));
        js.executeScript("document.getElementsByName('pincode')[0].setAttribute('pattern', '^[0-9]*$')");
        pincode.sendKeys("56010");

        // Wait for 1 sec
        Thread.sleep(1000);

        // Clicking on "Add Doctor" button
        WebElement addDoctor = driver.findElement(By.id("addDoctor"));
        addDoctor.click();

        // Wait for 10 secs
        Thread.sleep(10000);
    }
}
/*
* Conclusion:
*
* Error is not handled at the frontend, but handled at the backend.
* So it gives error in the console instead on a pop-up.
*
* */