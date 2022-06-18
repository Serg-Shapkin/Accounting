import java.util.ArrayList;

public class YearlyReportRecord {
    int month; // номер месяца
    int amount; // сумма
    boolean isExpense; // это расходы?

    YearlyReportRecord(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getMonth() {
        return month;
    }
    public int getAmount() {
        return amount;
    }
    public boolean getIsExpense() {
        return isExpense;
    }

}