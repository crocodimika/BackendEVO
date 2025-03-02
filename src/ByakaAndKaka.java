import java.util.Scanner;

public class ByakaAndKaka {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку:");
        String string = scanner.nextLine();

        String bString = string.replace("кака", "вырезано цензурой");
        String kString = bString.replace("бяка", "вырезано цензурой");

        System.out.println(kString);

        scanner.close();
    }
}
