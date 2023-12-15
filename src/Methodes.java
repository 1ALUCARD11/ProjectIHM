import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Methodes {
    public static Connection connect(String dbname) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dburl = "jdbc:mysql://localhost/" + dbname;

            connection = DriverManager.getConnection(dburl, "root", "");
        } catch (
                SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("successe");
        return connection;
    }

    public static void connect2() {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            connection = DriverManager.getConnection("jdbc:mysql://localhost/gestionbibliotheque", "root", "1234");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("successe");

        String url = "jdbc:mysql://localhost:3306/gestionbibliotheque";

        String username = "root";

        String password = "1234";

        try {


            Class.forName("com.mysql.cj.jdbc.Driver");


            connection = null;

            connection = DriverManager.getConnection(url, username, password);

            Statement statement = null;

            statement = connection.createStatement();


            ResultSet resultSet = null;

            resultSet = statement.executeQuery("select * from Enseignant");


            // while (resultSet.next()) {


            //   System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));


            // }


            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void fillTable(String tablename, Connection connection, JTable table) {
        String tmp = "select * from " + tablename;
        try {
            PreparedStatement pst = connection.prepareStatement(tmp);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static void fillmemoiretable( Connection connection, JTable table) {
        String tmp = "select m.titre as Titre,m.etudiants as 'Etudiants',m.annee 'Annee' ,m.cote as 'Cote',m.resume as 'Resume',e.nom as 'Enseignant' from memoire m, enseignant e where e.id_enseignant=m.id_enseignant ";
        try {
            PreparedStatement pst = connection.prepareStatement(tmp);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void fillensetable( Connection connection, JTable table) {
        String tmp = "select nom as 'Nom',prenom as 'Prenom',specialite as 'Specialite' from enseignant ";
        try {
            PreparedStatement pst = connection.prepareStatement(tmp);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void filllivreetable( Connection connection, JTable table) {
        String tmp = "select titre as 'Titre',auteur as 'Auter',annee as 'Annee',cote 'LaCote' from livre ";
        try {
            PreparedStatement pst = connection.prepareStatement(tmp);
            ResultSet rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public static void addMemoire(Connection connection, JTable table, JTextField titre, JTextField etudiant, JTextField annee, JTextField cote, JTextField resume, JTextField id_ens,String dirct) {

        try {
            PreparedStatement pst = connection.prepareStatement("insert into memoire(titre,etudiants,annee,cote,resume,id_enseignant,directoire)values(?,?,?,?,?,?,?)");
            pst.setString(1, titre.getText());
            pst.setString(2, etudiant.getText());
            pst.setString(3, annee.getText());
            pst.setString(4, cote.getText());
            pst.setString(5, resume.getText());
            pst.setString(6, id_ens.getText());
            pst.setString(7, dirct);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Informations added successfuly !!");
            etudiant.setText("");
            titre.setText("");
            cote.setText("");
            annee.setText("");
            resume.setText("");
            id_ens.setText("");
            titre.requestFocus();
            fillTable("memoire", connection, table);
            fillmemoiretable(connection,MainPanel.memoiresTable);


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }


    public static void deleteMemoire(Connection connection, JTable table, String id, JTextField titre, JTextField etudiant, JTextField annee, JTextField cote, JTextField resume, JTextField id_ens) {

        try {
            PreparedStatement pst = connection.prepareStatement("delete from memoire where id_memoire = ?");
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Memoire Deleted !!");
            titre.setText("");
            etudiant.setText("");
            annee.setText("");
            cote.setText("");
            resume.setText("");
            id_ens.setText("");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        fillTable("memoire", connection, table);
        fillmemoiretable(connection,MainPanel.memoiresTable);
    }



    public static void searchMemoire(Connection connection, JTable table, String id, Boolean tf, JTextField titre, JTextField etudiant, JTextField annee, JTextField cote, JTextField resume, JTextField id_ens) {
        try {


            PreparedStatement pst = connection.prepareStatement("select titre,etudiants,annee ,cote,resume,id_enseignant from memoire where id_memoire = ?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            pst = connection.prepareStatement("select * from memoire where id_memoire = ?");
            pst.setString(1, id);
            ResultSet rs2 = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs2));


            if (rs.next()) {


                titre.setText(rs.getString(1));
                etudiant.setText(rs.getString(2));
                annee.setText(rs.getString(3));
                cote.setText(rs.getString(4));
                resume.setText(rs.getString(5));
                id_ens.setText(rs.getString(6));


            } else {
                titre.setText("");
                etudiant.setText("");
                annee.setText("");
                cote.setText("");
                resume.setText("");
                id_ens.setText("");
                fillTable("memoire", connection, table);
                if (!tf) {
                    JOptionPane.showMessageDialog(null, "Memoire ID incorrect");
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void updateMemoire(Connection connection, JTable table, String id, JTextField titre, JTextField etudiant, JTextField annee, JTextField cote, JTextField resume, JTextField id_ens,String dirct) {



        try {
            PreparedStatement pst = connection.prepareStatement("update memoire set titre = ?,etudiants = ?,annee = ?,cote = ?,resume = ?,id_enseignant = ?,directoire = ? where id_memoire = ?");
            pst.setString(1, titre.getText());
            pst.setString(2, etudiant.getText());
            pst.setString(3, cote.getText());
            pst.setString(4, annee.getText());
            pst.setString(5, resume.getText());
            pst.setString(6, id_ens.getText());
            pst.setString(7, dirct);
            pst.setString(8, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Informations Updated successfuly !!");
            etudiant.setText("");
            titre.setText("");
            cote.setText("");
            annee.setText("");
            resume.setText("");
            id_ens.setText("");
            titre.requestFocus();
            fillTable("memoire", connection, table);
            fillmemoiretable(connection,MainPanel.memoiresTable);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }



    public static void addEnseignant(Connection connection, JTable table, JTextField name, JTextField surname, JTextField specialty) {
        try {
            PreparedStatement pst = connection.prepareStatement("insert into enseignant(nom,prenom,specialite)values(?,?,?)");
            pst.setString(1, name.getText());
            pst.setString(2, surname.getText());
            pst.setString(3, specialty.getText());

            pst.executeUpdate();

            fillTable("enseignant", connection, table);
            fillensetable(connection,MainPanel.enseignantTable);
            JOptionPane.showMessageDialog(null, "Enseignant ajouter avec succes");
            name.setText("");
            surname.setText("");
            specialty.setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEnseignant(Connection connection, JTable table, String id, JTextField name, JTextField surname, JTextField specialty) {
        try {
            PreparedStatement pst = connection.prepareStatement("delete from enseignant where id_enseignant = ?");
            pst.setString(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Enseignant supprimer avec succes");
            name.setText("");
            surname.setText("");
            specialty.setText("");
            name.requestFocus();
            fillTable("enseignant", connection, table);
            fillensetable(connection,MainPanel.enseignantTable);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Enseignant exist dans une memoire ","error",JOptionPane.ERROR_MESSAGE);

            throw new RuntimeException(e);
        }
    }


    public static void searchEnseignant(Connection connection, JTable table, Boolean stf, String id, JTextField name, JTextField surname, JTextField specialty) {
        try {


            PreparedStatement pst = connection.prepareStatement("select nom,prenom,specialite from enseignant where id_enseignant = ?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            pst = connection.prepareStatement("select * from enseignant where id_enseignant = ?");
            pst.setString(1, id);
            ResultSet rs2 = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs2));


            if (rs.next()) {


                name.setText(rs.getString(1));
                surname.setText(rs.getString(2));
                specialty.setText(rs.getString(3));



            } else {
                name.setText("");
                surname.setText("");
                specialty.setText("");

                fillTable("enseignant", connection, table);
                if (!stf) {
                    JOptionPane.showMessageDialog(null, "Memoire ID incorrect");
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void updateEnseignant(Connection connection, JTable table, String id, JTextField name, JTextField surname, JTextField specialty) {
        try {
            PreparedStatement pst = connection.prepareStatement("update enseignant set nom = ? ,prenom = ? ,specialite = ? where id_enseignant = ? ");
            pst.setString(1, name.getText());
            pst.setString(2, surname.getText());
            pst.setString(3, specialty.getText());
            pst.setString(4, id);
            if (name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "champ nom est vide!!");
                return;

            } else if (surname.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "champ prenome est vide!!");
                return;
            } else if (specialty.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "champ specialite est vide!!");
                return;
            }
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Enseignant modifer avec succes");
            name.setText("");
            surname.setText("");
            specialty.setText("");
            name.requestFocus();
            fillTable("enseignant", connection, table);
            fillensetable(connection,MainPanel.enseignantTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public static void addLivre(Connection connection, JTable table, JTextField titre, JTextField auteur, JTextField annee, JTextField cote ,String dirct) {
        try {
            PreparedStatement pst = connection.prepareStatement("insert into livre(titre,auteur,annee ,cote,emplacement)values(?,?,?,?,?)");
            pst.setString(1, titre.getText());
            pst.setString(2, auteur.getText());
            pst.setString(3, annee.getText());
            pst.setString(4, cote.getText());
            pst.setString(5, dirct);

            pst.executeUpdate();
            titre.setText("");
            auteur.setText("");
            annee.setText("");
            cote.setText("");
            titre.requestFocus();
            fillTable("livre", connection, table);
            filllivreetable(connection,MainPanel.livresTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLivre(Connection connection, JTable table, String id, JTextField titre, JTextField auteur, JTextField annee, JTextField cote,String dirct) {
        try {
        PreparedStatement pst = connection.prepareStatement("update livre set titre = ? , auteur = ? , annee = ? ,cote = ? ,emplacement =? where id_livre = ?");
        pst.setString(1, titre.getText());
        pst.setString(2, auteur.getText());
        pst.setString(3, annee.getText());
        pst.setString(4, annee.getText());
        pst.setString(5,dirct);
        pst.setString(6, id);
            if (titre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Champ titre est vide!!");
                return;

            } else if (auteur.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Champ auteur est vide!!");
                return;
            } else if (cote.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Champ cote est vide!!");
                return;
            }


            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        titre.setText("");
        auteur.setText("");
        annee.setText("");
        cote.setText("");
        titre.requestFocus();
        fillTable("livre", connection, table);
        filllivreetable(connection,MainPanel.livresTable);

    }

    public static void searchLivre(Connection connection, JTable table, Boolean stf, String id,JTextField titre,JTextField auteur, JTextField annee, JTextField cote ){

        try {


            PreparedStatement pst = connection.prepareStatement("select titre,auteur,annee,cote from livre where id_livre = ?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            pst = connection.prepareStatement("select * from livre where id_livre = ?");
            pst.setString(1, id);
            ResultSet rs2 = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs2));


            if (rs.next()) {


                titre.setText(rs.getString(1));
                auteur.setText(rs.getString(2));
                annee.setText(rs.getString(3));
                cote.setText(rs.getString(4));


            } else {
                titre.setText("");
                auteur.setText("");
                annee.setText("");
                cote.setText("");

                fillTable("livre", connection, table);
                if (!stf) {
                    JOptionPane.showMessageDialog(null, "livre ID incorrect");
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void deleteLivre(Connection connection ,JTable table ,String id ,JTextField titre, JTextField auteur, JTextField annee , JTextField cote){
        try {
        PreparedStatement pst = connection.prepareStatement("delete from livre where id_livre = ?");
        pst.setString(1,id);

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JOptionPane.showMessageDialog(null, "livre supprimer avec succes");
        titre.setText("");
        auteur.setText("");
        cote.setText("");
        annee.setText("");
        titre.requestFocus();
        fillTable("livre", connection, table);
        filllivreetable(connection,MainPanel.livresTable);

    }

    public static List<JTextField> extractTextFields(JPanel container) {
        ArrayList<JTextField> textFields = new ArrayList<>();
        List<String> h = new ArrayList<String>();
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                textFields.add( (JTextField) component);
            }
        }

        return textFields;
    }

    public static Path addpdfs(String folder ,String filename){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter pdf=new FileNameExtensionFilter("*pdf","pdf");
        fileChooser.addChoosableFileFilter(pdf);
        fileChooser.setFileFilter(pdf);

        String pth = "";
        Path destinationpath = Path.of("");
        int respons =  fileChooser.showOpenDialog(null);
        if (respons == JFileChooser.APPROVE_OPTION){
            File file = new File (fileChooser.getSelectedFile().getAbsolutePath());
            pth = fileChooser.getSelectedFile().getAbsolutePath();

            System.out.println(pth);
            Path surcpath = Paths.get(pth);
            Path destpath = Paths.get("src/"+folder+"/");

            try{
                // Files.createDirectories(destpath);
                System.out.printf(String.valueOf(surcpath.getFileName())+"hello ");

                destinationpath = destpath.resolve(filename+".pdf");

                Files.copy(surcpath,destinationpath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File copied successfuly to :" +destinationpath);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


        return destinationpath;
    }

    public static void rechercheCritere(int currentCard,String critere,String texte,JTable table) throws SQLException{
        Connection connection=connect("gestionbibliotheque");
        ResultSet rs;
        PreparedStatement ps;
            switch (currentCard) {
                case 1:
                    ps = connection.prepareStatement("select m.titre as Titre,m.etudiants as 'Etudiants',m.annee 'Annee' ,m.cote as 'Cote',m.resume as 'Resume',e.nom as 'Enseignant' from memoire m, enseignant e where e.id_enseignant=m.id_enseignant AND "+critere+" LIKE '%"+texte+"%'");
                    break;
                case 2:
                    ps = connection.prepareStatement("select nom as 'Nom',prenom as 'Prenom',specialite as 'Specialite' from enseignant WHERE "+critere+" LIKE '%"+texte+"%'");
                    break;
                default:
                    ps = connection.prepareStatement("select titre as 'Titre',auteur as 'Auteur',annee as 'Annee',cote as 'Cote' from livre WHERE "+critere+" LIKE '%"+texte+"%'");
                    break;


            }
            /*ps.setString(1, critere);
            ps.setString(2, "%" + texte + "%");*/

            rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            JOptionPane.showMessageDialog(null, "Aucun résultat correspondant a votre Recherche", "Résultat Recherche", JOptionPane.WARNING_MESSAGE);

        }
        else{
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
    }

    public static void affichagePDF(String t,int current) throws SQLException, IOException {
        Connection connection=connect("gestionbibliotheque");
        PreparedStatement ps;
        if (current == 0) {
            ps= connection.prepareStatement("SELECT emplacement FROM livre where titre = ?");
        } else {
            ps= connection.prepareStatement("SELECT directoire FROM memoire where titre = ?");
        }
        ps.setString(1,t);
        ResultSet rs= ps.executeQuery();
        rs.next();
        String path= rs.getString(1);
        Desktop desktop=Desktop.getDesktop();
        File file=new File(path);
        desktop.open(file);

    }
}
