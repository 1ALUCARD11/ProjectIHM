import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Objects;


public class MainPanel extends JFrame {
    GridBagConstraints gbc = new GridBagConstraints();
    private final JPanel mainpanel = new JPanel();
    private final JPanel toppartpanel = new JPanel();
    private final JPanel nav_panel = new JPanel();
    private final JPanel header_panel = new JPanel();
    private final JPanel livre_p = new JPanel();
    private final JPanel recherchlivre_p = new JPanel();
    private final JPanel tablivre_p = new JPanel();
    private final JPanel enseignant_p = new JPanel();
    private final JPanel recherchens_p = new JPanel();
    private final JPanel tabens_p = new JPanel();
    private final JPanel memoires_p = new JPanel();
    private final JPanel recherchmem_p = new JPanel();
    private final JPanel tabmem_p = new JPanel();
    private final JPanel accueil_p = new JPanel();
    private final JPanel cardpanel = new JPanel();
    private final JButton admin_b = new JButton("ADMIN");
    private final JButton livres_b = new JButton("LIVRES");
    private final JButton enseignant_b = new JButton("ENSEIGNANTS");
    private final JButton memoire_b = new JButton("MEMOIRES");
    private final JButton accueil_b = new JButton("ACCUEIL");
    private final JButton rechlivre_b = new JButton("Recherche");
    private final JTextField rechlivre_tf = new JTextField(15);

    private final JButton rechens_b = new JButton("Recherche");
    private final JTextField rechens_tf = new JTextField(15);

    private final JButton rechmemoire_b = new JButton("Recherche");
    private final JTextField rechmemoire_tf = new JTextField(15);
    private final JLabel resultat = new JLabel("RESULTAT");
    private final JLabel resultat1 = new JLabel("RESULTAT");
    private final JLabel resultat2 = new JLabel("RESULTAT");


    private final JLabel titre1_l = new JLabel("UNIVERSITE DE BOUMERDES - FACULTE DES SCIENCES\n");
    private final JLabel titre2_l = new JLabel("DEPARTEMENT D'INFORMATIQUE");
    private final JTable memoiresTable = new JTable();
    private final JTable enseignantTable = new JTable();
    private final JTable livresTable = new JTable();


    private final Connection connection ;

    MainPanel() {
        connection = Methodes.connect("gestionbibliotheque");
//        Controller.fill();
        mainpanel.setLayout(new BorderLayout());
        nav_panel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        nav_panel.add(titre1_l, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        nav_panel.add(admin_b, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        nav_panel.add(titre2_l, gbc);
        header_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        header_panel.add(accueil_b);
        header_panel.add(livres_b);
        header_panel.add(memoire_b);
        header_panel.add(enseignant_b);
        toppartpanel.setLayout(new BorderLayout());
        toppartpanel.add(nav_panel, BorderLayout.NORTH);
        toppartpanel.add(header_panel, BorderLayout.CENTER);
        toppartpanel.setSize(500, 500);
        toppartpanel.setBackground(Color.white);

        cardpanel.setLayout(new CardLayout());


        createDataPanel(livre_p,recherchlivre_p,tablivre_p,resultat,rechlivre_tf,rechlivre_b,livresTable,"livre");
        cardpanel.add(livre_p, "Livres");

       createDataPanel(enseignant_p,recherchens_p,tabens_p,resultat1,rechens_tf,rechens_b,enseignantTable,"enseignant");
        cardpanel.add(enseignant_p, "Enseignant");

       createDataPanel(memoires_p,recherchmem_p,tabmem_p,resultat2,rechmemoire_tf,rechmemoire_b,memoiresTable,"memoire");
        cardpanel.add(memoires_p, "Memoires");


        mainpanel.add(cardpanel, BorderLayout.CENTER);
        mainpanel.add(toppartpanel, BorderLayout.NORTH);
        setContentPane(mainpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        nav_panel.setBackground(new Color(0xFFB4CC16, true));
        header_panel.setBackground(new Color(0xFF1036B6, true));

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
        mainpanel.setBackground(Color.BLACK);
        accueil_p.setBackground(Color.CYAN);
        memoires_p.setBackground(Color.DARK_GRAY);
        enseignant_p.setBackground(Color.RED);
        livre_p.setBackground(Color.YELLOW);


        setVisible(true);
    }

    private void createDataPanel(JPanel mpan, JPanel searchpan, JPanel tablepan,JLabel resultat, JTextField recherchtf, JButton button, JTable table,String check) {

        mpan.setLayout(new BorderLayout());
        searchpan.add(button);
        searchpan.add(recherchtf);
        mpan.add(searchpan, BorderLayout.NORTH);
        tablepan.setLayout(new GridLayout(2,1));
        tablepan.add(resultat);
        table.setDefaultEditor(Object.class, null);
        if (Objects.equals(check, "memoire")){
        Methodes.fillmemoiretable(connection,table);}
        else if (check.equals("enseignant")){
            Methodes.fillensetable(connection,table);
        }else if(check.equals("livre")) Methodes.filllivreetable(connection,table);


        JScrollPane sp = new JScrollPane(table);
        tablepan.add(sp);
        mpan.add(tablepan, BorderLayout.CENTER);

    }
}
