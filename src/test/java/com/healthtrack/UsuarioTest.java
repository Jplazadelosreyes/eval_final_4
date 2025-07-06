package com.healthtrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void testCalcularIMCCorrectamente() {
        Usuario usuario = new Usuario("Juan", 175, 70.0);
        // IMC = 70 / (1.75 * 1.75) = 70 / 3.0625 = 22.857
        assertEquals(22.86, usuario.calcularIMC(), 0.01, "El IMC debería calcularse correctamente");
    }

    @Test
    void testActualizarPesoCorrectamente() {
        Usuario usuario = new Usuario("Maria", 160, 60.0);
        usuario.actualizarPeso(62.5);
        assertEquals(62.5, usuario.getPeso(), "El peso debería actualizarse correctamente");
    }

    // PRUEBA CORREGIDA: Ahora verifica que el peso se actualiza correctamente al valor esperado.
    // El nombre del test se ha cambiado para reflejar el comportamiento correcto.
    @Test
    void testActualizarPesoConValorEspecifico() {
        Usuario usuario = new Usuario("Pedro", 180, 70.0);
        usuario.actualizarPeso(75.0); // Intentamos actualizar el peso a 75.0
        // Si el bug de lógica ha sido corregido, el peso final debería ser 75.0
        assertEquals(75.0, usuario.getPeso(), "El peso debería ser 75.0 kg después de la actualización correcta.");
    }
}