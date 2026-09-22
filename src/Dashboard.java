import java.util.Scanner;

public class Dashboard {

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== SBI BANK RATIO ANALYSIS =====");
            System.out.println("1. Insert Financial Data");
            System.out.println("2. View Financial Data");
            System.out.println("3. Update Financial Data");
            System.out.println("4. Delete Financial Data");
            System.out.println("5. Print Financial Data");
            System.out.println("6. Calculate Ratios");
            System.out.println("7. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    InsertFinancialData.insertData();
                    break;

                case 2:
                    ViewFinancialData.view();
                    break;

                case 3:
                    UpdateFinancialData.updateData();
                    break;

                case 4:
                    DeleteFinancialData.deleteData();
                    break;

                case 5:
                    PrintFinancialData.printData();
                    break;

                case 6:
                    RatioCalculator.calculate();
                    break;

                case 7:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);

        sc.close();
    }

    public static void main(String[] args) {
        showMenu();
    }
}