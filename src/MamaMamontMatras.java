import java.util.Scanner;

public class MamaMamontMatras {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите строку:");
            String string = scanner.nextLine();

            System.out.println("Введите подстроку:");
            String subString = scanner.nextLine();

            int count = 0;
            int index = 0;
            while ((index = string.indexOf(subString, index)) != -1) {
                count++;
                index += subString.length();
            }

            System.out.println("Подстрока '" + subString + "' встречается " + count + " раз(а)");

            scanner.close();
        }
    }
