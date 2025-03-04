import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите 3 числа");
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        boolean found = false;

        if(a%5==0){
            System.out.print("a="+a);
            found = true;
        }
        if(b%5==0){
            if(found) System.out.print(", ");
            System.out.print("b="+b);
            found = true;
        }
        if(c%5==0) {
            if(found) System.out.print(", ");
            System.out.print("c="+c);
            found = true;
        }
        if(!found){
            System.out.println("Нет значений, кратных 5");
        } else {
            System.out.println();
        }

        int res1 = a/b;
        System.out.println("Результат целочисленного деления a на b: "+ res1);
        double res2 = (double) a/(double)b;
        System.out.println("Результат деления a на b "+ res2);
        System.out.println("Результат деления a на b с округлением в большую сторону: " + Math.ceil(res2));
        System.out.println("Результат деления a на b с округлением в меньшую сторону: " + Math.floor(res2));
        System.out.println("Результат деления a на b с математическим округлением: " + Math.round(res2));

        int res3 = b%c;
        System.out.println("Остаток от деления b на c: " + res3);
        System.out.println("Наименьшее значение из a и b: " + Math.min(a,b));
        System.out.println("Наибольшее значение из b и c: " + Math.max(b,c));

        in.close();

    }
}