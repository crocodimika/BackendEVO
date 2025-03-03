
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();

        for(int i = 1; i <= 5; i++){
            System.out.println("Введите имя пользователя " + i + ":");
            String name = scanner.nextLine();

            int age;
            while (true) {
                System.out.println("Введите возраст пользователя " + i + ":");
                try {
                    age = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Введите число.");
                }
            }

            users.add(new User(name, age));
        }
        scanner.close();

        users.sort(Comparator.comparing(User::getAge));


        System.out.println("\nСписок пользователей:");
        for(User user : users){
            System.out.println(user.toString());
        }
    }
}
