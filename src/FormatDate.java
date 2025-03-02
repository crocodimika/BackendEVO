import java.util.Scanner;
public class FormatDate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите дату в формате 'дд.мм.гггг':");
        String string = scanner.nextLine();

        if (string.length() == 10 && string.charAt(2) == '.' && string.charAt(5) == '.') {
            String day = string.substring(0, 2);
            String month = string.substring(3, 5);
            String year = string.substring(6);

            String formattedDate = year + "-" + month + "-" + day;
            System.out.println(formattedDate);
        } else {
            System.out.println("Ошибка: некорректный формат даты.");
        }

        scanner.close();
    }
}