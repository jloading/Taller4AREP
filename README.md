# TALLER 4: TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRON IOC, REFLEXION

Programa creado para simular el microframework Spring a traves de reflexiónes que ofrece Java.

### Prerrequisitos

- Java: Es un lenguaje de programación de propósito general orientado a objetos, portátil y muy versátil.
- Maven: Es una herramienta que maneja el ciclo de vida del programa.
- Git: Es un sistema de control de versiones distribuido (VCS).

### Instalación

1. Clonar el repositorio

```
https://github.com/jloading/Taller4AREP
```

2. Se construye el proyecto con Maven

```
mvn package
```

### Corriendo la aplicación

Se corren los comandos
```
mvn clean package install
mvn clean install
```

Se corre el servidor con el comando
#### Para Mac:
```
java -cp target/classes edu.escuelaing.HttpServer
```

#### Para Windows:
```
java -cp target\classes edu.escuelaing.HttpServer
```

Para finalizar, se accede a la siguiente dirección desde el navegador

```
http://localhost:35000/
```

## Corriendo las pruebas
Anotaciones @GetMapping:
<img width="372" alt="Captura de pantalla 2023-09-21 a la(s) 11 33 11 p m" src="https://github.com/jloading/Taller4AREP/assets/65261708/94930f91-04f1-46f3-98e2-fb2b4757faa9">
<img width="382" alt="Captura de pantalla 2023-09-21 a la(s) 11 33 38 p m" src="https://github.com/jloading/Taller4AREP/assets/65261708/e11c9e64-d4f1-4562-b4b9-a6f383aa861f">

Anotaciones @RequestMapping:
<img width="390" alt="Captura de pantalla 2023-09-21 a la(s) 11 34 21 p m" src="https://github.com/jloading/Taller4AREP/assets/65261708/f6a7a9af-9d82-404d-b81e-182c5b974ddd">

<img width="365" alt="Captura de pantalla 2023-09-21 a la(s) 11 34 52 p m" src="https://github.com/jloading/Taller4AREP/assets/65261708/32d7edd4-b95f-45d6-a625-7ef54edabb62">

Prueba de la aplicación funcionando con imágenes:

<img width="1433" alt="Captura de pantalla 2023-09-21 a la(s) 11 35 41 p m" src="https://github.com/jloading/Taller4AREP/assets/65261708/ee53ba7a-1630-45a5-af3a-ed558e3cc13b">

Prueba de la aplicación funcionando con HTML:

<img width="435" alt="Captura de pantalla 2023-09-21 a la(s) 11 57 18 p m" src="https://github.com/jloading/Taller4AREP/assets/65261708/694d62f0-9c8c-48be-9789-4b2c2adff7cf">

## Hecho con

* Java
* Git
* Maven
* HTML, JavaScript

## Autor

* **Juan Carlos Acosta**

