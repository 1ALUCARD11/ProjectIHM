import net.proteanit.sql.DbUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
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

    private final JTable memoiresTable = new JTable();
    private final JTable enseignantTable = new JTable();
    private final JTable livresTable = new JTable();

    private int currentCard=0;
    private ResultSet rs;

    private final Connection connection;

    public MainPanel() {
        connection = Methodes.connect("gestionbibliotheque");
        mainpanel.setLayout(new BorderLayout());

        nav_panel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JPanel topPartPanel = new JPanel(new BorderLayout());
        JLabel logoLabel = new JLabel("test");
        topPartPanel.add(logoLabel);
        JPanel adminPanel = new JPanel();
        adminPanel.add(admin_b);
        topPartPanel.add(adminPanel, BorderLayout.EAST);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        nav_panel.add(topPartPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;

        header_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        header_panel.add(livres_b);
        header_panel.add(memoire_b);
        header_panel.add(enseignant_b);
        toppartpanel.setLayout(new GridLayout(2, 1));
        toppartpanel.add(nav_panel);
        toppartpanel.add(header_panel);
        toppartpanel.setSize(500, 500);
        toppartpanel.setBackground(Color.white);

        cardpanel.setLayout(new CardLayout());

        JComboBox<String> typeRechercheLivres = new JComboBox<>(new String[]{"Titre", "Auteur", "Année", "Cote", "Emplacement"});

        createDataPanel(livre_p, recherchlivre_p, tablivre_p, resultat, rechlivre_tf, rechlivre_b, livresTable, "livre",typeRechercheLivres);
        cardpanel.add(livre_p, "Livres");

        JComboBox<String> typeRechercheEnseignants = new JComboBox<>(new String[]{"Nom", "Prénom", "Spécialité"});


        createDataPanel(enseignant_p, recherchens_p, tabens_p, resultat1, rechens_tf, rechens_b, enseignantTable, "enseignant",typeRechercheEnseignants);
        cardpanel.add(enseignant_p, "Enseignant");

        JComboBox<String> typeRechercheMemoires = new JComboBox<>(new String[]{"Titre", "Étudiants", "Année", "Spécialité", "Encadreur", "Cote", "Mots clés", "Détails"});


        createDataPanel(memoires_p, recherchmem_p, tabmem_p, resultat2, rechmemoire_tf, rechmemoire_b, memoiresTable, "memoire",typeRechercheMemoires);
        cardpanel.add(memoires_p, "Memoires");

        mainpanel.add(cardpanel, BorderLayout.CENTER);
        mainpanel.add(toppartpanel, BorderLayout.NORTH);
        setContentPane(mainpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        header_panel.setBackground(new Color(0xFF1036B6, true));
        header_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        setVisible(true);
        admin_b.addActionListener(e -> {
            AdminLogData addata = new AdminLogData();
            new LoginPage(addata.getLoginInfo());
        });
        livres_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Livres");
            currentCard=0;
        });
        memoire_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Memoires");
            currentCard=1;
        });
        enseignant_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Enseignant");
            currentCard=2;
        });
        accueil_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) cardpanel.getLayout();
            cardlayout.show(cardpanel, "Accueil");
        });
        applyModernDesign();  // Nouvelle méthode pour appliquer le design moderne
    }

    private void createDataPanel(JPanel mpan, JPanel searchpan, JPanel tablepan,JLabel resultat, JTextField recherchtf, JButton button, JTable table,String check,JComboBox<String> filterComboBox) {

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

        // Modification de la section RECHERCHER
        searchpan.setLayout(new BorderLayout());
        searchpan.setBackground(Color.WHITE);

        // Zone de recherche avec icone
        Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        recherchtf.setBorder(BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 40, 5, 10)));
        recherchtf.setFont(new Font("Bebas Neue", Font.PLAIN, 16));
        recherchtf.setPreferredSize(new Dimension(200, 30));

        // Icone dans la zone de recherche
        ImageIcon searchIcon = new ImageIcon("src/search.png");
        Image image=searchIcon.getImage();
        Image image1=image.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        ImageIcon img=new ImageIcon(image1);
        JLabel searchIconLabel = new JLabel(img);
        searchIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à effectuer lors du clic sur l'icône de recherche
                System.out.println("Search Icon Clicked and current : " + currentCard);
                String critere= Objects.requireNonNull(filterComboBox.getSelectedItem()).toString().toLowerCase();
                String texte= recherchtf.getText();
                critere= StringUtils.stripAccents(critere);

                try {
                    Methodes.rechercheCritere(currentCard,critere,texte,table);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        // Filtre
        JPanel filterPanel = new JPanel(new BorderLayout());
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        JLabel filterLabel = new JLabel("Rechercher par");
        filterLabel.setFont(new Font("Bebas Neue", Font.PLAIN, 16));
        filterComboBox.setFont(new Font("Bebas Neue", Font.PLAIN, 14));

        filterPanel.add(filterLabel, BorderLayout.NORTH);
        filterPanel.add(filterComboBox, BorderLayout.CENTER);
// DE ICI JUSQU A
        // Ajout des composants a la zone de recherche
        searchpan.add(searchIconLabel, BorderLayout.WEST);
        searchpan.add(recherchtf, BorderLayout.CENTER);
        searchpan.add(filterPanel, BorderLayout.EAST);

        // Ajout de la zone de recherche au panel principal
        mpan.add(searchpan, BorderLayout.NORTH);
        // Fin des modifications de la section RECHERCHER

        // Ajout de la section RESULTAT
        JPanel resultPanel = new JPanel(new BorderLayout());
        JLabel resultTitleLabel = new JLabel("RESULTAT DE LA RECHERCHE");
        resultTitleLabel.setFont(new Font("Bebas Neue", Font.PLAIN, 24));
        resultTitleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Ligne séparatrice
        JSeparator separator = new JSeparator();

        // Ajout du titre et de la séparatrice
        resultPanel.add(resultTitleLabel, BorderLayout.NORTH);
        resultPanel.add(separator, BorderLayout.CENTER);

        // JUSAU A ICI MATMCHICH

        // Ajout du tableau en dessous
        resultPanel.add(tablepan, BorderLayout.SOUTH);

        mpan.add(resultPanel, BorderLayout.CENTER);
        // Fin de la section RESULTAT


        tablepan.setLayout(new GridLayout(2, 1));
        tablepan.add(resultat);
        table.setDefaultEditor(Object.class, null);


        JScrollPane sp = new JScrollPane(table);
        tablepan.add(sp);
        mpan.add(tablepan, BorderLayout.CENTER);

        admin_b.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        admin_b.setBackground(Color.WHITE);
        admin_b.setOpaque(true);

        admin_b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        admin_b.setFocusPainted(false);
        admin_b.setFont(new Font("Montserrat", Font.PLAIN, 14));

        Font montserratFont = new Font("Bebas Neue", Font.PLAIN, 25);

        accueil_b.setFont(montserratFont);
        livres_b.setFont(montserratFont);
        memoire_b.setFont(montserratFont);
        enseignant_b.setFont(montserratFont);

        memoire_b.setForeground(Color.WHITE);
        accueil_b.setForeground(Color.WHITE);
        enseignant_b.setForeground(Color.WHITE);
        livres_b.setForeground(Color.WHITE);

        accueil_b.setOpaque(false);
        accueil_b.setContentAreaFilled(false);
        accueil_b.setBorderPainted(false);

        livres_b.setOpaque(false);
        livres_b.setContentAreaFilled(false);
        livres_b.setBorderPainted(false);

        memoire_b.setOpaque(false);
        memoire_b.setContentAreaFilled(false);
        memoire_b.setBorderPainted(false);

        enseignant_b.setOpaque(false);
        enseignant_b.setContentAreaFilled(false);
        enseignant_b.setBorderPainted(false);

        Dimension buttonSize = new Dimension(150, 40);
        accueil_b.setPreferredSize(buttonSize);
        livres_b.setPreferredSize(buttonSize);
        memoire_b.setPreferredSize(buttonSize);
        enseignant_b.setPreferredSize(buttonSize);
    }

    private void applyModernDesign() {
        // Appliquer le design moderne ici (bordures blanches, alternance de couleurs, polices, etc.)
        applyTableDesign(livresTable);
        applyTableDesign(enseignantTable);
        applyTableDesign(memoiresTable);
    }

    private void applyTableDesign(JTable table) {
        // Design moderne pour les tables (bordures blanches, alternance de couleurs, polices, etc.)
        table.getTableHeader().setFont(new Font("Bebas Neue", Font.PLAIN, 16));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0xFFF56E00, true));
        table.getTableHeader().setForeground(Color.WHITE);

        table.setFont(new Font("Montserrat", Font.PLAIN, 14));
        table.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,
                new CustomTableCellRenderer());
    }

}

