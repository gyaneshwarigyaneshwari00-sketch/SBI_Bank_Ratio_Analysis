import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RatioCalculatorForm extends JFrame {

    JTextArea area;

    public RatioCalculatorForm() {

        setTitle("Financial Ratio Calculator");
        setSize(550, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background colour
        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(new BorderLayout());

        // ===== Title =====
        JLabel title = new JLabel("FINANCIAL RATIO ANALYSIS");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(30, 64, 175));

        add(title, BorderLayout.NORTH);

        // ===== Text Area =====
        area = new JTextArea();
        area.setEditable(false);
        area.setBackground(Color.WHITE);
        area.setForeground(new Color(17, 24, 39));
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        calculateRatio();

        add(new JScrollPane(area), BorderLayout.CENTER);

        // ===== Back Button =====
        JButton back = new JButton("Back");
        back.setBackground(new Color(30, 64, 175));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 12));

        back.addActionListener(e -> {
            dispose();
            new DashboardForm();
        });

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(240, 248, 255));
        bottom.add(back);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    void calculateRatio() {

        try {

            Connection con = DBConnection.connect();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM financial_data");

            while (rs.next()) {

                int year = rs.getInt("year");

                double profit = rs.getDouble("net_profit");
                double assets = rs.getDouble("total_assets");
                double equity = rs.getDouble("shareholders_equity");
                double ca = rs.getDouble("current_assets");
                double cl = rs.getDouble("current_liabilities");

                double roa = (profit / assets) * 100;
                double roe = (profit / equity) * 100;
                double current = ca / cl;

                area.append("====================================\n");
                area.append("YEAR            : " + year + "\n");
                area.append("ROA             : " + String.format("%.2f", roa) + "%\n");
                area.append("ROE             : " + String.format("%.2f", roe) + "%\n");
                area.append("CURRENT RATIO   : " + String.format("%.2f", current) + "\n");
                area.append("====================================\n\n");
            }

        } catch (Exception e) {

            area.setText(e.getMessage());

        }
    }
}