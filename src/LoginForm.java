import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;

    public LoginForm() {

        setTitle("SBI Bank Ratio Analysis - Login");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(10, 10, 10, 10);
gbc.anchor = GridBagConstraints.WEST;
getContentPane().setBackground(new Color(240,248,255));

        // Username
gbc.gridx = 0;
gbc.gridy = 0;
JLabel lblUser = new JLabel("Username");
lblUser.setForeground(new Color(30,64,175));
lblUser.setFont(new Font("Arial", Font.BOLD, 13));
add(lblUser, gbc);

gbc.gridx = 1;
txtUser = new JTextField(15);
add(txtUser, gbc);

// Password
gbc.gridx = 0;
gbc.gridy = 1;
JLabel lblPass = new JLabel("Password");
lblPass.setForeground(new Color(30,64,175));
lblPass.setFont(new Font("Arial", Font.BOLD, 13));
add(lblPass, gbc);

gbc.gridx = 1;
txtPass = new JPasswordField(15);
add(txtPass, gbc);

// Login Button
gbc.gridx = 1;
gbc.gridy = 2;
btnLogin = new JButton("Login");
btnLogin.setBackground(new Color(30,64,175));
btnLogin.setForeground(Color.WHITE);
btnLogin.setFont(new Font("Arial", Font.BOLD, 13));
add(btnLogin, gbc);

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    Connection con = DBConnection.connect();

                    String sql = "SELECT * FROM users WHERE username=? AND password=?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, txtUser.getText());
                    ps.setString(2, String.valueOf(txtPass.getPassword()));

                    ResultSet rs = ps.executeQuery();

                    if(rs.next()){

                        JOptionPane.showMessageDialog(null,"Login Successful");

                        dispose();

                        new DashboardForm();

                    }else{

                        JOptionPane.showMessageDialog(null,"Invalid Username or Password");

                    }

                }catch(Exception ex){

                    JOptionPane.showMessageDialog(null,ex.getMessage());

                }

            }

        });

        setVisible(true);

    }

    public static void main(String[] args) {

        new LoginForm();

    }

}