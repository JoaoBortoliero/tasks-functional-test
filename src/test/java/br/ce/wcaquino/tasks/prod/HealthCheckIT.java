package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


public class HealthCheckIT {
    
    @Test 
     public void healthCheck() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.3.10:4444"), options);
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.navigate().to("http://192.168.3.10:9999/tasks");
            String version = driver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build"));
        } finally {
            driver.quit();
        }
    }
}
