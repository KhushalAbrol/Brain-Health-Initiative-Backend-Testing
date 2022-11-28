package com.healthcare.ConsultationForm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
/*
 * Author : Jayprakash Ray
 * Date : 25/11/2022
 * Bypass Testing of Add Consultaion Form Feature/Module by invalid Input
 *
 * This feature lets doctors add a consultation form on a patient's medical visit by entering details of his current medical condition and also can prescribe medicine by filling and submitting the form
 *
 * Add Consultation Form has following Constraint :
 * 2. Date : Current Date Only [Read Only]
 * 3. Compliant : Required
 * 4. ICD Description : Required

 *   Here we are providing following invalid inputs:
 * 1. Compliant: Provided Empty Input
.
*
 *
 * */
public class ConsultationFormInvalidInputCompliant {
    public static void main(String[] args) throws InterruptedException, IOException {

        //Chrome Web Driver Object Initialized
        WebDriver webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

        //Opening Web Page
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get("http://localhost:4200/login");

        //Setting User ID
        WebElement uid = webDriver.findElement(By.name("userId"));
        uid.sendKeys("123456789105");

        //Setting Password
        WebElement pass = webDriver.findElement(By.name("userPassword"));
        pass.sendKeys("hello world");

        //Clicking on Login Button
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

        js.executeScript("document.getElementsByName('compliant')[0].required=false");
//        WebElement compliant = webDriver.findElement(By.name("compliant"));
//        compliant.sendKeys("testCompliant");

        js.executeScript("document.getElementsByName('examination')[0].required=false");
//        WebElement examination = webDriver.findElement(By.name("examination"));
//        examination.sendKeys("testExamination");

        Select icd10Code = new Select(webDriver.findElement(By.name("icd10Code")));
        icd10Code.selectByIndex(1);

        js.executeScript("document.getElementsByName('icdDescription')[0].required=false");
//        WebElement icdDescription = webDriver.findElement(By.name("icdDescription"));
//        icdDescription.sendKeys("testICDDescription");

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
