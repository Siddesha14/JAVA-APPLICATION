package calorietracker;

import java.io.*;
import java.nio.file.*;

public class DataUtils {

    private static final String FILE_PATH = "calories.txt";
    private static final String GOAL_FILE_PATH = "calorieGoal.txt";

    public static double loadTotalCalories() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
                return Double.parseDouble(content.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;  // Return 0 if the file doesn't exist or is empty
    }

    public static void saveTotalCalories(double totalCalories) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(totalCalories));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double loadCalorieGoal() {
        try {
            if (Files.exists(Paths.get(GOAL_FILE_PATH))) {
                String content = new String(Files.readAllBytes(Paths.get(GOAL_FILE_PATH)));
                return Double.parseDouble(content.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;  // Return 0 if the file doesn't exist or is empty
    }

    public static void saveCalorieGoal(double calorieGoal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GOAL_FILE_PATH))) {
            writer.write(String.valueOf(calorieGoal));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void refreshData() {
        saveTotalCalories(0);
        saveCalorieGoal(0);
    }
}
