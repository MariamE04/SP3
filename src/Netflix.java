import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Netflix {
    private List<User> users;
    private TextUI ui;
    private FileIO io;
    private ArrayList<Movies> moviesList;
    private ArrayList<Series> seriesList;
    private UserHandler userHandler;
    private String UserDataPath;

    public Netflix() {
        ui = new TextUI(userHandler);
        this.io = new FileIO(); // Create an instance of FileIO
        this.users = new ArrayList<>();

        this.UserDataPath = "data/Userdata.csv";
    }


    }