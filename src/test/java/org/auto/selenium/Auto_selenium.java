package org.auto.selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.PropertyConfigurator;

public class Auto_selenium {

    private static WebDriver driver = null;
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeTest
    public void setup() {
        extent = new ExtentReports();
        PropertyConfigurator.configure("log4j.properties");
        ExtentSparkReporter spark = new ExtentSparkReporter("Results/rapport.html");
        extent.attachReporter(spark);
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
    }

    @Test
    public void test() {
        test = extent.createTest("Mon Premier Test", "Description du test");
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        test.log(Status.INFO, "Navigateur ouvert et maximisé");
    }

    @AfterTest
    public void tearDownTest() {
        driver.close();
        test.log(Status.INFO, "Navigateur fermé");
        extent.flush(); // Génère le rapport
    }
}
