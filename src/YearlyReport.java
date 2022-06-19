import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// класс месячных отчетов только хранит данные
public class YearlyReport {

    List<YearlyReportRecord> yearlyReportRecords = new ArrayList<>(); // хранит объекты годовых отчетов

    public List<YearlyReportRecord> getYearlyReportRecords() {

        return yearlyReportRecords;
    }


    public void add(YearlyReportRecord record) {

        yearlyReportRecords.add(record);
    }

    public void informationYearlyReports() {
        List<Integer> listExpenses = new ArrayList<>();   // список расходов в годовом отчете
        List<Integer> listIncome = new ArrayList<>();     // список доходов в годовом отчете
        Map<Integer,String> listMonth = new HashMap();       // название месяца

        for (int i = 0; i < yearlyReportRecords.size(); i++) {
            int month = yearlyReportRecords.get(i).getMonth();             // получили номер месяца
            if (month == 1) {
                listMonth.put(1, "Январь");
            } else if (month == 2) {
                listMonth.put(2, "Февраль");
            } else if (month == 3) {
                listMonth.put(3, "Март");
            }

            int amount = yearlyReportRecords.get(i).getAmount();           // получили сумму
            boolean isExpense = yearlyReportRecords.get(i).getIsExpense(); // получили расход - true/доход - false

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

}
