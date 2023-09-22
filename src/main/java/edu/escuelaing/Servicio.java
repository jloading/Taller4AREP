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

    @RequestMapping(path = "/pojotest")
    public static String index(String arg) {
        return "test pojo";
    }

    @RequestMapping(path = "/reqtest")
    public static String request(String arg) {
        return "Request mapping test";
    }

    @RequestMapping(path = "/monito.jpg")
    public static String images(String arg){return "/monito.jpg";}

}
