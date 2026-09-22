import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RatioCalculator {

    public static double[] calculate(int year) {

        try {
            Connection con = DBConnection.connect();

            String sql = "SELECT * FROM financial_data WHERE year=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, year);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                double profit = rs.getDouble("net_profit");
                double assets = rs.getDouble("total_assets");
                double equity = rs.getDouble("shareholders_equity");
                double currentAssets = rs.getDouble("current_assets");
                double currentLiabilities = rs.getDouble("current_liabilities");

                double roa = (profit / assets) * 100;
                double roe = (profit / equity) * 100;
                double currentRatio = currentAssets / currentLiabilities;

                rs.close();
                ps.close();
                con.close();

                return new double[]{roa, roe, currentRatio};
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}