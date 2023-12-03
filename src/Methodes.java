import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;

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


            connection = DriverManager.getConnection("jdbc:mysql://localhost/gestionbibliotheque", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("successe");

        String url = "jdbc:mysql://localhost:3306/gestionbibliotheque";

        String username = "root";

        String password = "";

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
    }

    public static void updateMemoire(Connection connection, JTable table, String id, JTextField titre, JTextField etudiant, JTextField annee, JTextField cote, JTextField resume, JTextField id_ens) {


        String dirct = "this is a temporary path";
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
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


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

    public static void addMemoire(Connection connection, JTable table, JTextField titre, JTextField etudiant, JTextField annee, JTextField cote, JTextField resume, JTextField id_ens) {

        String dirct = "this is a temporary path";
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
            JOptionPane.showMessageDialog(null, "Enseignant ajouter avec succes");
            name.setText("");
            surname.setText("");
            specialty.setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void addLivre(Connection connection, JTable table, JTextField titre, JTextField auteur, JTextField annee, JTextField cote) {
        try {
            PreparedStatement pst = connection.prepareStatement("insert into livre(titre,auteur,annee ,cote)values(?,?,?,?)");
            pst.setString(1, titre.getText());
            pst.setString(2, auteur.getText());
            pst.setString(3, annee.getText());
            pst.setString(4, cote.getText());

            pst.executeUpdate();
            titre.setText("");
            auteur.setText("");
            annee.setText("");
            cote.setText("");
            titre.requestFocus();
            fillTable("livre", connection, table);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLivre(Connection connection, JTable table, String id, JTextField titre, JTextField auteur, JTextField annee, JTextField cote) {
        try {
        PreparedStatement pst = connection.prepareStatement("update livre set titre = ? , auteur = ? , annee = ? ,cote = ? where id_livre = ?");
        pst.setString(1, titre.getText());
        pst.setString(2, auteur.getText());
        pst.setString(3, annee.getText());
        pst.setString(4, annee.getText());
        pst.setString(5, id);
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
    }
}
