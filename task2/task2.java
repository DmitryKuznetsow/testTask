import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CirclePointRelation {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Use: java task_2.java <circle_file> <points_file>");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        // Чтение данных окружности
        double[] circleData = readCircleData(circleFilePath);
        if (circleData == null) {
            System.out.println("Error reading circle data.");
            return;
        }
        
        double centerX = circleData[0];
        double centerY = circleData[1];
        double radius = circleData[2];

        // Чтение координат точек
        List<double[]> points = readPointsData(pointsFilePath);
        if (points == null) {
            System.out.println("Error reading points data.");
            return;
        }

        // Определение положения каждой точки
        for (double[] point : points) {
            int relation = determineRelation(centerX, centerY, radius, point[0], point[1]);
            System.out.println(relation);
        }
    }

    private static double[] readCircleData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] coords;
            coords = br.readLine().split(" ");
            double x = Double.parseDouble(coords[0]);
            double y = Double.parseDouble(coords[1]);
            double radius = Double.parseDouble(br.readLine());
            return new double[]{x, y, radius};
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<double[]> readPointsData(String filePath) {
        List<double[]> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coords = line.split(" ");
                for (int i = 0; i < coords.length; i += 2) {
                    double x = Double.parseDouble(coords[i]);
                    double y = Double.parseDouble(coords[i + 1]);
                    points.add(new double[]{x, y});
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return points;
    }

    private static int determineRelation(double centerX, double centerY, double radius, double pointX, double pointY) {
        double distanceSquared = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2);
        double radiusSquared = Math.pow(radius, 2);

        if (distanceSquared < radiusSquared) {
            return 1; // Точка внутри окружности
        } else if (distanceSquared == radiusSquared) {
            return 0; // Точка на окружности
        } else {
            return 2; // Точка снаружи окружности
        }
    }
}
