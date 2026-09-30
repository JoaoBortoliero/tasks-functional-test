package br.ce.wcaquino.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TasksTest {

    private final By addTodo = By.id("addTodo");
    private final By inputTask = By.id("task");
    private final By inputDueDate = By.id("dueDate");
    private final By bttnSave = By.id("saveButton");
    private final By message = By.id("message");

    public WebDriver acessarAplicacao() {
        try {
            WebDriver driver = new ChromeDriver();
            driver.navigate().to("http://localhost:8001/tasks");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            return driver;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();
        try {
            driver.findElement(addTodo).click();
            driver.findElement(inputTask).sendKeys("Teste via Selenium");
            driver.findElement(inputDueDate).sendKeys("01/10/2030");
            driver.findElement(bttnSave).click();
            String msg = driver.findElement(message).getText();
            Assert.assertEquals("Success!", msg);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();
        try {
            driver.findElement(addTodo).click();
            driver.findElement(inputDueDate).sendKeys("01/10/2030");
            driver.findElement(bttnSave).click();
            String msg = driver.findElement(message).getText();
            Assert.assertEquals("Fill the task description", msg);
            driver.quit();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();
        try {
            driver.findElement(addTodo).click();
            driver.findElement(inputTask).sendKeys("Teste negativo via Selenium");
            driver.findElement(bttnSave).click();
            String msg = driver.findElement(message).getText();
            Assert.assertEquals("Fill the due date", msg);
            driver.quit();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();
        try {
            driver.findElement(addTodo).click();
            driver.findElement(inputTask).sendKeys("Teste negativo via Selenium");
            driver.findElement(inputDueDate).sendKeys("01/10/2020");
            driver.findElement(bttnSave).click();
            String msg = driver.findElement(message).getText();
            Assert.assertEquals("Due date must not be in past", msg);
            driver.quit();
        } finally {
            driver.quit();
        }
    }
}

