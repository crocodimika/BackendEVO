import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[20];
        Random random = new Random();

        for(int i = 0; i < nums.length; i++){
            nums[i] = random.nextInt(15) + 1;
        }
        System.out.println(Arrays.toString(nums));

        boolean[] checked = new boolean[16]; // массив для уже обработанных чисел

        for (int i = 0; i < nums.length; i++) {
            if (checked[nums[i]]) {
                continue;
            }

            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            if (count > 1) {
                System.out.println("Число '" + nums[i] + "' встречается " + count + " раза.");
            }

            checked[nums[i]] = true;
        }
    }
}
