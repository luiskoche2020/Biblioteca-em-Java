import java.util.Scanner;

public class Input {
    public static int scanInt(String message, Scanner scan) {
        while (true) {
            try {
                System.out.print(message);
                int value = scan.nextInt();
                return value;
            } catch (Exception e) {
                System.out.println("Valor Inválido! Digite um número inteiro");
            } finally {
                scan.nextLine();
            }
        }
    }

    public static String scanString(String message, Scanner scan){
        while (true) {
            System.out.print(message);
            String value = scan.nextLine();
            value = value.trim();

            if (value.isEmpty())
                System.out.println("Valor Inválido!!!");
            else
                return value;
        }
    }
    public static double scanDouble(String message, Scanner scan) {
    while (true) {
        try {
            System.out.print(message);
            double value = scan.nextDouble();
            return value;
        } catch (Exception e) {
            System.out.println("Valor inválido! Digite um número decimal (ex.: 12.5)");
        } finally {
            scan.nextLine();
            }
        }
    }
}