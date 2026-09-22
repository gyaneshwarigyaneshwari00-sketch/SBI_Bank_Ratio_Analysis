import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewFinancialDataForm extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewFinancialDataForm() {

        setTitle("View Financial Data");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("Year");
        model.addColumn("Net Profit");
        model.addColumn("Total Assets");
        model.addColumn("Equity");
        model.addColumn("Current Assets");
        model.addColumn("Current Liabilities");

        table = new JTable(model);

        loadData();

        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton print = new JButton("Print");
        JButton back = new JButton("Back");

        JPanel panel = new JPanel();

        panel.add(update);
        panel.add(delete);
        panel.add(print);
        panel.add(back);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Back
        back.addActionListener(e -> {
            dispose();
            new DashboardForm();
        });

        // Delete
        delete.addActionListener(e -> deleteData());

        // Update
        update.addActionListener(e -> updateData());

        // Print
        print.addActionListener(e -> printData());

        setVisible(true);
    }

    // Load MySQL Data
    void loadData() {

        try {

            model.setRowCount(0);

            Connection con = DBConnection.connect();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM financial_data");

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("year"),
                        rs.getDouble("net_profit"),
                        rs.getDouble("total_assets"),
                        rs.getDouble("shareholders_equity"),
                        rs.getDouble("current_assets"),
                        rs.getDouble("current_liabilities")
                });

            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Delete Selected Row
    void deleteData() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }

        try {

            int year = (int) model.getValueAt(row, 0);

            Connection con = DBConnection.connect();

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM financial_data WHERE year=?");

            ps.setInt(1, year);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record Deleted");

            loadData();

            ps.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Update Selected Row
    void updateData() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }

        try {

            Connection con = DBConnection.connect();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE financial_data SET net_profit=?, total_assets=?, shareholders_equity=?, current_assets=?, current_liabilities=? WHERE year=?");

            ps.setDouble(1, Double.parseDouble(model.getValueAt(row,1).toString()));
            ps.setDouble(2, Double.parseDouble(model.getValueAt(row,2).toString()));
            ps.setDouble(3, Double.parseDouble(model.getValueAt(row,3).toString()));
            ps.setDouble(4, Double.parseDouble(model.getValueAt(row,4).toString()));
            ps.setDouble(5, Double.parseDouble(model.getValueAt(row,5).toString()));
            ps.setInt(6, Integer.parseInt(model.getValueAt(row,0).toString()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Updated");

            ps.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // Print Selected Row
    void printData() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }

        try {

            JTextArea area = new JTextArea();

            area.append("SBI BANK FINANCIAL REPORT\n\n");

            area.append("Year : " + model.getValueAt(row,0) + "\n");
            area.append("Net Profit : " + model.getValueAt(row,1) + "\n");
            area.append("Total Assets : " + model.getValueAt(row,2) + "\n");
            area.append("Equity : " + model.getValueAt(row,3) + "\n");
            area.append("Current Assets : " + model.getValueAt(row,4) + "\n");
            area.append("Current Liabilities : " + model.getValueAt(row,5));

            area.print();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Print Failed");
        }
    }
}