import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Netflix {
    private List<User> users;
    private TextUI ui = new TextUI();
    private FileIO io;
    private ArrayList<Movies> moviesList;
    private ArrayList<Series> seriesList;

    public Netflix() {
        this.io = new FileIO(); // Create an instance of FileIO
        this.users = new ArrayList<>();
        this.moviesList = io.readMovieData(); // Use instance method
        this.seriesList = io.readSeries(); // Use instance method
    }

    public void moviesAddList() {
        for (int i = 0; i < moviesList.size(); i++) {
            System.out.println(moviesList.get(i)); // Example logic
        }
    }
}
