import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import javax.swing.border.LineBorder;

public class LoginPage extends JFrame implements ActionListener, ItemListener {
    HashMap<String, String> login;
    JButton log_b = new JButton("Se connecter");
    JButton cancel_b = new JButton("Annuler");
    JTextField userid_t = new JTextField();
    JPasswordField pass_t = new JPasswordField();
    JToggleButton showPasswordButton = new JToggleButton("Voir");

    JLabel title_l1 = new JLabel("CONNEXION ADMINISTRATEUR");
    JLabel title_l2 = new JLabel("Veuillez vous connecter");
    JSeparator separator = new JSeparator();
    JLabel userid_l = new JLabel("Nom d'utilisateur");
    JLabel pass_l = new JLabel("Mot de passe");
    JLabel message_l = new JLabel();

    LoginPage(HashMap<String, String> login) {
        this.login = login;

        Font titleFont = new Font("Montserrat", Font.BOLD, 18);
        title_l1.setFont(titleFont);
        title_l1.setForeground(new Color(0xFF1036B6));
        title_l2.setFont(new Font("Montserrat", Font.BOLD, 13));

        title_l1.setBounds(55, 20, 300, 30);
        title_l2.setBounds(110, 55, 200, 20);

        separator.setBounds(0, 50, 550, 50);
        separator.setForeground(Color.BLACK);

        log_b.setBounds(140, 230, 120, 30);
        log_b.setFont(new Font("Montserrat", Font.BOLD, 11));
        log_b.setForeground(Color.WHITE);
        log_b.setBackground(new Color(0x2ECC71)); // Vert
        log_b.setBorderPainted(false);
        log_b.setFocusPainted(false);
        log_b.addActionListener(e -> {
            String id = userid_t.getText();
            String password = String.valueOf(pass_t.getPassword());

            if (login.containsKey(id)) {
                if (login.get(id).equals(password)) {
                    new AdminPage();
                    dispose();
                } else {
                    message_l.setText("Mot de passe incorrect !");
                }
            } else {
                message_l.setText("Nom d'utilisateur non trouvé !");
            }
        });

        cancel_b.setBounds(270, 230, 80, 30);
        cancel_b.setFont(new Font("Montserrat", Font.PLAIN, 11));
        cancel_b.setForeground(Color.BLACK);
        cancel_b.setBackground(Color.WHITE);
        cancel_b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cancel_b.addActionListener(e -> dispose());

        userid_l.setBounds(50, 100, 100, 25);
        userid_l.setFont(new Font("Montserrat", Font.PLAIN, 10));

        pass_l.setBounds(50, 150, 100, 25);
        pass_l.setFont(new Font("Montserrat", Font.PLAIN, 10));


        userid_t.setBounds(50, 120, 300, 25);
        pass_t.setBounds(50, 170, 245, 25);

        // Bouton pour afficher/masquer le mot de passe
        showPasswordButton.setBounds(300, 170, 55, 25);
        showPasswordButton.setFont(new Font("Montserrat", Font.BOLD, 10));
        showPasswordButton.addItemListener(this);

        message_l.setFont(new Font(null, Font.BOLD, 12));
        message_l.setForeground(Color.RED);
        message_l.setBounds(170, 190, 250, 25);

        add(title_l1);
        add(title_l2);
        add(separator);
        add(message_l);
        add(userid_l);
        add(userid_t);
        add(pass_l);
        add(pass_t);
        add(showPasswordButton);
        add(log_b);
        add(cancel_b);

        // Centrage du contenu dans panel1
        title_l1.setHorizontalAlignment(SwingConstants.CENTER);
        title_l2.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(420, 320);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Afficher le mot de passe
            showPasswordButton.setText("Msq");
            pass_t.setEchoChar((char) 0);
        } else {
            // Masquer le mot de passe
            showPasswordButton.setText("Voir");

            pass_t.setEchoChar('⚫');
        }
    }
}
