import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, List<User>> users = new HashMap<>();

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

            if (!users.containsKey(age)) {
                users.put(age, new ArrayList<>()); //если ключа нет
            }
            users.get(age).add(new User(name, age));
        }
        System.out.println();
        int searchAge;
        while (true) {
            System.out.println("Введите требуемый возраст:");
            try {
                searchAge = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите число.");
            }
        }

        List<User> userList = users.get(searchAge);

        if (userList != null){
            userList.sort(Comparator.comparing(User::getName));

            for (User user : userList) {
                System.out.println(user);
            }
        } else {
            System.out.println("Пользователь с возрастом '" + searchAge + "' не найден");
        }

        scanner.close();
    }
}
