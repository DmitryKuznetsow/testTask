import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinMovesToEqualArray {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Use: java task_4.java <input_file>");
            return;
        }

        String inputFilePath = args[0];
        List<Integer> nums = readNumbersFromFile(inputFilePath);

        if (nums.isEmpty()) {
            System.out.println("Input file is empty or contains invalid data.");
            return;
        }

        int minMoves = calculateMinMoves(nums);
        System.out.println(minMoves);
    }

    private static List<Integer> readNumbersFromFile(String filePath) {
        List<Integer> nums = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            String[] numbers = line.split(" ");
            for (String number : numbers) {
                try {
                    nums.add(Integer.parseInt(number));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format: " + number);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nums;
    }

    private static int calculateMinMoves(List<Integer> nums) {

        int median = getMedian(nums);

        // Подсчет перемещений к медиане
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }

    private static int getMedian(List<Integer> nums) {
        int[] sorted = nums.stream().mapToInt(i -> i).sorted().toArray();
        int mid = sorted.length / 2;
        if (sorted.length % 2 == 0) {
            return sorted[mid - 1]; // В случае четного количества возвращаем меньшее
        } else {
            return sorted[mid];
        }
    }
}
