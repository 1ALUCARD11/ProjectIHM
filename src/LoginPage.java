import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage extends JFrame implements ActionListener {
    HashMap<String ,String> login;
    JButton log_b = new JButton(" Login ");
    JTextField userid_t = new JTextField();
    JPasswordField pass_t = new JPasswordField();

    JLabel userid_l = new JLabel("USER ID :");
    JLabel pass_l = new JLabel ("PASSWORD :");
    JLabel message_l = new JLabel();
    LoginPage(HashMap<String,String> login){
        this.login = login;
        userid_l.setBounds(50,100,75,25);
        pass_l.setBounds(50,150,100,25);
        userid_t.setBounds(150,100,100,25);
        pass_t.setBounds(150,150,100,25);
        log_b.setBounds(160,225,75,30);
        getRootPane().setDefaultButton(log_b);

        log_b.addActionListener(e -> {
            String id = userid_t.getText();
            String password = String.valueOf(pass_t.getPassword());

            if(login.containsKey(id)){
                if (login.get(id).equals(password)){

                   // message_l.setText("");
                    new AdminPage();
                    dispose();
                }
                else {
                    message_l.setText("Incorrect Password!!");
                }
            }
            else {
                message_l.setText("User Name Not Found !!");
            }
        });

        message_l.setFont(new Font(null,Font.BOLD,12));
        message_l.setBounds(160,180,150,25);
        add(message_l);
        add(userid_l);
        add(userid_t);
        add(pass_l);
        add(pass_t);
        add(log_b);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(420,420);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
