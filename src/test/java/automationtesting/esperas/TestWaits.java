package automationtesting.esperas;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

import static automationtesting.Constantes.URL_PRUEBA_DEMO_FRAME;
import static automationtesting.Constantes.URL_PRUEBA_PRACTICE;

public class TestWaits {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void esperaImplicita(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_PRACTICE);
        Utilidades.esperar(30, driver);
        driver.findElement(By.linkText("Shop")).click();
    }

    @Test
    public void esperaExplicita(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_PRACTICE);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(visibilityOfElementLocated(By.linkText("Shop")));
        driver.findElement(By.linkText("Shop")).click();
    }

    @Test
    public void esperaFluida(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_PRACTICE);

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(Exception.class);

        WebElement linkShop = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.linkText("Shop")));

        linkShop.click();
    }


    @After
    public void close(){
        driver.quit();
    }

}
