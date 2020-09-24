package automationtesting.autenticacion;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static automationtesting.Constantes.*;
import static org.junit.Assert.assertEquals;

public class TestAutenticacion {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void autenticacionExitosa(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_PRACTICE);
        driver.findElement(By.id(MY_ACCOUNT)).click();
        Utilidades.esperar(TIEMPO_ESPERA_CORTO);
        driver.findElement(By.id(USUARIO)).click();
        driver.findElement(By.id(USUARIO)).sendKeys(VALOR_USUARIO);
        driver.findElement(By.id(CONTRASENA)).click();
        driver.findElement(By.id(CONTRASENA)).sendKeys(VALOR_CONTRASENA);
        driver.findElement(By.name(BOTON_LOGIN)).click();
        Utilidades.esperar(TIEMPO_ESPERA_CORTO);
        assertEquals(VALIDAR_TEXTO_LOGIN, driver.findElement(By.xpath("//*[@id=\"page-36\"]/div/div[1]/div/p[1]")).getText());
    }

    @Test
    public void autenticacionFallida(){
        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_PRACTICE);
        driver.findElement(By.id(MY_ACCOUNT)).click();
        Utilidades.esperar(TIEMPO_ESPERA_CORTO);
        driver.findElement(By.id(USUARIO)).click();
        driver.findElement(By.id(USUARIO)).sendKeys(VALOR_USUARIO);
        driver.findElement(By.id(CONTRASENA)).click();
        driver.findElement(By.id(CONTRASENA)).sendKeys(VALOR_CONTRASENA_ERRONEA);
        driver.findElement(By.name(BOTON_LOGIN)).click();
        Utilidades.esperar(TIEMPO_ESPERA_CORTO);
        assertEquals(VALIDAR_TEXTO_LOGIN_INCORRECTO, driver.findElement(By.xpath("//*[@id=\"page-36\"]/div/div[1]/ul/li")).getText());
    }



    @After
    public void close(){
        driver.quit();
    }

}
