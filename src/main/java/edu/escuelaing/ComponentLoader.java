package edu.escuelaing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ComponentLoader {

    private static Map<String, Method> servicios = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        cargarComponentes(args);

        System.out.println(ejecutar("/hello", "?name=Juan&sn=Acosta"));
    }

    public static String ejecutar(String ruta, String param) throws InvocationTargetException, IllegalAccessException {
        return servicios.get(ruta).invoke(null, param) + "";

    }

    public static void cargarComponentes(String[] args) throws ClassNotFoundException{
        for(String arg: args){
            Class c = Class.forName(arg);
            if(c.isAnnotationPresent(Componente.class)){
                Method[] metodos = c.getDeclaredMethods();
                for(Method m: metodos){
                    if(m.isAnnotationPresent(GetMapping.class)){
                        String ruta = m.getAnnotation(GetMapping.class).value();
                        System.out.println("Cargando método: " + m.getName());
                        servicios.put(ruta, m);
                    }
                }
            }
        }
    }
}
