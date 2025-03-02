import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class SimpleDate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            System.out.println("Введите дату в формате dd.MM.yyyy:");
            Date date = dateFormat.parse(scanner.nextLine());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // + 45 дней
            calendar.add(Calendar.DAY_OF_YEAR, 45);
            System.out.println("Дата после увеличения на 45 дней: " + dateFormat.format(calendar.getTime()));

            // начало года
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            System.out.println("Дата после сдвига на начало года: " + dateFormat.format(calendar.getTime()));

            // Увеличение на 10 рабочих дней
            calendar.setTime(date);
            int workDaysAdded = 0;
            while (workDaysAdded < 10) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                    workDaysAdded++;
                }
            }
            System.out.println("Дата после увеличения на 10 рабочих дней: " + dateFormat.format(calendar.getTime()));

            System.out.println("Введите вторую дату в формате dd.MM.yyyy:");
            Date secondDate = dateFormat.parse(scanner.nextLine());

            // рабочие дни между датами
            calendar.setTime(date);
            Calendar secondCalendar = Calendar.getInstance();
            secondCalendar.setTime(secondDate);

            int workDaysBetween = 0;
            while (calendar.before(secondCalendar)) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                    workDaysBetween++;
                }
            }

            System.out.println("Количество рабочих дней между введенными датами: " + workDaysBetween);

        } catch (ParseException e) {
            System.out.println("Ошибка: некорректный формат даты.");
        }

        scanner.close();
    }
}
