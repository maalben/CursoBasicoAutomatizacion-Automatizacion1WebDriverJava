package automationtesting.recorridodinamico;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static automationtesting.Constantes.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TestRecorridos {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void recorrerRadios(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_REGISTRO);

        List<WebElement> contenedorRadios = driver.findElements(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/label"));
        Utilidades.seleccionarElementoDinamicamente(contenedorRadios, SELECCIONAR_FEMENINO);

        List<WebElement> contenedorSkills = driver.findElements(By.xpath("//select[@ng-model='Skill']/option"));
        Utilidades.seleccionarElementoDinamicamente(contenedorSkills, "Perl");


        Utilidades.esperar(5);
    }

    @After
    public void close(){
        driver.quit();
    }

}
