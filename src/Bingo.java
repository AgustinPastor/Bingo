import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Bingo
{
    private static final int NUMERO_CARTON = 10;
    private static final int LIMITE_NUMERO = 99;
    private static Set<Integer> numerosExtraidos = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] carton = generarCarton();
        System.out.println("Cartón de bingo generado:");
        for (int num: carton) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Introduce la cantidad de tu apuesta:");
        int apuesta= scanner.nextInt();
        System.out.println("Introduce el numero e intentos prevista para acertar el bingo:");
        int intentosPrevistos = scanner.nextInt();

        int intentos = 0;
        int aciertos = 0;
        int numerosParaLinea = 0;
        boolean lineaCantada = false;

        while (aciertos < NUMERO_CARTON) {
            int numeroExtraido = extraerNumero();
            intentos++;
            System.out.println("Número extraído:" + numeroExtraido);


            for (int numero: carton) {
                if (numerosExtraidos.contains(numero)) {
                    aciertos++;
                    System.out.println("¡Acierto! Aciertos totales:" + aciertos);
                    break;
                }
            }

            if (aciertos == 5 && !lineaCantada) {
                numerosParaLinea = intentos;
                lineaCantada = true;
                System.out.println("¡Linea cantada!");
            }

        }

        System.out.println("¡Bingo! Se han acertado todos los numeros.");
        System.out.println("numeros extraidos para linea:"+numerosParaLinea);
        System.out.println("numeros extraidos para bingo:"+intentos);

        if (intentos == intentosPrevistos) {
            System.out.println("¡Felicidades! Has acertado el bingo en el numero previsto. Premio obtenido:"+ (apuesta*10));
        } else {
            System.out.println("No se ha acertado el bingo en el numero previsto");
        }
        scanner.close();
    }
    private static int [] generarCarton() {
        Set<Integer> numerosCarton = new HashSet<>();
        int[] carton=new int[NUMERO_CARTON];

        while (numerosCarton.size() < NUMERO_CARTON) {
            int numeroAleatorio = (int) (Math.random()*LIMITE_NUMERO)+1;
            numerosCarton.add(numeroAleatorio);
        }

        int index=0;
        for (int numero : numerosCarton) {
            carton[index++]=numero;
        }
        return carton;
    }
    private static int extraerNumero() {
        int numero;
        do {
            numero = (int) (Math.random() * LIMITE_NUMERO) + 1;
        } while (numerosExtraidos.contains(numero));

        numerosExtraidos.add(numero);
        return numero;
    }
}

