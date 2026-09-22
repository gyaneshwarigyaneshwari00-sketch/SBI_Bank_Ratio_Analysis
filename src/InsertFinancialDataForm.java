import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class InsertFinancialDataForm extends JFrame {

    JTextField txtYear, txtProfit, txtAssets, txtEquity, txtCA, txtCL;

    public InsertFinancialDataForm() {

        setTitle("Insert Financial Data");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(240, 248, 255));
        setLayout(new GridLayout(10, 2, 10, 10));

        JLabel title = new JLabel("INSERT FINANCIAL DATA");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(30, 64, 175));

        add(title);
        add(new JLabel(""));

        add(new JLabel("Year"));
        txtYear = new JTextField();
        add(txtYear);

        add(new JLabel("Net Profit"));
        txtProfit = new JTextField();
        add(txtProfit);

        add(new JLabel("Total Assets"));
        txtAssets = new JTextField();
        add(txtAssets);

        add(new JLabel("Shareholder Equity"));
        txtEquity = new JTextField();
        add(txtEquity);

        add(new JLabel("Current Assets"));
        txtCA = new JTextField();
        add(txtCA);

        add(new JLabel("Current Liabilities"));
        txtCL = new JTextField();
        add(txtCL);

        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnPrint = new JButton("Print");
        JButton btnBack = new JButton("Back");

        add(btnSave);
        add(btnUpdate);

        add(btnDelete);
        add(btnPrint);

        add(btnBack);
        add(new JLabel(""));

        btnSave.addActionListener(e -> saveData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnPrint.addActionListener(e -> printData());

        btnBack.addActionListener(e -> {
            dispose();
            new DashboardForm();
        });

        setVisible(true);
    }

    void saveData() {
        try {
            Connection con = DBConnection.connect();

            String sql = "INSERT INTO financial_data VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtYear.getText()));
            ps.setDouble(2, Double.parseDouble(txtProfit.getText()));
            ps.setDouble(3, Double.parseDouble(txtAssets.getText()));
            ps.setDouble(4, Double.parseDouble(txtEquity.getText()));
            ps.setDouble(5, Double.parseDouble(txtCA.getText()));
            ps.setDouble(6, Double.parseDouble(txtCL.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Saved Successfully");
            clearFields();

            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void updateData() {
        try {
            Connection con = DBConnection.connect();

            String sql = "UPDATE financial_data SET net_profit=?, total_assets=?, shareholders_equity=?, current_assets=?, current_liabilities=? WHERE year=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, Double.parseDouble(txtProfit.getText()));
            ps.setDouble(2, Double.parseDouble(txtAssets.getText()));
            ps.setDouble(3, Double.parseDouble(txtEquity.getText()));
            ps.setDouble(4, Double.parseDouble(txtCA.getText()));
            ps.setDouble(5, Double.parseDouble(txtCL.getText()));
            ps.setInt(6, Integer.parseInt(txtYear.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Updated Successfully");
            clearFields();

            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void deleteData() {
        try {
            Connection con = DBConnection.connect();

            String sql = "DELETE FROM financial_data WHERE year=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtYear.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record Deleted");
            clearFields();

            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void printData() {
        try {
            Connection con = DBConnection.connect();

            String sql = "SELECT * FROM financial_data WHERE year=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtYear.getText()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JTextArea area = new JTextArea();

                area.append("SBI BANK FINANCIAL REPORT\n\n");
                area.append("Year : " + rs.getInt("year") + "\n");
                area.append("Net Profit : " + rs.getDouble("net_profit") + "\n");
                area.append("Total Assets : " + rs.getDouble("total_assets") + "\n");
                area.append("Shareholder Equity : " + rs.getDouble("shareholders_equity") + "\n");
                area.append("Current Assets : " + rs.getDouble("current_assets") + "\n");
                area.append("Current Liabilities : " + rs.getDouble("current_liabilities"));

                area.print();
            } else {
                JOptionPane.showMessageDialog(this, "Record Not Found");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void clearFields() {
        txtYear.setText("");
        txtProfit.setText("");
        txtAssets.setText("");
        txtEquity.setText("");
        txtCA.setText("");
        txtCL.setText("");
    }
}