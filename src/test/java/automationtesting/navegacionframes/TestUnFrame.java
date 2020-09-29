package automationtesting.navegacionframes;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static automationtesting.Constantes.*;
import static org.junit.Assert.assertEquals;

public class TestUnFrame {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void ingresarAUnFrame(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_FRAME);
        driver.switchTo().frame("singleframe");
        driver.findElement(By.xpath("/html/body/section/div/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/div/input")).sendKeys(VALOR_USUARIO);
        driver.switchTo().defaultContent();
        assertEquals(VALIDAR_TEXTO_AUTOMATION_DEMO_SITE, driver.findElement(By.tagName("h1")).getText());
        Utilidades.esperar(2);
    }

    @Test
    public void ingresarADosFrame(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_FRAME);
        driver.findElement(By.xpath("//a[contains(text(), 'Iframe with in an Iframe')]")).click();

        WebElement primerFrame = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));

        if(primerFrame.isDisplayed()){
            driver.switchTo().frame(primerFrame);
        }
        Utilidades.esperar(1);
        WebElement segundoframe = driver.findElement(By.tagName("iframe"));

        if(segundoframe.isDisplayed()){
            driver.switchTo().frame(segundoframe);
        }

        Utilidades.esperar(1);

        driver.findElement(By.xpath("/html/body/section/div/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/div/input")).sendKeys(VALOR_USUARIO);

        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();

        assertEquals(VALIDAR_TEXTO_AUTOMATION_DEMO_SITE, driver.findElement(By.tagName("h1")).getText());
        Utilidades.esperar(2);

    }

    @After
    public void close(){
        driver.quit();
    }

}
