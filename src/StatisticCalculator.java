import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticCalculator {

    public void informationMonthlyReports (List<MonthlyReport> monthlyReports) {
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
            System.out.println(listMonth.get(monthNumber));
            System.out.println("Самый прибыльный товар: " + nameMaxSum + " - " + maxSum);
            System.out.println("Самая большая трата: " + nameMaxExpense + " - " + maxExpense);
        }
    }

    public void informationYearlyReports(YearlyReport yearlyReport) {
        List<Integer> listExpenses = new ArrayList<>();   // список расходов в годовом отчете
        List<Integer> listIncome = new ArrayList<>();     // список доходов в годовом отчете
        Map<Integer,String> listMonth = new HashMap();       // название месяца

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
                    listExpenses.add(amount);   // записали в список расходов
                } else {
                    listIncome.add(amount);     // записали в список доходов
                }
            }
        for (int i = 0; i < listExpenses.size(); i++) {
            System.out.println("Прибыль за " + listMonth.get(i + 1) + " месяц: " + (listIncome.get(i) - listExpenses.get(i)));
        }

        int sumExpenses = 0; // сумма расходов за год
        for (int i = 0; i < listExpenses.size(); i++) {
            sumExpenses += listExpenses.get(i);
        }
        System.out.println("- средний расход за все месяцы в году: " + sumExpenses / listExpenses.size() + " руб.");

        int sumIncome = 0;  // сумма доходов за год
        for (int i = 0; i < listIncome.size(); i++) {
            sumIncome += listIncome.get(i);
        }
        System.out.println("- средний доход за все месяцы в году: " + sumIncome / listExpenses.size() + " руб.");
    }

    public void verificationReports(List<MonthlyReport> monthlyReports, YearlyReport yearlyReport) {
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
            MonthlyReport list = monthlyReports.get(i);
            for (int j = 0; j < list.monthlyReportRecords.size(); j++) {
                String itemName = monthlyReports.get(i).monthlyReportRecords.get(j).getItemName();
                boolean isExpense = monthlyReports.get(i).monthlyReportRecords.get(j).getIsExpense();
                int quantity = monthlyReports.get(i).monthlyReportRecords.get(j).getQuantity();
                int sumOfOne = monthlyReports.get(i).monthlyReportRecords.get(j).getSumOfOne();

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