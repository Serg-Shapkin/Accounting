public class MonthlyReportRecord { // класс для чтения файла месячного отчете
    String itemName; // наименование товара
    boolean isExpense; // это расходы?
    int quantity; // количество
    int sumOfOne; // сумма за единицу товара

    // создаем конструктор класса
    MonthlyReportRecord(String itemName,boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return  itemName;
    }
    public boolean getIsExpense() {
        return  isExpense;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getSumOfOne() {
        return sumOfOne;
    }
}
