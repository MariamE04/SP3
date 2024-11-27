package Netflix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SeriesHandler {
    private ArrayList<Media> series;
    private Scanner scan;
    private TextUI textUI;

    public SeriesHandler(ArrayList<Media> series) {
        this.series = series;
        this.scan = new Scanner(System.in);
    }

    public void showSeries() {
        if (series.isEmpty()) {
            System.out.println("No series available.");
        } else {
            System.out.println("Available Series:");
            for (int i = 0; i < series.size(); i++) {
                Media serie = series.get(i);
                // Nummeret i listen starter fra 1 (i + 1)
                System.out.println((i + 1) + serie.toString());
            }
        }
    }

    public void showSavedSeries() {
        System.out.println("Saved Series:");

        ArrayList<String> savedSeries = new ArrayList<>(); // Store the saved series in a list
        try (BufferedReader reader = new BufferedReader(new FileReader("Data/SavedSeriesList.csv"))) {
            String line;
            int index = 1; // Add numbering to the saved series
            while ((line = reader.readLine()) != null) {
                savedSeries.add(line); // Add each series to the list
                System.out.println(index + ": " + line); // Display numbered list
                index++;
            }
            if (savedSeries.isEmpty()) {
                System.out.println("No saved series found.");
            } else {
                showPlayOrBackMenu(savedSeries); // Show options after displaying series
            }
        } catch (IOException e) {
            System.out.println("Error loading saved series: " + e.getMessage());
        }
    }

    private void showPlayOrBackMenu(ArrayList<String> savedSeries) {
        System.out.println("Choose an option:");
        System.out.println("1: Play a saved series");
        System.out.println("2: Back to menu");

        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter the number of the series you want to play:");
                    int seriesChoice = scan.nextInt();
                    scan.nextLine(); // Clear buffer

                    // Validate the user's choice
                    if (seriesChoice > 0 && seriesChoice <= savedSeries.size()) {
                        System.out.println("Playing series: " + savedSeries.get(seriesChoice - 1));
                    } else {
                        System.out.println("Invalid series number. Please try again.");
                        showPlayOrBackMenu(savedSeries); // Show menu again
                    }
                    break;
                case 2:
                    textUI.showMediaMenu(); // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    showPlayOrBackMenu(savedSeries); // Show menu again
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine(); // Clear buffer
            showPlayOrBackMenu(savedSeries); // Show menu again
        }
    }

    /*public void displaySeries() {
        System.out.println("You chose series, here's some options:");
        for (int i = 0; i < series.size(); i++) {
            String seriesDetails = i + " - " + series.get(i).getTitel() + " - "
                    + series.get(i).getReleaseDate() + " - "
                    + series.get(i).getCategory() + " - "
                    + series.get(i).getRating();
            System.out.println(seriesDetails);
        }
    }*/

    public void chooseSeries() {
        System.out.println("\nPlease press the number of the series you want to watch:");
        try {
            int seriesNumberToWatch = scan.nextInt();
            scan.nextLine(); // Clear buffer
            System.out.println("You have chosen: " + series.get(seriesNumberToWatch).getTitel());

            System.out.println("What would you like to do?");
            System.out.println("1: Play the series");
            System.out.println("2: Save the series to your list");
            System.out.println("Enter your choice:");
            int option = scan.nextInt();
            scan.nextLine();
            seriesOption(option);

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scan.nextLine(); // Clear buffer
            chooseSeries();
        }
    }

    public void seriesOption(int input) {
        if (input == 1) {
            System.out.println("The series is now playing.");
        } else if (input == 2) {
            System.out.println("Enter the name of the series you want to save:");
            String seriesName = scan.nextLine();
            try (FileWriter csvWriter = new FileWriter("Data/SavedSeriesList.csv", true)) {
                csvWriter.append(seriesName).append("\n");
                System.out.println("The series has been added to your list.");
            } catch (IOException e) {
                System.out.println("Error saving series: " + e.getMessage());
            }
        } else {
            System.out.println("Option does not exist, please choose the available options.");
        }
    }
}
