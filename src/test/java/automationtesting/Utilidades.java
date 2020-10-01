package automationtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Ésta clase permite tener funcionalidades genéricas para la automatización
 * @author: Mario Benítez
 * @version: 1.0
 */
public class Utilidades {

    public static void esperar(long cantidadTiempo){
        cantidadTiempo = cantidadTiempo * 1000;
        try{
            Thread.sleep(cantidadTiempo);
        }catch (InterruptedException interruptedException){
            Thread.currentThread().interrupt();
        }
    }

    public static void esperar(long cantidadTiempo, WebDriver driver){
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
    }
    /**
     *
     * @author Mario Benítez
     * @version 1.0
     * Método para seleccionar elementos de SELECT y RADIOS de manera dinámica
     * @param contenedorRadios Contenedor que tiene acceso a los elementos a inspeccionar
     * @param texto Argumento que buscará dentro de los elementos inspeccionados.
     *
     */
    public static void seleccionarElementoDinamicamente(List<WebElement> contenedorRadios, String texto){
        for(int i=0; i<contenedorRadios.size(); i++){
            if(contenedorRadios.get(i).getText().equals(texto)){
                contenedorRadios.get(i).click();
                break;
            }
        }
    }

    public static void seleccionarElementosCheckBox(String opciones, List<WebElement> contenedorCheckbox, WebDriver driver){
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
    }

    public static List<WebElement> contenedorXpath(String mapeo, WebDriver driver){
        return driver.findElements(By.xpath(mapeo));
    }

    public static WebElement elementoXpath(String mapeo, WebDriver driver){
        return driver.findElement(By.xpath(mapeo));
    }

}
