import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPage extends JFrame {
    Connection connection;
    PreparedStatement pst;
    String id,id_enseignant,id_livre;
    private JPanel main_p = new JPanel();
    private JButton livre_b = new JButton("Gestion Livres");
    private JButton ensignant_b = new JButton("Gestion Enseignant");
    private JButton memoire_b= new JButton("Gestion Memoire");
    private JPanel card_p = new JPanel();
    private JPanel memoires_p = new JPanel();
    private JPanel enseignant_p = new JPanel();
    private JPanel livres_p= new JPanel();
    private JLabel titreprincipal_l =new JLabel();
    private JPanel nav_p = new JPanel();
    private JTextField titre_tf = new JTextField(15);
    private JLabel titre_l = new JLabel("Titre :");
    private JTextField cote_tf = new JTextField(15);
    private JLabel cote_l = new JLabel("La Cote");
    private JTextField resume_tf = new JTextField(15);
    private JLabel resume_l = new JLabel("Resume");

    private JTextField etudiants_tf= new JTextField(15);
    private JLabel etudiant_l = new JLabel("Etudiant");
    private JTextField annee_tf= new JTextField(15);
    private JLabel annee_l = new JLabel("Annee");
    private JTextField enseignant_tf= new JTextField(15);
    private JLabel enseignant_l = new JLabel("Enseignat");
    private JTable memoires_t= new JTable();
    private JButton sauvegarde_b = new JButton("Ajouter");
    private JButton miseajour_b = new JButton("Mise a Jour");
    private JButton supprimer_b =new JButton("Supprimer");
    private JButton recherche_b= new JButton("Recherch");
    private JTextField recherche_tf= new JTextField(15);
    private JScrollPane scplivre;
    private JTextField nomens_tf = new JTextField(15);
    private JTextField prenomens_tf = new JTextField(15);
    private JTextField specens_tf = new JTextField(15);
    private JTable enseignants_t = new JTable();
    private JButton ajouteens_b= new JButton("Ajouter");
    private JButton modifierens_b= new JButton("Mise a Jour");
    private JButton supprimerens_b= new JButton("Supprimer");
    private JButton rechercheens_b= new JButton("Recherche");
    private JTextField recherchens_tf = new JTextField();
    private JLabel nomens_l = new JLabel("Nom :");
    private JLabel prenomens_l = new JLabel("Prenom");
    private JLabel specens_l = new JLabel("Specialite");
    private JScrollPane scrollpantens;
    private JPanel modbuttons_p = new JPanel();
    private JTextField titrelivre_tf= new JTextField(15);
    private JTextField anneelivre_tf= new JTextField(15);
    private JTextField auteurlivre_tf= new JTextField(15);
    private JTextField cotelivre_tf= new JTextField(15);
    private JLabel glivre_l = new JLabel("Gestion Livres ");
    private JTable livres_t = new JTable();
    private JButton ajouterPdfButton = new JButton("PDF");
    private JButton ajouterlivre_b = new JButton("Ajouter");
    private JButton supprimerlivre_b = new JButton("Supprimer");
    private JButton modifierButton = new JButton("Mise A Jour");
    private JButton recherchLivre_b= new JButton("Recherch");
    private JTextField recherchlivre_tf = new JTextField(15);
    private JLabel titrelivre_l= new JLabel("Titre :");
    private JLabel anneelivre_l= new JLabel("Annee :");
    private JLabel auterlivre_l= new JLabel("Autuer :");
    private JLabel cotelivre_l= new JLabel("Cote");
    private JButton ajouterPDFButton = new JButton("PDF");
    private JPanel tlivre_p = new JPanel();
    private JPanel tform_p = new JPanel();
    private JPanel tserlivre_p = new JPanel();

    GridBagConstraints gbc = new GridBagConstraints();

    private  JFileChooser pdfchooser;

    private Path thepath;

    public AdminPage(){
        connection = Methodes.connect("gestionbibliotheque");
        Methodes.fillTable("memoire",connection, memoires_t);
        Methodes.fillTable("livre",connection, livres_t);
        Methodes.fillTable("enseignant",connection, enseignants_t);
        setTitle("Gestion des donnes ");
        main_p.setLayout(new BorderLayout());
        nav_p.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        nav_p.add(memoire_b);nav_p.add(livre_b);nav_p.add(ensignant_b);
        nav_p.setBackground(Color.pink);
        main_p.add(nav_p,BorderLayout.NORTH);
        livres_p.setLayout(new GridLayout(1,2));
        tform_p.setBackground(Color.darkGray);
        tform_p.setLayout(new GridLayout(3,4));

        tform_p.add(titre_l);

        tform_p.add(titre_tf);


        tform_p.add(etudiant_l);


        tform_p.add(etudiants_tf);

        tform_p.add(annee_l);


        tform_p.add(annee_tf);


        tform_p.add(cote_l);


        tform_p.add(cote_tf);

        tform_p.add(resume_l);


        tform_p.add(resume_tf);


        tform_p.add(enseignant_l);

        tform_p.add(enseignant_tf);


        livres_p.add(tform_p,0);
        tlivre_p.setBackground(Color.CYAN);
        tlivre_p.setLayout(new BorderLayout());
        livres_p.add(tlivre_p,1);





        card_p.add(livres_p,"livres");
//        card_p.add(enseignant_p,"enseignants");
//        card_p.add(memoires_p,"memoires");
        memoires_t.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int i = memoires_t.getSelectedRow();
                if (i != -1) {
                    int id_int = (int) memoires_t.getValueAt(i, 0);
                    id = String.valueOf(id_int);
                    System.out.println(id + " this is the id ");
                }

                try {
                    pst = connection.prepareStatement("select titre,etudiants,cote,resume,id_enseignant,annee from memoire where id_memoire = ? ");

                    pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();


                    if (rs.next()) {
                        titre_tf.setText(rs.getString(1));
                        etudiants_tf.setText(rs.getString(2));
                        cote_tf.setText(rs.getString(3));
                        resume_tf.setText(rs.getString(4));
                        enseignant_tf.setText(rs.getString(5));
                        annee_tf.setText(rs.getString(6));

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }


        });

        scplivre= new JScrollPane(livres_t);
        tlivre_p.add(scplivre,BorderLayout.CENTER);
        JPanel tmp = new JPanel(new GridLayout(1,2));
        tmp.add(recherchLivre_b);tmp.add(recherchlivre_tf);
        tlivre_p.add(tmp,BorderLayout.SOUTH);
        main_p.add(livres_p,BorderLayout.CENTER);

        setContentPane(main_p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800,800));
        setLocationRelativeTo(null);
        setVisible(true);

        memoires_t.setDefaultEditor(Object.class, null);
        enseignants_t.setDefaultEditor(Object.class ,null);
        livres_t.setDefaultEditor(Object.class,null);

    }

}
