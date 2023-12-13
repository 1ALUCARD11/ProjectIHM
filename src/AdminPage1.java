import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPage1 extends JFrame {
    Connection connection;
    PreparedStatement pst;
    String id,id_enseignant,id_livre;
    private JPanel main_p;
    private JButton livre_b;
    private JButton ensignant_b;
    private JButton memoire_b;
    private JPanel card_p;
    private JPanel memoires_p;
    private JPanel enseignant_p;
    private JPanel livres_p;
    private JLabel titreprincipal_l;
    private JPanel nav_p;
    private JTextField titre_tf;
    private JTextField cote_tf;
    private JTextField resume_tf;
    private JLabel titre_l;
    private JTextField etudiants_tf;
    private JTextField annee_tf;
    private JTextField enseignant_tf;
    private JTable memoires_t;
    private JButton sauvegarde_b;
    private JButton miseajour_b;
    private JButton supprimer_b;
    private JButton recherche_b;
    private JTextField recherche_tf;
    private JScrollPane table_scrollpane;
    private JTextField nomens_tf;
    private JTextField prenomens_tf;
    private JTextField specens_tf;
    private JTable enseignants_t;
    private JButton ajouteens_b;
    private JButton modifierens_b;
    private JButton supprimerens_b;
    private JButton rechercheens_b;
    private JTextField recherchens_tf;
    private JLabel nomens_l;
    private JLabel prenomens_l;
    private JLabel specens_l;
    private JScrollPane scrollpantens;
    private JPanel modbuttons_p;
    private JTextField titrelivre_tf;
    private JTextField anneelivre_tf;
    private JTextField auteurlivre_tf;
    private JTextField cotelivre_tf;
    private JLabel glivre_l;
    private JTable livres_t;
    private JButton ajouterPdfButton;
    private JButton ajouterlivre_b;
    private JButton supprimerlivre_b;
    private JButton modifierButton;
    private JButton recherchLivre_b;
    private JTextField recherchlivre_tf;
    private JLabel titrelivre_l;
    private JLabel anneelivre_l;
    private JLabel auterlivre_l;
    private JLabel cotelivre_l;
    private JButton ajouterPDFButton;

    private  JFileChooser pdfchooser;

    private Path thepath;

    AdminPage1() {


        connection = Methodes.connect("gestionbibliotheque");
        Methodes.fillTable("memoire",connection, memoires_t);
        setTitle("Gestion des donnes ");

        setContentPane(main_p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        memoires_t.setDefaultEditor(Object.class, null);
        enseignants_t.setDefaultEditor(Object.class ,null);
        livres_t.setDefaultEditor(Object.class,null);

        // ##################### Gestion des memoires ########################################
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


        memoire_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) card_p.getLayout();
            cardlayout.show(card_p, "Card1");
        });


        ensignant_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) card_p.getLayout();
            cardlayout.show(card_p, "Card2");
        });


        livre_b.addActionListener(e -> {
            CardLayout cardlayout = (CardLayout) card_p.getLayout();
            cardlayout.show(card_p, "Card3");
        });


        sauvegarde_b.addActionListener(e -> Methodes.addMemoire(connection,
                memoires_t,
                etudiants_tf,
                titre_tf,
                annee_tf,
                cote_tf,
                resume_tf,
                enseignant_tf,
                thepath.toString()));


        recherche_b.addActionListener(e -> {
            String id = recherche_tf.getText();
            Methodes.searchMemoire(connection, memoires_t, id, recherche_tf.getText().isEmpty(), titre_tf, etudiants_tf, annee_tf, cote_tf, resume_tf, enseignant_tf);


        });
        miseajour_b.addActionListener(e -> {
            String idm;
            if (recherche_tf.getText().isEmpty()) {
                idm = id;
            } else idm = recherche_tf.getText();

            Methodes.updateMemoire(connection, memoires_t, idm, titre_tf, etudiants_tf, annee_tf, cote_tf, resume_tf, enseignant_tf,thepath.toString());


        });
        supprimer_b.addActionListener(e -> {
            String ids;
            if (recherche_tf.getText().isEmpty()) {
                ids = id;
            } else ids = recherche_tf.getText();
            Methodes.deleteMemoire(connection, memoires_t, ids, titre_tf, etudiants_tf, annee_tf, cote_tf, resume_tf, enseignant_tf);

        });
        // ##################### fin Gestion des memoires ########################################

        // ##################### Gestion des Enseignants ########################################
        Methodes.fillTable("enseignant",connection,enseignants_t);

        ajouteens_b.addActionListener(e -> Methodes.addEnseignant(connection,enseignants_t,nomens_tf,prenomens_tf,specens_tf));

        enseignants_t.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int i = enseignants_t.getSelectedRow();
                if (i != -1) {
                    int id_int = (int) enseignants_t.getValueAt(i, 0);
                    id_enseignant = String.valueOf(id_int);
                    System.out.println("the id of the eneseisifsi si = " + id_enseignant);

                }

                try {
                    pst = connection.prepareStatement("select nom,prenom,specialite from enseignant where id_enseignant = ? ");

                    pst.setString(1, id_enseignant);
                    ResultSet rs = pst.executeQuery();


                    if (rs.next()) {
                        nomens_tf.setText(rs.getString(1));
                        prenomens_tf.setText(rs.getString(2));
                        specens_tf.setText(rs.getString(3));

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }


        });


        modifierens_b.addActionListener(e -> {
            String id;
            if (recherchens_tf.getText().isEmpty()){
                id = id_enseignant;
            }else id = recherchens_tf.getText();
            Methodes.updateEnseignant(connection,enseignants_t,id,nomens_tf,prenomens_tf,specens_tf);

        });
        supprimerens_b.addActionListener(e -> Methodes.deleteEnseignant(connection,enseignants_t,id_enseignant,nomens_tf,prenomens_tf,specens_tf));
        rechercheens_b.addActionListener(e -> {
                String id = recherchens_tf.getText();
                Methodes.searchEnseignant(connection,enseignants_t,recherchens_tf.getText().isEmpty(),id,nomens_tf,prenomens_tf,specens_tf);
        });

        // ##################### fin Gestion des Enseignants ########################################

        // ##################### Gestion des livres ########################################
            Methodes.fillTable("livre",connection,livres_t);
        livres_t.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int i = livres_t.getSelectedRow();
                if (i != -1) {
                    int id_int = (int) livres_t.getValueAt(i, 0);
                    id_livre = String.valueOf(id_int);
                    System.out.println(id + "   this is the id ");
                }

                try {
                    pst = connection.prepareStatement("select titre,auteur,annee,cote from livre where id_livre = ? ");

                    pst.setString(1, id_livre);
                    ResultSet rs = pst.executeQuery();


                    if (rs.next()) {
                        titrelivre_tf.setText(rs.getString(1));
                        auteurlivre_tf.setText(rs.getString(2));
                        anneelivre_tf.setText(rs.getString(3));
                        cotelivre_tf.setText(rs.getString(4));


                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }


        });



        ajouterlivre_b.addActionListener(e -> {

            Methodes.addLivre(connection,livres_t,titrelivre_tf,auteurlivre_tf,anneelivre_tf,cotelivre_tf,thepath.toString());

        });
        modifierButton.addActionListener(e -> {
            String idl;
            if (recherchlivre_tf.getText().isEmpty()){
                idl = id_livre;
            }else idl =recherchlivre_tf.getText();
            Methodes.updateLivre(connection,livres_t,idl,titrelivre_tf,auteurlivre_tf,anneelivre_tf,cotelivre_tf,thepath.toString());

        });
        supprimerlivre_b.addActionListener(e -> {
            String idl;
            if (recherchlivre_tf.getText().isEmpty()){
                idl = id_livre;
            }else idl =recherchlivre_tf.getText();
            Methodes.deleteLivre(connection,livres_t,idl,titrelivre_tf,auteurlivre_tf,anneelivre_tf,cotelivre_tf);
        });
        recherchLivre_b.addActionListener(e -> {
            String idl =recherchlivre_tf.getText();
            Methodes.searchLivre(connection,livres_t,recherchlivre_tf.getText().isEmpty(),idl,titrelivre_tf,auteurlivre_tf,anneelivre_tf,cotelivre_tf);
        });
        ajouterPDFButton.addActionListener(e -> {
            thepath = Methodes.addpdfs("Memoires");
        });


        ajouterPdfButton.addActionListener(e -> {
            thepath = Methodes.addpdfs("Livres");
        });
    }



}
