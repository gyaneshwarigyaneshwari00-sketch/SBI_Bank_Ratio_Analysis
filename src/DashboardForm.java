import javax.swing.*;
import java.awt.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {

        setTitle("SBI Bank Ratio Analysis System");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("SBI BANK RATIO ANALYSIS SYSTEM");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,18));

        JButton btnInsert = new JButton("Insert Financial Data");
        JButton btnView = new JButton("View Financial Data");
        JButton btnRatio = new JButton("Calculate Ratios");
        JButton btnExit = new JButton("Exit");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10,10));

        panel.add(btnInsert);
        panel.add(btnView);
        panel.add(btnRatio);
        panel.add(btnExit);

        add(title,BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        btnInsert.addActionListener(e -> {
    dispose();
    new InsertFinancialDataForm();
});
btnView.addActionListener(e -> {
    dispose();
    new ViewFinancialDataForm();
});
btnRatio.addActionListener(e -> {
    dispose();
    new RatioCalculatorForm();
});

btnExit.addActionListener(e -> {

    JOptionPane.showMessageDialog(
        this,
        "Thank you for using\nSBI Bank Ratio Analysis System!",
        "Thank You",
        JOptionPane.INFORMATION_MESSAGE
    );

    System.exit(0);

});

        setVisible(true);
    }
}