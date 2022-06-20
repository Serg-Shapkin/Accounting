import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Verification {
    // вроде бы так, уверенности практически нет =)
    SimpleFileReader simpleFileReader = new SimpleFileReader();
    List<MonthlyReport> monthlyReports = simpleFileReader.countAllMonthlyExpenses();
    YearlyReport yearlyReport = simpleFileReader.readAnnualReport();

    public void verificationReports() {
        // для отчетов за каждый месяц
        List<Integer> listExpensesMonth = new ArrayList<>();   // список расходов в годовом отчете
        List<Integer> listIncomeMonth = new ArrayList<>();
        int sumExpensesMonth = 0; // суммы расходов по месяцам
        int sumIncomeMonth = 0; // суммы доходов по месяцам

        // для годового отчета
        List<Integer> listExpensesYear = new ArrayList<>();   // список расходов в годовом отчете
        List<Integer> listIncomeYear = new ArrayList<>();     // список доходов в годовом отчете
        Map<Integer,String> listMonth = new HashMap();       // название месяца

        for (int i = 0; i < monthlyReports.size(); i++) {
            MonthlyReport report = monthlyReports.get(i);
            for (int j = 0; j < report.monthlyReportRecords.size(); j++) {
                String itemName = report.monthlyReportRecords.get(j).getItemName();
                boolean isExpense = report.monthlyReportRecords.get(j).getIsExpense();
                int quantity = report.monthlyReportRecords.get(j).getQuantity();
                int sumOfOne = report.monthlyReportRecords.get(j).getSumOfOne();

                if (isExpense) {
                    sumExpensesMonth += quantity * sumOfOne;
                } else {
                    sumIncomeMonth += quantity * sumOfOne;
                }
            }
            listExpensesMonth.add(sumExpensesMonth);
            listIncomeMonth.add(sumIncomeMonth);
            sumExpensesMonth = 0;   // обнулил переменные
            sumIncomeMonth = 0;     // обнулил переменные
        }


        for (int i = 0; i < yearlyReport.yearlyReportRecords.size(); i++) {
            int month = yearlyReport.yearlyReportRecords.get(i).getMonth();             // получили номер месяца
            if (month == 1) {
                listMonth.put(1, "Январь");
            } else if (month == 2) {
                listMonth.put(2, "Февраль");
            } else if (month == 3) {
                listMonth.put(3, "Март");
            }

            int amount = yearlyReport.yearlyReportRecords.get(i).getAmount();           // получили сумму
            boolean isExpense = yearlyReport.yearlyReportRecords.get(i).getIsExpense(); // получили расход - true/доход - false

            if (isExpense) {
                listExpensesYear.add(amount);   // записали в список расходов
            } else {
                listIncomeYear.add(amount);     // записали в список доходов
            }
        }

        boolean flag = true;
        for (int i = 0; i < listExpensesYear.size(); i++) {
            if ((listExpensesYear.get(i) - listExpensesMonth.get(i) == 0) && (listIncomeYear.get(i) - listIncomeMonth.get(i) == 0)) {
            } else {
                System.out.println("Ошибка в месяце " + listMonth.get(i + 1));
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Сверка отчетов завершена успешно!");
        }
    }
}