package com.healthcare.ConsultationForm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class ConsultationFormInvalidInput {
    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriver webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get("http://localhost:4200/login");

        WebElement uid = webDriver.findElement(By.name("userId"));
        uid.sendKeys("123456789105");

        WebElement pass = webDriver.findElement(By.name("userPassword"));
        pass.sendKeys("hello world");

        WebElement loginButton = webDriver.findElement(By.id("login"));
        loginButton.click();

        //sleep
        Thread.sleep(1100);
        WebElement abha_Id = webDriver.findElement(By.name("abhaId"));
//        js.executeScript("document.getElementsByName('abhaId')[0].required=false");
        abha_Id.sendKeys("987654321012");

        Thread.sleep(1100);
        WebElement checkBox = webDriver.findElement(By.name("checkbox"));

        js.executeScript("document.getElementsByName('checkbox')[0].click()");
        Thread.sleep(1100);
        WebElement searchButton = webDriver.findElement(By.name("search"));
        Thread.sleep(1100);
        searchButton.click(); //Clicking on the 'submit' button

        Thread.sleep(1100);



        WebElement dynamicButton = webDriver.findElement(By.name("dynamicButton"));
        dynamicButton.click();

        Thread.sleep(1100);

        // Bypassing ReadOnly validation
        WebElement date = webDriver.findElement(By.name("date"));
        //Making Date field Editable
        js.executeScript("document.getElementsByName('date')[0].readOnly=false");
        //Entering Past Date
        date.sendKeys("18-01-2021");

        Select diagnosisType = new Select(webDriver.findElement(By.name("diagnosisType")));
        diagnosisType.selectByIndex(1);

        WebElement compliant = webDriver.findElement(By.name("compliant"));
        compliant.sendKeys("testCompliant");

        WebElement examination = webDriver.findElement(By.name("examination"));
        examination.sendKeys("testExamination");

        Select icd10Code = new Select(webDriver.findElement(By.name("icd10Code")));
        icd10Code.selectByIndex(1);

        WebElement icdDescription = webDriver.findElement(By.name("icdDescription"));
        icdDescription.sendKeys("testICDDescription");

        WebElement instructions = webDriver.findElement(By.name("instructions"));
        instructions.sendKeys("testInstructions");

        Select improvementType = new Select(webDriver.findElement(By.name("improvementType")));
        improvementType.selectByIndex(1);

        WebElement illnessSummary = webDriver.findElement(By.name("illnessSummary"));
        illnessSummary.sendKeys("testIllnessSummary");

        WebElement remarks = webDriver.findElement(By.name("remarks"));
        remarks.sendKeys("testRemarks");

        Select furtherInstructions = new Select(webDriver.findElement(By.name("furtherInstructions")));
        furtherInstructions.selectByIndex(0);

        WebElement followUp = webDriver.findElement(By.name("followUp"));
        followUp.sendKeys("27-12-2022");

        Thread.sleep(1100);

        WebElement submit = webDriver.findElement(By.id("submitB"));
        submit.click();

        Thread.sleep(1100);
    }
}
