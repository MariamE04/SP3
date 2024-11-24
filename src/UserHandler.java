import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHandler {
    ArrayList<User> users = new ArrayList<>();
    File file;


    public UserHandler(String filePath) {
        this.file = new File(filePath);
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean createUser(String fullName, String username, String password) {
        boolean usernameTaken = false;
        if(!isPasswordValid(password)){
            return false;
        }
        for (User users : users) {
            if (users.getUsername().equals(username))
                return false;

        }
        users.add(new User(fullName, password, username));
        return true;
    }

    public boolean isPasswordValid(String password) {
        if(password == null || password.length() <= 5 || password.length() >10){
            System.out.println("the password is not meeting conditons ");
            return false;
        }
        else{

            return true ;
        }
    }

    public void loadUsers() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] values = input.split(",");
                users.add(new User(values[0], values[1], values[2]));
                }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: Unable to load users.");
        }
    }
    public void saveUsers(){
        try(FileWriter writer = new FileWriter(file)){
            for (User users : users){
                writer.write(users.getFullName() +"," +users.getUsername() +","+ users.getPassword()+
                        "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("system is not working currently ");

        }

    }

}