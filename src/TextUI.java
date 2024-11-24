import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TextUI {
    Scanner scan;
    UserHandler userHandler;

    public TextUI(UserHandler userHandler){
        this.userHandler = userHandler;
        this.scan = new Scanner(System.in);
    }

    public String getUserInput(){
        System.out.println("Welcome to Netflix stream");
        System.out.println("Select 1 to log in or 2 to sign up");
        return scan.nextLine();
    }

    public void UserLogin(){
        System.out.println("Please enter your username.");
        String username = scan.nextLine();

        System.out.println("Please enter your password");
        String password = scan.nextLine();
        if(userHandler.login(password,username)){
            System.out.println("Welcome to Chills " + username);
            System.out.println("\n\n");
        } else {
            int passwordAttempts = 0; //initialized to 0 to track the number of failed login attempts.
            while(passwordAttempts < 4){ //his allows the user up to four additional login attempts.
                System.out.println("Your password or username is incorect please try again");
                System.out.println("Please enter your username");
                username = scan.nextLine();
                System.out.println("please enter your password");
                password = scan.nextLine();
                if(userHandler.login(username, password)){
                    System.out.println("Welcome to Netflix stream");
                    break;
                } else {
                    passwordAttempts++;
                } if(passwordAttempts == 4) {
                    System.out.println("Maximum login attempts have been reached. Shutting down...");
                }
            }
        }
    }
    public void CreatAUser(){
        System.out.println("Please enter your full name: ");
        String fullname = scan.nextLine();
        System.out.println("Please Creat a username");
        String username = scan.nextLine();
        System.out.println("Please create a password (it should be between 5 and 9 characters)");
        String password = scan.next();

        if(userHandler.createUser(fullname, username, password)){
            System.out.println("Welcome to Chills  " + username);
            System.out.println("\n\n");
        } else{
            System.out.println("Invalid username or password. Please log in or sign up.");
        }
    }

    public void pickMedia(ArrayList<Media> medias) {

        TextUI textUI = new TextUI(userHandler);
        System.out.println("Menu: Choose type of media: ");

        Scanner scan = new Scanner(System.in);
        System.out.println("1-Movies");
        System.out.println("2-Series");
        System.out.println("3-watched movies");
        System.out.println("4-saved movies");
        int input = scan.nextInt();
        if (input == 1) {
            System.out.println("You choose movies, here's some options");
            for (int i = 0; i < medias.size(); i++) {
                String movies = i + " - " + medias.get(i).getTitel();
                System.out.println(movies);
            }
            chooseMovie();
            youHaveChosenMovie();

        } else if (input == 2) {
            System.out.println("You choose series, here's some options");
        } else if (input == 3) {
            System.out.println("You choose your watched list: ");
        }  else if (input == 4) {
            System.out.println("You choose your saved list: ");
        }
    }

    public void chooseMovie() {
        try (Scanner cs = new Scanner(System.in);) {
            System.out.println("\n\n");
            System.out.println("Please press the number of the movie you want to watch");
            int movieNumberToWatch = cs.nextInt();
            System.out.println("you have chosen " + choseResults(movieNumberToWatch));
        } catch (Exception e) {
            System.out.println("you cannot write letters, write only number, you can try again now   ");
            Scanner cs = new Scanner(System.in);
            System.out.println("\n\n");
            System.out.println("Please press the number of the movie you want to watch");
            int movieNumberToWatch = cs.nextInt();
            System.out.println("you have chosen " + choseResults(movieNumberToWatch));
        }
    }
        public String choseResults ( int movieNumberToWatch){
            ArrayList<Media> wannaWatch = MovieAndSeriesLab.movies;
            return wannaWatch.get(movieNumberToWatch).getTitel();
        }

    public void youHaveChosenMovie() {
        Scanner scanner = new Scanner(System.in); // Ensure Scanner is properly set up
        System.out.print("Enter your choice: ");
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            System.out.println("You selected: " + choice);
        } else {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    public void movieOption(int input){
        Scanner scanner = new Scanner(System.in);
        if (input == 1) {
            System.out.println("The movie is now playing ");

        } else if (input == 2) {
            System.out.println("Enter the name of the movie you want to save: ");
            String movieName = scanner.nextLine();
            try {
                FileWriter csvWriter = new FileWriter("data/SavedMoviesList.csv", true);
                csvWriter.append(movieName);
                csvWriter.append("\n");
                csvWriter.flush();
                csvWriter.close();
                System.out.println("The movie has been added to your list.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Option does not exist, please choose the available options ");
            }
        }
    }