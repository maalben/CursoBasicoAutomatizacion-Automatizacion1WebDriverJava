package automationtesting.aperturapagina;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static automationtesting.Constantes.*;
import static org.junit.Assert.assertEquals;

public class TestAbrirPagina {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void abrirPagina(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_PRACTICE);
        Utilidades.esperar(TIEMPO_ESPERA_NORMAL);
    }

    @After
    public void close(){
        driver.quit();
    }

}
