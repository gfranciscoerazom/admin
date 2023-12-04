package com.microservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.awt.Desktop;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // Crea un menú con tres opciones: consultar SRI, consultar ANT y salir
		HttpClient httpClient = HttpClient.newHttpClient();

        while (true) {
            // Mostrar el menú
            System.out.println("1. Consultar SRI");
            System.out.println("2. Consultar institución");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");

            // Leer la opción ingresada por el usuario
            int opcion = Integer.parseInt(System.console().readLine());

            // Ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    System.out.println("Consultando SRI...");

                    System.out.print("Ingrese el número de RUC: ");
                    String numeroRuc = System.console().readLine();

		            HttpRequest getRequest = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:3001/admin1?numeroRuc=" + numeroRuc))
                        .build();

                    HttpResponse<String> response = httpClient.send(getRequest, BodyHandlers.ofString());
                    // Con un operador ternario di si está aportando al SRI o no
                    System.out.println(response.body().equals("true") ? "Está aportando al SRI" : "No está aportando al SRI");
                    break;
                case 2:
                    System.out.println("Consultando Gov.ec...");
                    
                    System.out.print("Ingrese el id de la institución: ");
                    String idInstitucion = System.console().readLine();

                    HttpRequest getRequest2 = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:3002/admin2?id=" + idInstitucion))
                        .build();

                    HttpResponse<String> response2 = httpClient.send(getRequest2, BodyHandlers.ofString());
                    System.out.println(response2.body());
                    // System.out.println("Consultando ANT...");

                    // System.out.print("Ingrese el número de cédula: ");
                    // String numeroCedula = System.console().readLine();

                    // System.out.print("Ingrese la placa: ");
                    // String placa = System.console().readLine();

                    // if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    //     // A la brava
                    //     Desktop.getDesktop().browse(new URI("https://consultaweb.ant.gob.ec/PortalWEB/paginas/clientes/clp_grid_citaciones.jsp?ps_tipo_identificacion=CED&ps_identificacion=" + numeroCedula + "&ps_placa=" + placa));
                    //     // A la buena
                    //     // Desktop.getDesktop().browse(new URI("http://localhost:3002/admin2?numeroCedula=" + numeroCedula + "&placa=" + placa));
                    // }
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
}