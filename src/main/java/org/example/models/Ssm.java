package org.example.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Ssm {
    private Integer cantidadReactivos;
    private Queue<Persona> colaPersonas;
    private HashMap<Integer,Registro> listaTesteados;
    private HashMap<String,Persona> sanos;
    private HashMap<String,Persona> enfermos;

    public Ssm(Integer cantidadReactivos) {
        this.cantidadReactivos = cantidadReactivos;
        this.colaPersonas = new LinkedList<>();
        this.listaTesteados = new HashMap<>();
        this.sanos = new HashMap<>();
        this.enfermos = new HashMap<>();
    }

    public Ssm(Integer cantidadReactivos, Queue<Persona> colaPersonas) {
        this.cantidadReactivos = cantidadReactivos;
        this.colaPersonas = colaPersonas;
    }

    public Integer getCantidadReactivos() {
        return cantidadReactivos;
    }

    public void setCantidadReactivos(Integer cantidadReactivos) {
        this.cantidadReactivos = cantidadReactivos;
    }

    public Queue<Persona> getColaPersonas() {
        return colaPersonas;
    }

    public void setColaPersonas(Queue<Persona> colaPersonas) {
        this.colaPersonas = colaPersonas;
    }

    public HashMap<Integer, Registro> getListaTesteados() {
        return listaTesteados;
    }

    public void setListaTesteados(HashMap<Integer, Registro> listaTesteados) {
        this.listaTesteados = listaTesteados;
    }

    public HashMap<String, Persona> getSanos() {
        return sanos;
    }

    public void setSanos(HashMap<String, Persona> sanos) {
        this.sanos = sanos;
    }

    public HashMap<String, Persona> getEnfermos() {
        return enfermos;
    }

    public void setEnfermos(HashMap<String, Persona> enfermos) {
        this.enfermos = enfermos;
    }

    @Override
    public String toString() {
        return "Ssm{" +
                "cantidadReactivos=" + cantidadReactivos +
                ", colaPersonas=" + colaPersonas +
                '}';
    }

    public Boolean dniRepetido(Persona persona){
        Boolean flag = false;
        try{
            for(Persona p : this.colaPersonas){
                if(p.getDni().equals(persona.getDni())){
                    flag = true;
            }
        }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public Integer numeroKit(){
        Random r = new Random();
        return r.nextInt(99,999);
    }
    public void encolarPersona(Persona persona){
        try{
            if(this.cantidadReactivos>0 && !dniRepetido(persona)){
                persona.setNumeroKit(numeroKit());
                this.colaPersonas.add(persona);
                this.cantidadReactivos --;
            }else{
                    throw new ExcepcionCustom();
            }
        }catch (IllegalArgumentException | ExcepcionCustom e){
            System.out.println(e.getMessage());
        }
    }


    public Integer fiebreRandom(){
        Random r = new Random();
        return r.nextInt(36,39);
    }
    public void testear(){
        try{
            for(Persona p : this.colaPersonas){
                    this.listaTesteados.put(p.getNumeroKit(),new Registro(p.getDni(),fiebreRandom()));
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public Persona buscarPersonaDni(String dni) {
        Persona aux = null;
        try{
            for(Persona p : this.colaPersonas){
                if(p.getDni() == dni){
                    aux = p;
            }
        }}catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return aux;
    }


    public void stringToArchivo(String cadena){
        try {
            File file = new File("urgente.dat");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(cadena);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
        }

    }
    public void aislar() throws ExcepcionCustom {
        Persona p = null;
        try{
            for (Map.Entry<Integer, Registro> entry : this.listaTesteados.entrySet()) {
                int key = entry.getKey();
                Registro value = entry.getValue();
                p = buscarPersonaDni(value.getDni());
                if(value.getTemperatura()>=38){
                    this.enfermos.put(p.getDni(),p);
                    String mensaje = String.valueOf(key)+" "+p.getBarrio();
                    stringToArchivo(mensaje);
                    throw new ExcepcionCustom(mensaje);

                }else{
                    this.sanos.put(p.getDni(), p);
                }
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public void sanosToJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(this.sanos);
                File archivo = new File("sanos.json");
                objectMapper.writeValue(archivo, this.sanos);
                System.out.println("Archivo de casos en JSON creado exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
            }
        }


    public void enfermosToJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this.enfermos);
            File archivo = new File("enfermos.json");
            objectMapper.writeValue(archivo, this.enfermos);
            System.out.println("Archivo de casos sospechosos en JSON creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public void   recorrerHashMapTesteados(){
        try{
            for (Map.Entry<Integer, Registro> entry : this.listaTesteados.entrySet()) {
                int key = entry.getKey();
                Registro value = entry.getValue();
                System.out.println("Clave: "+key+" "+"Valor: "+value);
            }
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}


