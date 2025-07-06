package com.healthtrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // Inicializamos un usuario antes de cada test para asegurar un estado limpio.
        usuario = new Usuario("Juan Pérez", 70.0);
    }

    @Test
    void testGetNombre() {
        assertEquals("Juan Pérez", usuario.getNombre(), "El nombre del usuario debería ser 'Juan Pérez'");
    }

    @Test
    void testGetPesoInicial() {
        assertEquals(70.0, usuario.getPeso(), 0.001, "El peso inicial del usuario debería ser 70.0 kg");
    }

    @Test
    void testActualizarPesoConError() {
        // Este test DEBE fallar inicialmente para demostrar el bug.
        double nuevoPesoEsperado = 75.0;
        usuario.actualizarPeso(nuevoPesoEsperado);
        // Debido al bug, esperamos que el peso sea 69.0 (70.0 - 1.0)
        assertEquals(69.0, usuario.getPeso(), 0.001, "El peso debería ser 69.0 kg debido al error de lógica");
        // Puedes añadir una aserción que demuestre que NO es el valor esperado:
        assertNotEquals(nuevoPesoEsperado, usuario.getPeso(), 0.001, "El peso NO debería ser el nuevo peso ingresado debido al error");
    }

    // Después de corregir el bug en Usuario.java, este test pasará
    @Test
    void testActualizarPesoCorrectamente() {
        // Este test asumirá que el bug ya está corregido.
        // Si lo corres antes de corregir, también fallará.
        double nuevoPeso = 72.5;
        usuario.actualizarPeso(nuevoPeso);
        // Una vez corregido, esperamos que el peso sea el nuevoPeso
        assertEquals(nuevoPeso, usuario.getPeso(), 0.001, "El peso debería actualizarse correctamente al nuevo valor.");
    }
}