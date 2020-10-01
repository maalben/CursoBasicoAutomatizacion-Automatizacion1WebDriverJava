package automationtesting.recorridodinamico;

import static automationtesting.ConstantesMapeosObjetos.CONTENEDOR_GENERO;
import static automationtesting.Utilidades.seleccionarElementoDinamicamente;
import static automationtesting.Utilidades.seleccionarElementosCheckBox;
import static automationtesting.Utilidades.esperar;
import static automationtesting.Utilidades.contenedorXpath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static automationtesting.Constantes.*;

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
        //seleccionarElementoDinamicamente(contenedorXpath(CONTENEDOR_GENERO, driver), SELECCIONAR_FEMENINO);
        //seleccionarElementoDinamicamente(driver.findElements(By.xpath("//select[@ng-model='Skill']/option")), "Perl");
        //seleccionarElementosCheckBox("Cricket,Hockey", driver.findElements(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/div")), driver);
        List<WebElement> contenedorCheckbox = driver.findElements(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/div"));
        String opciones = "Hockey,Movies";

        List<String> listaOpciones = new ArrayList<>();
        listaOpciones.addAll(Arrays.asList(opciones.split(",")));
        for(int i=0; i<contenedorCheckbox.size(); i++){
            for(int j=0; j<listaOpciones.size(); j++) {
                if (contenedorCheckbox.get(i).getText().equals(listaOpciones.get(j))) {
                    driver.findElement(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/div[" + (i + 1) + "]/input")).click();
                    break;
                }
            }
        }

        esperar(5);
    }

    @After
    public void close(){
        driver.quit();
    }

}
