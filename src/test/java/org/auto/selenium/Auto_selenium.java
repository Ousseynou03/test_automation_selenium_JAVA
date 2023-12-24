package org.auto.selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;

public class Auto_selenium {

    // Déclaration des variables
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    private static String baseUrl = "https://www.google.com";

    // Méthode d'initialisation exécutée avant chaque test
    @BeforeTest
    public void setup() {
        // Initialisation du rapport ExtentReports
        extent = new ExtentReports();

        // Configuration du journal log4j
        PropertyConfigurator.configure("log4j.properties");

        // Configuration du rapport HTML avec ExtentSparkReporter
        ExtentSparkReporter spark = new ExtentSparkReporter("Results/rapport.html");
        extent.attachReporter(spark);

        // Initialisation du navigateur Safari
        driver = new FirefoxDriver();
    }



    public void captureScreenshot(WebDriver driver, String testName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destionationFile = new File(System.getProperty("user.dir").concat("/screenshots/").concat(testName).concat(".png"));
        FileUtils.copyFile(sourceFile, destionationFile);
        System.out.println("***** Screenshot taken *****");

    }

    // Méthode de test
    @Test
    public void test() throws IOException {
        // Création d'un test Extent avec un nom et une description
        test = extent.createTest("Mon Premier Test", "Description du test");

        // Ouverture du site Web de Google
        driver.get(baseUrl);

        // Maximisation de la fenêtre du navigateur
        driver.manage().window().maximize();
        captureScreenshot(driver, "Réussite ouverture google");

        // Enregistrement d'un message de log dans le rapport
        test.log(Status.INFO, "Navigateur ouvert et maximisé");
    }

    // Méthode exécutée après chaque test
    @AfterTest
    public void tearDownTest() {
        // Fermeture du navigateur
        driver.close();

        // Enregistrement d'un message de log dans le rapport
        test.log(Status.INFO, "Navigateur fermé");

        // Génération du rapport
        extent.flush();
    }
}
