import javax.swing.*;
import java.sql.Connection;

public class Main {


    public static void main(String[] args) {

        Connection connection = Methodes.connect("gestionbibliotheque");
      //  new MainPanel();
        new AdminPage();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        String file_name;

    }


}