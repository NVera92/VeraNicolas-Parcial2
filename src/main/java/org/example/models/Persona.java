package org.example.models;

public class Persona {
    private String nombre;
    private Integer edad;
    private String barrio;
    private String dni;
    private String ocupacion;
    private Integer numeroKit;

    public Persona() {
    }

    public Persona(String nombre, Integer edad, String barrio, String dni, String ocupacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.barrio = barrio;
        this.dni = dni;
        this.ocupacion = ocupacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Integer getNumeroKit() {
        return numeroKit;
    }

    public void setNumeroKit(Integer numeroKit) {
        this.numeroKit = numeroKit;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", barrio='" + barrio + '\'' +
                ", dni='" + dni + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", numeroKit=" + numeroKit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Boolean flag = false;
        if(o instanceof Persona){
            Persona p = (Persona) o;
            if(p.getDni() == dni){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        return dni != null ? dni.hashCode() : 0;
    }


}
