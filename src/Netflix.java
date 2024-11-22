import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Netflix {
    private List<User> users;
    private TextUI ui;
    private FileIO io;
    private String DataPath;;

    private ArrayList<Movies> moviesList;
    private ArrayList<Series> seriesList;

    public Netflix() {
        this.moviesList = new ArrayList<>();
        this.seriesList = new ArrayList<>();
    }


    FileIO fileIO = new FileIO();
    moviesList = fileIO.readMovieData();
    seriesList = fileIO.readSeries();

//Test, 100 skal være længden af alle movies i tekstfilen
void moviesAddList() {
    for (int i = 0; i < 100; i++) {
        moviesList.add(i, new Movies(io.readData));
    }
}


}