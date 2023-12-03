import javax.swing.*;
import java.awt.*;


public class MainPanel extends JFrame {
    private JPanel mainpanel;
    private JButton admin_b;
    private JButton livres_b;
    private JButton enseignant_b;
    private JButton memoire_b;
    private JButton accueil_b;
    private JPanel nav_panel;
    private JPanel header_panel;
    private JPanel livre_p;
    private JPanel enseignant_p;
    private JPanel memoires_p;
    private JPanel accueil_p;
    private JPanel cardpanel;

    MainPanel() {
        setContentPane(mainpanel);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        nav_panel.setBackground(new Color(0xFF0F39A4, true));
        header_panel.setBackground(new Color(0xFF0F39A4, true));

        setVisible(true);
        admin_b.addActionListener(e -> {
            AdminLogData addata = new AdminLogData();
            new LoginPage(addata.getLoginInfo());
        });
        livres_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Livres");
        });
        enseignant_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Enseignant");
        });
        memoire_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Memoires");
        });
        accueil_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Accueil");
        });

        accueil_p.setBackground(Color.CYAN);
        memoires_p.setBackground(Color.DARK_GRAY);
        enseignant_p.setBackground(Color.RED);
        livre_p.setBackground(Color.YELLOW);
    }
}
