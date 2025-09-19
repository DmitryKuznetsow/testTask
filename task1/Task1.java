import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args2) {

        if (args.length != 2) {
            System.out.println("Use: java Task1.java <length array n> <interval length m>");
            return;
        } 
        int n, m;
        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
            if(n < 1 || m < 1) { 
                System.out.println("Use: int > 0 for length n and interval m");
                return; 
            }
        } catch (NumberFormatException e) {
            System.out.println("Use: int for length n and interval m");
            return;
        }
       
        // Инициализация кругового массива с элементами от 1 до n
        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        // Массив для хранения пути
        List<Integer> path  = new ArrayList<>();
        int currentIndex = 0;
        do {
            path.add(circularArray[currentIndex]);
            currentIndex = (currentIndex + m - 1) % n;
            // Переход к следующему интервалу
        } while (currentIndex != 0);

        // Вывод результата
        for(Integer el : path)
            System.out.print(el);
        System.out.println();
    }
    private static boolean isInteger(String str) {
        if(str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}


