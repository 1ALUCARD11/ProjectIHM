import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private final JPanel card_p = new JPanel();

    private final JLabel titreprincipal_l =new JLabel();

    /*########################################### LIVRES PART ######################################################### */
    private final JLabel livre_l = new JLabel("Gestion Livres ");

    private final JTextField titreLivre_tf= new JTextField(15);
    private final JTextField anneeLivre_tf= new JTextField(15);
    private final JTextField auteurLivre_tf= new JTextField(15);
    private final JTextField coteLivre_tf= new JTextField(15);

    private final JTable livresTable_t = new JTable();
    private final JTextField recherchLivre_tf = new JTextField(15);


    private final JTextField nomEnseignant_tf = new JTextField(15);
    private final JTextField preEnseignant_tf = new JTextField(15);
    private final JTextField specEnseignant_tf = new JTextField(15);
    private final JTable enseignantsTable_t = new JTable();

    private final JButton rechercheEnseignant_b= new JButton("Recherche");
    private final JTextField recherchEnseignant_tf = new JTextField();

    private final JTextField rechercheMemoire_tf= new JTextField(15);

    private final JTextField titre_tf = new JTextField();

    private final JTextField cote_tf = new JTextField(15);
    private final JTextField resume_tf = new JTextField(15);

    private final JTextField etudiants_tf= new JTextField(15);
    private final JTextField annee_tf= new JTextField(15);
    private final JTextField enseignant_tf= new JTextField(15);
    private final JTable memoires_t= new JTable();

    /*############################################################################################################### */








    private Path thepath;

    public AdminPage(){
        connection = Methodes.connect("gestionbibliotheque");
        memoires_t.setRowHeight(20);
        livresTable_t.setRowHeight(20);
        enseignantsTable_t.setRowHeight(20);
        Methodes.fillTable("memoire",connection, memoires_t);
        Methodes.fillTable("livre",connection, livresTable_t);
        Methodes.fillTable("enseignant",connection, enseignantsTable_t);
        setTitle("Gestion des donnes ");
        JPanel main_p = new JPanel();
        main_p.setLayout(new BorderLayout());
        main_p.setBorder(new EmptyBorder(3,5,3,5));
        card_p.setLayout(new CardLayout());
        JPanel memoires_p = new JPanel();
        card_p.add(memoires_p,"memoires");
        JPanel livres_p = new JPanel();
        card_p.add(livres_p,"livres");
        JPanel enseignant_p = new JPanel();
        card_p.add(enseignant_p,"enseignants");

        /* #########################################################################################*/

        JPanel nav_p = new JPanel();
        nav_p.setLayout(new GridLayout(1,3));
        nav_p.setBorder(new EmptyBorder(3,0,3,0));
        JPanel b1 = new JPanel(new FlowLayout(FlowLayout.LEFT,5,35));

        /*############################################################################################################### */
        /*####################################### MEMOIRE PART ############################################################# */
        JButton memoire_b = new JButton("Gestion Memoire");
        b1.add(memoire_b);
        JPanel b2 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,35));
        JButton livre_b = new JButton("Gestion Livres");
        b2.add(livre_b);
        JPanel b3 = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,35));
        /*############################################################################################################### */
        /*############################################## ENSEIGNANT PART ###################################################### */
        JButton ensignant_b = new JButton("Gestion Enseignant");
        b3.add(ensignant_b);

        nav_p.add(b1);
        nav_p.add(b2);
        nav_p.add(b3);




        nav_p.setPreferredSize(new Dimension(1,100));
        JPanel h= new JPanel();
        h.setLayout(new BoxLayout(h,BoxLayout.Y_AXIS));
        JPanel title_p = new JPanel();
        title_p.setBackground(Color.YELLOW);
        title_p.setPreferredSize(new Dimension(700,50));
        h.add(title_p);
        h.add(nav_p);

        main_p.add(h,BorderLayout.NORTH);

        /* ########################## ENSEIGNANT PART  ###############################################################*/

        enseignant_p.setLayout(new GridLayout(1,1));

        JPanel enseignantForm_p = new JPanel();
        enseignantForm_p.setLayout(new BoxLayout(enseignantForm_p,BoxLayout.PAGE_AXIS));
        enseignantForm_p.setBorder(new EmptyBorder(5,5,5,5));

        JLabel nomEnseignant_l = new JLabel("Nom ");
        nomEnseignant_l.setLabelFor(nomEnseignant_tf);
        nomEnseignant_l.setDisplayedMnemonic('n');
        nomEnseignant_l.setPreferredSize(new Dimension(200,50));
        enseignantForm_p.add(nomEnseignant_l);
        nomEnseignant_tf.setMaximumSize(new Dimension(700,100));
        nomEnseignant_tf.setPreferredSize(new Dimension(200,40));
        enseignantForm_p.add(nomEnseignant_tf);

        JLabel prenomEnseignant_l = new JLabel("Prenom ");
        prenomEnseignant_l.setPreferredSize(new Dimension(200,50));
        enseignantForm_p.add(prenomEnseignant_l);
        preEnseignant_tf.setMaximumSize(new Dimension(700,100));
        preEnseignant_tf.setPreferredSize(new Dimension(200,40));

        enseignantForm_p.add(preEnseignant_tf);

        JLabel specEnseignant_l = new JLabel("Specialite ");
        specEnseignant_l.setPreferredSize(new Dimension(200,50));

        enseignantForm_p.add(specEnseignant_l);

        specEnseignant_tf.setMaximumSize(new Dimension(700,100));
        specEnseignant_tf.setPreferredSize(new Dimension(200,40));

        enseignantForm_p.add(specEnseignant_tf);



        enseignant_p.add(enseignantForm_p,0);
        JPanel enseignantTable_p = new JPanel();
        enseignantTable_p.setLayout(new BorderLayout());
        enseignantTable_p.setBorder(new EmptyBorder(5,5,5,5));
        enseignant_p.add(enseignantTable_p,1);


        enseignantForm_p.add(Box.createGlue());
        JPanel s = new JPanel(new GridLayout(1,3));
        s.setPreferredSize(new Dimension(500,40));
        s.setMaximumSize(new Dimension(400,40));
        s.setBorder(new EmptyBorder(10,0,0,0));
        JButton sauvegardeEnseignant_b = new JButton("Ajouter");
        s.add(sauvegardeEnseignant_b);
        JButton supprimerEnseignant_b = new JButton("Supprimer");
        s.add(supprimerEnseignant_b);
        JButton modifierEnseignant_b = new JButton("Mise a Jour");
        s.add(modifierEnseignant_b);
        enseignantForm_p.add(s);

        enseignantsTable_t.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int i = enseignantsTable_t.getSelectedRow();
                if (i != -1) {
                    int id_int = (int) enseignantsTable_t.getValueAt(i, 0);
                    id_enseignant = String.valueOf(id_int);
                    System.out.println("the id of the eneseisifsi si = " + id_enseignant);

                }

                try {
                    pst = connection.prepareStatement("select nom,prenom,specialite from enseignant where id_enseignant = ? ");

                    pst.setString(1, id_enseignant);
                    ResultSet rs = pst.executeQuery();


                    if (rs.next()) {
                        nomEnseignant_tf.setText(rs.getString(1));
                        preEnseignant_tf.setText(rs.getString(2));
                        specEnseignant_tf.setText(rs.getString(3));

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



            }


        });

        JScrollPane scpEnseignant = new JScrollPane(enseignantsTable_t);
        enseignantTable_p.add(scpEnseignant,BorderLayout.CENTER);
        JPanel tens = new JPanel(new GridLayout(1,2));
        tens.add(rechercheEnseignant_b);tens.add(recherchEnseignant_tf);
        enseignantTable_p.add(tens,BorderLayout.SOUTH);





        enseignantsTable_t.setDefaultEditor(Object.class,null);
        ensignant_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) card_p.getLayout();
            cardlayout.show(card_p, "enseignants");
        });




        sauvegardeEnseignant_b.addActionListener(e -> Methodes.addEnseignant(connection,enseignantsTable_t,nomEnseignant_tf,preEnseignant_tf,specEnseignant_tf));


        rechercheEnseignant_b.addActionListener(e -> {
            String id = recherchEnseignant_tf.getText();
            Methodes.searchEnseignant(connection,enseignantsTable_t,recherchEnseignant_tf.getText().isEmpty(),id,nomEnseignant_tf,preEnseignant_tf,specEnseignant_tf);
        });
        modifierEnseignant_b.addActionListener(e -> {
            String id;
            if (recherchEnseignant_tf.getText().isEmpty()){
                id = id_enseignant;
            }else id = recherchEnseignant_tf.getText();
            Methodes.updateEnseignant(connection,enseignantsTable_t,id,nomEnseignant_tf,preEnseignant_tf,specEnseignant_tf);

        });
        supprimerEnseignant_b.addActionListener(e -> Methodes.deleteEnseignant(connection,enseignantsTable_t,id_enseignant,nomEnseignant_tf,preEnseignant_tf,specEnseignant_tf));










        /* ########################## ENSEIGNANT PART  ###############################################################*/
        /* ########################## LIVRE PART  ###############################################################*/

        livres_p.setLayout(new GridLayout(1,1));

        JPanel livreForm_p = new JPanel();
        livreForm_p.setLayout(new BoxLayout(livreForm_p,BoxLayout.PAGE_AXIS));
        livreForm_p.setBorder(new EmptyBorder(5,5,5,5));

        JLabel titreLivre_l = new JLabel("Titre ");
        titreLivre_l.setPreferredSize(new Dimension(200,50));
        livreForm_p.add(titreLivre_l);
        titreLivre_tf.setMaximumSize(new Dimension(700,100));
        titreLivre_tf.setPreferredSize(new Dimension(200,40));
        livreForm_p.add(titreLivre_tf);

        JLabel auterLivre_l = new JLabel("Autuer ");
        auterLivre_l.setPreferredSize(new Dimension(200,50));
        livreForm_p.add(auterLivre_l);
        auteurLivre_tf.setMaximumSize(new Dimension(700,100));
        auteurLivre_tf.setPreferredSize(new Dimension(200,40));

        livreForm_p.add(auteurLivre_tf);

        JLabel anneeLivre_l = new JLabel("Annee ");
        anneeLivre_l.setPreferredSize(new Dimension(200,50));

        livreForm_p.add(anneeLivre_l);

        anneeLivre_tf.setMaximumSize(new Dimension(700,100));
        anneeLivre_tf.setPreferredSize(new Dimension(200,40));

        livreForm_p.add(anneeLivre_tf);

        JLabel coteLivre_l = new JLabel("Cote");
        coteLivre_l.setPreferredSize(new Dimension(200,50));

        livreForm_p.add(coteLivre_l);
        coteLivre_tf.setMaximumSize(new Dimension(700,100));
        coteLivre_tf.setPreferredSize(new Dimension(200,40));

        livreForm_p.add(coteLivre_tf);



        livres_p.add(livreForm_p,0);
        JPanel livreTable_p = new JPanel();
        livreTable_p.setLayout(new BorderLayout());
        livreTable_p.setBorder(new EmptyBorder(5,5,5,5));
        livres_p.add(livreTable_p,1);

        JPanel l4 = new JPanel(new GridLayout(1,1));
        l4.setBorder(new EmptyBorder(5,5,5,5));
        JButton ajouterLivrePdf_b = new JButton("PDF");
        ajouterLivrePdf_b.setPreferredSize(new Dimension(700,25));
        livreForm_p.add(Box.createRigidArea(new Dimension(700,45)));

        l4.add(ajouterLivrePdf_b);
        l4.setPreferredSize(new Dimension(200,60));
        livreForm_p.add(l4);
        livreForm_p.add(Box.createRigidArea(new Dimension(700,150)));



        JPanel sp = new JPanel(new GridLayout(1,3));
        sp.setPreferredSize(new Dimension(500,40));
        sp.setMaximumSize(new Dimension(400,40));
        sp.setBorder(new EmptyBorder(10,0,0,0));
        JButton sauvegardeLivre_b = new JButton("Ajouter");
        sp.add(sauvegardeLivre_b);
        JButton supprimerLivre_b = new JButton("Supprimer");
        sp.add(supprimerLivre_b);
        JButton miseajourLivre_b = new JButton("Mise A Jour");
        sp.add(miseajourLivre_b);
        livreForm_p.add(sp);
        livresTable_t.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int i = livresTable_t.getSelectedRow();
                if (i != -1) {
                    int id_int = (int) livresTable_t.getValueAt(i, 0);
                    id_livre = String.valueOf(id_int);
                    System.out.println(id + "   this is the id ");
                }

                try {
                    pst = connection.prepareStatement("select titre,auteur,annee,cote from livre where id_livre = ? ");

                    pst.setString(1, id_livre);
                    ResultSet rs = pst.executeQuery();


                    if (rs.next()) {
                        titreLivre_tf.setText(rs.getString(1));
                        auteurLivre_tf.setText(rs.getString(2));
                        anneeLivre_tf.setText(rs.getString(3));
                        coteLivre_tf.setText(rs.getString(4));


                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });

        JScrollPane scplivre = new JScrollPane(livresTable_t);
        livreTable_p.add(scplivre,BorderLayout.CENTER);
        JPanel t = new JPanel(new GridLayout(1,2));
        JButton recherchLivre_b = new JButton("Recherch");
        t.add(recherchLivre_b);t.add(recherchLivre_tf);
        livreTable_p.add(t,BorderLayout.SOUTH);

        livresTable_t.setDefaultEditor(Object.class,null);
        livre_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) card_p.getLayout();
            cardlayout.show(card_p, "livres");
        });

        sauvegardeLivre_b.addActionListener(e -> Methodes.addLivre(connection,livresTable_t,titreLivre_tf,auteurLivre_tf,anneeLivre_tf,coteLivre_tf,thepath.toString()));

        recherchLivre_b.addActionListener(e -> {
            System.out.println("the button is buttining");
            String idl =recherchLivre_tf.getText();
            Methodes.searchLivre(connection,livresTable_t,recherchLivre_tf.getText().isEmpty(),idl,titreLivre_tf,auteurLivre_tf,anneeLivre_tf,coteLivre_tf);
        });
        miseajourLivre_b.addActionListener(e -> {
            String idl;
            if (recherchLivre_tf.getText().isEmpty()){
                idl = id_livre;
            }else idl =recherchLivre_tf.getText();
            Methodes.updateLivre(connection,livresTable_t,idl,titreLivre_tf,auteurLivre_tf,anneeLivre_tf,coteLivre_tf,thepath.toString());

        });
        supprimerLivre_b.addActionListener(e -> {
            String idl;
            if (recherchLivre_tf.getText().isEmpty()){
                idl = id_livre;
            }else idl =recherchLivre_tf.getText();
            Methodes.deleteLivre(connection,livresTable_t,idl,titreLivre_tf,auteurLivre_tf,anneeLivre_tf,coteLivre_tf);
        });

        ajouterLivrePdf_b.addActionListener(e -> thepath = Methodes.addpdfs("Livres",titreLivre_tf.getText()));
        /* ################################ MEMOIRE PART #########################################################*/
        memoires_p.setLayout(new GridLayout(1,1));
        JPanel memoireForm_p = new JPanel();
        memoireForm_p.setLayout(new BoxLayout(memoireForm_p,BoxLayout.PAGE_AXIS));
        memoireForm_p.setBorder(new EmptyBorder(5,5,5,5));
        JLabel titre_l = new JLabel("Titre :");
        titre_l.setPreferredSize(new Dimension(200,50));
        memoireForm_p.add(titre_l);
        titre_tf.setMaximumSize(new Dimension(700,100));
        titre_tf.setPreferredSize(new Dimension(200,40));
        memoireForm_p.add(titre_tf);
        JLabel etudiant_l = new JLabel("Etudiant");
        etudiant_l.setPreferredSize(new Dimension(200,50));
        memoireForm_p.add(etudiant_l);
        etudiants_tf.setMaximumSize(new Dimension(700,100));
        etudiants_tf.setPreferredSize(new Dimension(200,40));
        memoireForm_p.add(etudiants_tf);
        JLabel annee_l = new JLabel("Annee");
        annee_l.setPreferredSize(new Dimension(200,50));
        memoireForm_p.add(annee_l);
        annee_tf.setMaximumSize(new Dimension(700,100));
        annee_tf.setPreferredSize(new Dimension(200,40));
        memoireForm_p.add(annee_tf);
        JLabel cote_l = new JLabel("La Cote");
        cote_l.setPreferredSize(new Dimension(200,50));
        memoireForm_p.add(cote_l);
        cote_tf.setMaximumSize(new Dimension(700,100));
        cote_tf.setPreferredSize(new Dimension(200,40));
        memoireForm_p.add(cote_tf);
        JLabel resume_l = new JLabel("Resume");
        resume_l.setPreferredSize(new Dimension(200,50));
        memoireForm_p.add(resume_l);
        resume_tf.setMaximumSize(new Dimension(700,100));
        resume_tf.setPreferredSize(new Dimension(200,40));
        memoireForm_p.add(resume_tf);
        JLabel enseignant_l = new JLabel("Enseignat");
        enseignant_l.setPreferredSize(new Dimension(200,50));
        memoireForm_p.add(enseignant_l);
        enseignant_tf.setMaximumSize(new Dimension(700,100));
        enseignant_tf.setPreferredSize(new Dimension(200,40));
        memoireForm_p.add(enseignant_tf);
        memoires_p.add(memoireForm_p,0);
        JPanel memoireTable_p = new JPanel();
        memoireTable_p.setLayout(new BorderLayout());
        memoireTable_p.setBorder(new EmptyBorder(5,5,5,5));
        memoires_p.add(memoireTable_p,1);
        JPanel b4 = new JPanel(new GridLayout(1,1));
        b4.setBorder(new EmptyBorder(5,5,5,5));
        JButton ajouterMemoirePDF_b = new JButton("PDF");
        b4.add(ajouterMemoirePDF_b);
        memoireForm_p.add(b4);
        enseignant_tf.setMaximumSize(new Dimension(700,100));
        ajouterMemoirePDF_b.setPreferredSize(new Dimension(700,25));
        memoireForm_p.add(Box.createGlue());
        JPanel sub_p = new JPanel(new GridLayout(1,3));
        sub_p.setPreferredSize(new Dimension(500,40));
        sub_p.setMaximumSize(new Dimension(400,40));
        sub_p.setBorder(new EmptyBorder(10,0,0,0));
        JButton sauvegardeMemoire_b = new JButton("Ajouter");
        sub_p.add(sauvegardeMemoire_b);
        JButton supprimerMemoire_b = new JButton("Supprimer");
        sub_p.add(supprimerMemoire_b);
        JButton miseajourMemoire_b = new JButton("Mise a Jour");
        sub_p.add(miseajourMemoire_b);
        memoireForm_p.add(sub_p);
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
        JScrollPane scpMemoire = new JScrollPane(memoires_t);
        memoireTable_p.add(scpMemoire,BorderLayout.CENTER);
        JPanel tmp = new JPanel(new GridLayout(1,2));
        JButton rechercheMemoire_b = new JButton("Recherch");
        tmp.add(rechercheMemoire_b);tmp.add(rechercheMemoire_tf);
        memoireTable_p.add(tmp,BorderLayout.SOUTH);
        memoires_t.setDefaultEditor(Object.class, null);

        memoire_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) card_p.getLayout();
            cardlayout.show(card_p, "memoires");
        });

        sauvegardeMemoire_b.addActionListener(e -> Methodes.addMemoire(connection,
                memoires_t,
                etudiants_tf,
                titre_tf,
                annee_tf,
                cote_tf,
                resume_tf,
                enseignant_tf,
                thepath.toString()));

        rechercheMemoire_b.addActionListener(e -> {
            String id = rechercheMemoire_tf.getText();
            Methodes.searchMemoire(connection, memoires_t, id, rechercheMemoire_tf.getText().isEmpty(), titre_tf, etudiants_tf, annee_tf, cote_tf, resume_tf, enseignant_tf);


        });
        miseajourMemoire_b.addActionListener(e -> {
            String idm;
            if (rechercheMemoire_tf.getText().isEmpty()) {
                idm = id;
            } else idm = rechercheMemoire_tf.getText();

            Methodes.updateMemoire(connection, memoires_t, idm, titre_tf, etudiants_tf, annee_tf, cote_tf, resume_tf, enseignant_tf,thepath.toString());

        });
        supprimerMemoire_b.addActionListener(e -> {
            String ids;
            if (rechercheMemoire_tf.getText().isEmpty()) {
                ids = id;
            } else ids = rechercheMemoire_tf.getText();
            Methodes.deleteMemoire(connection, memoires_t, ids, titre_tf, etudiants_tf, annee_tf, cote_tf, resume_tf, enseignant_tf);

        });

        ajouterMemoirePDF_b.addActionListener(e -> thepath = Methodes.addpdfs("Memoires",titre_tf.getText()));

        // ##################### END OF MEMOIRE PANEL ##########################################################

        main_p.add(card_p,BorderLayout.CENTER);
        enseignantsTable_t.setDefaultEditor(Object.class ,null);
        setContentPane(main_p);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(800,800));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
