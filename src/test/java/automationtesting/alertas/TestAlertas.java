package automationtesting.alertas;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static automationtesting.Constantes.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.junit.Assert.assertEquals;

public class TestAlertas {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void alertaConOK(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_ALERTAS);
        driver.findElement(By.xpath("//button[@class='btn btn-danger' and @onclick='alertbox()']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(alertIsPresent());
        Alert alerta = driver.switchTo().alert();
        assertEquals("I am an alert box!", alerta.getText());
        alerta.accept();
        assertEquals("Automation Demo Site", driver.findElement(By.xpath("//h1")).getText());
        Utilidades.esperar(TIEMPO_ESPERA_NORMAL);
    }

    @Test
    public void alertaConCancelaryOK(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_ALERTAS);
        driver.findElement(By.xpath("//a[@href='#CancelTab']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-primary' and @onclick='confirmbox()']")).click();
        wait.until(alertIsPresent());
        Alert alerta = driver.switchTo().alert();
        assertEquals("Press a Button !", alerta.getText());
        alerta.dismiss();
        assertEquals("You Pressed Cancel", driver.findElement(By.id("demo")).getText());
        driver.findElement(By.xpath("//button[@class='btn btn-primary' and @onclick='confirmbox()']")).click();
        wait.until(alertIsPresent());
        alerta.accept();
        assertEquals("You pressed Ok", driver.findElement(By.id("demo")).getText());
        assertEquals("Automation Demo Site", driver.findElement(By.xpath("//h1")).getText());
        Utilidades.esperar(TIEMPO_ESPERA_NORMAL);
    }

    @Test
    public void alertaConTextbox(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_ALERTAS);
        driver.findElement(By.xpath("//a[@href='#Textbox']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-info' and @onclick='promptbox()']")).click();
        wait.until(alertIsPresent());
        Alert alerta = driver.switchTo().alert();
        assertEquals("Please enter your name", alerta.getText());
        alerta.sendKeys(VALOR_USUARIO);
        alerta.accept();
        assertEquals("Hello "+VALOR_USUARIO+" How are you today", driver.findElement(By.id("demo1")).getText());
        assertEquals("Automation Demo Site", driver.findElement(By.xpath("//h1")).getText());
        Utilidades.esperar(TIEMPO_ESPERA_NORMAL);
    }

    @After
    public void close(){
        driver.quit();
    }

}
