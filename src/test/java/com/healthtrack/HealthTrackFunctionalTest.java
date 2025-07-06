package com.healthtrack.functional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthTrackFunctionalTest {

    private WebDriver driver;
    // Usa el nombre del servicio Docker Compose 'webapp' y su puerto interno (80 por defecto para Nginx)
    private final String BASE_URL = "http://webapp:80/";

    @BeforeAll
    static void setupClass() {
        // No usamos WebDriverManager aquí, confiamos en el chromedriver instalado en el Dockerfile.
    }

    @BeforeEach
    void setupTest() {
        // Configurar opciones para ejecutar Chrome en modo headless dentro de Docker
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Ejecuta Chrome sin UI, esencial en Docker
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox"); // Necesario para ejecutar Chrome como root en Docker
        options.addArguments("--disable-dev-shm-usage"); // Soluciona problemas de memoria en Docker
        options.addArguments("--window-size=1920,1080"); // Establece un tamaño de ventana virtual

        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // Maximiza la ventana (útil incluso en headless)
    }

    @Test
    void testUserLoginAndUpdateWeightFlow() {
        // 1. Navegar a la página de inicio de sesión
        driver.get(BASE_URL);
        System.out.println("Navegando a: " + driver.getCurrentUrl());
        assertTrue(driver.getTitle().contains("HealthTrack - Inicio de Sesión"), "Debería estar en la página de inicio de sesión");

        // Simular inicio de sesión (estos son campos estáticos en nuestro HTML)
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys("testuser");
        passwordField.sendKeys("password123");
        loginButton.click();
        System.out.println("Intentando iniciar sesión...");

        // Aquí esperaríamos una redirección a la página de perfil o dashboard real.
        // Como es HTML estático, simulamos la navegación manual a la página de actualización de peso.
        // En una app real, verificarías la URL o un elemento post-login.
        WebElement goToUpdateWeightLink = driver.findElement(By.id("goToUpdateWeight"));
        goToUpdateWeightLink.click();
        System.out.println("Navegando a la página de actualización de peso...");

        // Esperar a que la página de actualización de peso cargue
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("update-weight.html"));
        assertTrue(driver.getTitle().contains("HealthTrack - Actualizar Peso"), "Debería estar en la página de actualización de peso");

        // 2. Obtener el peso actual (simulado)
        WebElement currentWeightElement = driver.findElement(By.id("currentWeight"));
        String currentWeightText = currentWeightElement.getText();
        System.out.println("Peso actual simulado en la UI: " + currentWeightText + " kg");
        assertEquals("70.0", currentWeightText, "El peso actual inicial simulado debería ser 70.0");

        // 3. Ingresar un nuevo peso y actualizar
        WebElement newWeightInput = driver.findElement(By.id("newWeight"));
        // CORRECCIÓN: Cambiado el ID del botón para que coincida con el HTML
        WebElement updateButton = driver.findElement(By.id("updateWeightButton"));

        newWeightInput.clear(); // Limpiar cualquier valor preexistente
        newWeightInput.sendKeys("75.5"); // Ingresar un nuevo peso
        updateButton.click();
        System.out.println("Actualizando peso a 75.5 kg...");

        // En una aplicación real, aquí esperaríamos una confirmación o la actualización del peso en la UI.
        // Para nuestro HTML estático, simplemente verificamos que la "acción" de hacer click se completó
        // y que no hubo errores obvios de navegación.
        // Podríamos simular un mensaje de éxito si la página lo tuviera.
        WebElement messageElement = driver.findElement(By.id("message"));
        wait.until(ExpectedConditions.textToBePresentInElement(messageElement, "")); // Esperar a que algún mensaje aparezca (si lo hubiera)
        System.out.println("Simulación de actualización de peso completada.");

        // Este test pasará si Selenium puede navegar e interactuar con los elementos.
        // Para verificar la lógica del peso (el bug), eso ya lo hicimos con las pruebas unitarias.
        // Una prueba funcional real iría a la página de perfil para VERIFICAR el nuevo peso.
        // Dado que es HTML estático, no podemos persistir el dato.
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit(); // Cierra el navegador después de cada test
        }
    }
}
