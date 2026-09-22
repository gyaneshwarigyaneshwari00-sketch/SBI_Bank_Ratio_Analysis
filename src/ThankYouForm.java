import javax.swing.*;
import java.awt.*;

public class ThankYouForm extends JFrame {

    public ThankYouForm() {

        setTitle("Thank You");
        setSize(420, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(230, 242, 255));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("THANK YOU!", JLabel.CENTER);
        title.setOpaque(true);
        title.setBackground(new Color(30, 64, 175));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel message = new JLabel(
            "<html><center>Thank you for using<br><b>SBI Bank Ratio Analysis System</b></center></html>",
            JLabel.CENTER
        );
        message.setFont(new Font("Arial", Font.PLAIN, 16));
        message.setForeground(new Color(30, 64, 175));

        JButton close = new JButton("Close");
        close.setBackground(new Color(22, 163, 74));
        close.setForeground(Color.WHITE);
        close.setFont(new Font("Arial", Font.BOLD, 14));

        close.addActionListener(e -> System.exit(0));

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(230, 242, 255));
        bottom.add(close);

        add(title, BorderLayout.NORTH);
        add(message, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}