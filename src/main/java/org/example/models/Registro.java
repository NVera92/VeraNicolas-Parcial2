package org.example.models;

public class Registro {
    private String dni;
    private Integer temperatura;

    public Registro() {
    }

    public Registro(String dni, Integer temperatura) {
        this.dni = dni;
        this.temperatura = temperatura;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "dni='" + dni + '\'' +
                ", temperatura=" + temperatura +
                '}';
    }
}
