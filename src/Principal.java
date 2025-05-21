import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;

public class Principal {

    public static void main(String[] args) throws IOException, InterruptedException {
        String conversionDesde="";
        String conversionHacia="";
        int opcion = 0;
        Scanner teclado = new Scanner(System.in);
        while (opcion != 7) {
            System.out.println("*****************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("\n1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("*****************************************");
            opcion = teclado.nextInt();

            try {

                if(opcion==1){
                    conversionDesde="USD";
                    conversionHacia="ARS";
                }  else if(opcion == 2) {
                    conversionDesde = "ARS";
                    conversionHacia="USD";
                } else if(opcion == 3) {
                    conversionDesde = "USD";
                    conversionHacia = "BRL";
                } else if(opcion == 4) {
                    conversionDesde = "BRL";
                    conversionHacia = "USD";
                } else if(opcion == 5) {
                    conversionDesde = "USD";
                    conversionHacia = "COP";
                } else if(opcion == 6) {
                    conversionDesde = "COP";
                    conversionHacia = "USD";
                } else if (opcion == 7) {
                    System.out.println("Programa ha finalizado.");
                    break;
                }

                System.out.println("Ingrese la suma que desea transformar: ");
                var suma = teclado.nextDouble();

                URI direccion = URI.create("https://v6.exchangerate-api.com/v6/8d7cdfa9133d781c2a7c1e4b/latest/" + conversionDesde);

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(direccion)
                        .build();

                HttpResponse<String> response = null;
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                MonedaElegida resultado = new Gson().fromJson(response.body(), MonedaElegida.class);
                Double factor = resultado.conversion_rates().get(conversionHacia);

                System.out.println(suma + " " + conversionDesde + " equivalen a " + suma * factor + " " + conversionHacia);

            } catch (Exception e) {
                System.out.println("No existe");
                System.out.println("Finalizando aplicación.");
            }

        }
    }
}