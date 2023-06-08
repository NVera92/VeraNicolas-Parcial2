package org.example;

import org.example.models.ExcepcionCustom;
import org.example.models.Persona;
import org.example.models.Ssm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Ssm sistema = new Ssm(10);

        /// Creacion de persona
        Persona p1 = new Persona("Facundo Alvarez",29,"La Loma","38569432","Administrativo");
        Persona p2 = new Persona("Alfredo Gonzalez",53,"Stella Maris","16548902","Abogado");
        Persona p3 = new Persona("Jose Navas",80,"Centro","539932","Jubilado");


        /// Agregado de persona a cola
        sistema.encolarPersona(p1);
        sistema.encolarPersona(p2);
        sistema.encolarPersona(p3);
        System.out.println(sistema.getColaPersonas());



        /// Testeados
        sistema.testear();
        sistema.recorrerHashMapTesteados();


        /// Aislados y pasaje a archivo .dat
        try {
            sistema.aislar();
        } catch (ExcepcionCustom e) {
            System.out.println("Excepcion propia Aislar: "+e.getMessage());
        }



        /// Pasaje a Json
        sistema.enfermosToJson();
        sistema.sanosToJson();


    }
}
