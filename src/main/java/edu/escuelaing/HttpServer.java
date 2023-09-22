package edu.escuelaing;

import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;



import java.net.*;
import java.io.*;



public class HttpServer {



    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }



            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            outputLine = "";



            boolean firstLine = true;
            String uriString = "";



            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    firstLine = false;
                    // POST /hellopost?name=John HTTP/1.1
                    uriString = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            System.out.println("URI: " + uriString);

            ComponentLoader.cargarComponentes(new String[]{"edu.escuelaing.Servicio"});
            String responseBody = "";
            if (uriString.startsWith("/hello?")) {
                outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n"
                + ComponentLoader.ejecutar("/hello", uriString);

            }else if (uriString.startsWith("/hellopost?")){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + ComponentLoader.ejecutar("/hellopost", uriString);
            }else if (uriString.startsWith("/pojotest")){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + ComponentLoader.ejecutar("/pojotest", uriString);
            }else if (uriString.startsWith("/reqtest")){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + ComponentLoader.ejecutar("/reqtest", uriString);
            }else if (uriString.startsWith("/monito.jpg")){
                outputLine = searchFileInPublic(uriString, responseBody, outputLine, clientSocket);
            }
            else {
                outputLine = getIndexResponse();
            }
            out.println(outputLine);



            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }



    public static String getHello(String uri) {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n"
                + "{ \"msg\": \"Hello Pedro\" }";
        return response;
    }



    public static String getIndexResponse() {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Form Example</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Form with GET</h1>\n"
                + "        <form action=\"/hello\">\n"
                + "            <label for=\"name\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                + "        </form> \n"
                + "        <div id=\"getrespmsg\"></div>\n"
                + "\n"
                + "        <script>\n"
                + "            function loadGetMsg() {\n"
                + "                let nameVar = document.getElementById(\"name\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                }\n"
                + "                xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "        </script>\n"
                + "\n"
                + "        <h1>Form with POST</h1>\n"
                + "        <form action=\"/hellopost\">\n"
                + "            <label for=\"postname\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n"
                + "        </form>\n"
                + "        \n"
                + "        <div id=\"postrespmsg\"></div>\n"
                + "        \n"
                + "        <script>\n"
                + "            function loadPostMsg(name){\n"
                + "                let url = \"/hellopost?name=\" + name.value;\n"
                + "\n"
                + "                fetch (url, {method: 'POST'})\n"
                + "                    .then(x => x.text())\n"
                + "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n"
                + "            }\n"
                + "        </script>\n"
                + "    </body>\n"
                + "</html>";
        return response;
    }

    private static String searchFileInPublic(String uriString, String responseBody, String outputLine, Socket clientSocket) throws IOException {
        if (uriString != null && !getFile(uriString).equals("Not Found")) {
            responseBody = getFile(uriString);
            outputLine = getLine(responseBody);
        } else if (uriString != null && uriString.split("\\.")[1].equals("jpg") ||
                uriString.split("\\.")[1].equals("png")) {
            OutputStream outputStream = clientSocket.getOutputStream();
            File file = new File("src/main/resources/public/" + uriString);
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            ImageIO.write(bufferedImage, uriString.split("\\.")[1], byteArrayOutputStream);
            outputLine = getImg("");
            dataOutputStream.writeBytes(outputLine);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
            System.out.println(outputLine);

        }
        return outputLine;
    }

    public static String getFile(String route) {
        Path file = FileSystems.getDefault().getPath("src/main/resources/public", route);
        Charset charset = Charset.forName("US-ASCII");
        String web = new String();
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                web += line + "\n";
            }
        } catch (IOException x) {
            web = "Not Found";
        }
        return web;
    }

    public static String getLine(String responseBody) {
        return "HTTP/1.1 200 OK \r\n"
                + "Content-Type: text/html \r\n"
                + "\r\n"
                + "\n"
                + responseBody;
    }

    private static String getImg(String responseBody) {
        System.out.println("response Body" + responseBody);
        return "HTTP/1.1 200 OK \r\n"
                + "Content-Type: image/jpg \r\n"
                + "\r\n";
    }

}