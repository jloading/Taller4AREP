package edu.escuelaing;

@Componente
public class Servicio {

    @GetMapping("/hello")
    public static String hola(String arg){
        return "Hola " + arg;
    }

    @GetMapping("/hellopost")
    public static String post(String arg){
        return "hola post " + arg;
    }
}
