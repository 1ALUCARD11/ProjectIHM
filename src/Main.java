import javax.swing.*;
import java.sql.Connection;

public class Main {


    public static void main(String[] args) {

        Connection connection = Methodes.connect("gestionbibliotheque");
        new MainPanel();
        for (UIManager.LookAndFeelInfo lafinfo: UIManager.getInstalledLookAndFeels()) {
            System.out.println(lafinfo.getClassName());

            
        }
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        String file_name;

    }


}