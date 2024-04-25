import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String opciones = """
                1) Dolar -> Peso argentino
                2) Peso argentino -> Dolar
                3) Dolar -> Real brasileño
                4) Real brasileño -> Dolar
                5) Dolar -> Peso colombiano
                6) Peso colombiano -> Dolar
                7) Salir
                """;

        Scanner teclado = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        while(true) {
            System.out.println("********************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda");
            System.out.println("");
            System.out.println(opciones);
            System.out.println("Elija una opcion valida");
            System.out.println("********************************************");

            try {
                int seleccion = teclado.nextInt();

                if (seleccion == 7){
                    break;
                } else if (seleccion < 1 || seleccion > 7) {
                    System.out.println("Opcion no valida");
                    break;
                }

                System.out.println("Ingrese el valor que desea convertir");
                Double monto = teclado.nextDouble();


                Moneda moneda = consulta.consultar(seleccion, monto);
                System.out.println("El valor " + monto + " [" + moneda.base_code() + "] corresponde al valor final de -> " + moneda.conversion_result() + " [" + moneda.target_code() + "]");
            } catch (InputMismatchException e){
                System.out.println("No es un numero valido");
                break;
            } catch (RuntimeException e) {
                System.out.println("Finalizando");
            }



        }



    }
}
