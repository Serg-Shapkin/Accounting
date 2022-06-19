import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyReportsCalculator {
    SimpleFileReader simpleFileReader = new SimpleFileReader();
    List<MonthlyReport> monthlyReports = simpleFileReader.countAllMonthlyExpenses();

    public void informationMonthlyReports () {
        int maxSum = 0;
        String nameMaxSum = null;
        int maxExpense = 0;
        String nameMaxExpense = null;
        int monthNumber = 0;
        Map<Integer,String> listMonth = new HashMap();       // название месяца
        listMonth.put(1,"Январь");
        listMonth.put(2,"Февраль");
        listMonth.put(3,"Март");

        for (int i = 0; i < monthlyReports.size(); i++) {
            MonthlyReport list = monthlyReports.get(i);
            monthNumber++;
            for (int j = 0; j < list.monthlyReportRecords.size(); j++) {
                String itemName = monthlyReports.get(i).monthlyReportRecords.get(j).getItemName();
                boolean isExpense = monthlyReports.get(i).monthlyReportRecords.get(j).getIsExpense();
                int quantity = monthlyReports.get(i).monthlyReportRecords.get(j).getQuantity();
                int sumOfOne = monthlyReports.get(i).monthlyReportRecords.get(j).getSumOfOne();

                if (!isExpense) {
                    if (quantity * sumOfOne > maxSum) {
                        maxSum = quantity * sumOfOne;
                        nameMaxSum = itemName;
                    }
                } else {
                    if (quantity * sumOfOne > maxExpense) {
                        maxExpense = quantity * sumOfOne;
                        nameMaxExpense = itemName;
                    }
                }
            }
            System.out.println("Самый прибыльный товар: " + nameMaxSum + " - " + maxSum);
            maxSum = 0;
            System.out.println("Самая большая трата: " + nameMaxExpense + " - " + maxExpense);
            maxExpense = 0;
        }
    }
}
