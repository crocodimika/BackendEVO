import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Как тебя зовут? ");
        String name = in.nextLine();
        System.out.print("Привет, " + name + "!");
    }
}