package com.healthtrack;

public class Usuario {
    private String nombre;
    private int alturaCm; // Altura en centímetros
    private double pesoKg; // Peso en kilogramos

    // Constructor que acepta nombre, altura y peso
    public Usuario(String nombre, int alturaCm, double pesoKg) {
        this.nombre = nombre;
        this.alturaCm = alturaCm;
        this.pesoKg = pesoKg;
    }

    // Constructor que acepta solo nombre y peso (si es necesario para otras partes de la app)
    // Este constructor es el que el error indicaba que estaba "required"
    public Usuario(String nombre, double pesoKg) {
        this(nombre, 0, pesoKg); // Llama al constructor principal, altura por defecto 0
    }

    public String getNombre() {
        return nombre;
    }

    public int getAlturaCm() {
        return alturaCm;
    }

    public double getPeso() {
        return pesoKg;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAlturaCm(int alturaCm) {
        this.alturaCm = alturaCm;
    }

    public void actualizarPeso(double nuevoPeso) {
        // Aquí iría la lógica de negocio para actualizar el peso.
        // Si hubo un bug previo, esta es la versión corregida.
        if (nuevoPeso > 0) { // Asegurarse de que el peso sea positivo
            this.pesoKg = nuevoPeso;
        } else {
            System.out.println("El peso no puede ser negativo o cero. No se actualizó.");
        }
    }

    // Método para calcular el IMC
    public double calcularIMC() {
        if (alturaCm <= 0 || pesoKg <= 0) {
            return 0.0; // Evitar división por cero o valores inválidos
        }
        double alturaMetros = alturaCm / 100.0;
        return pesoKg / (alturaMetros * alturaMetros);
    }
}
