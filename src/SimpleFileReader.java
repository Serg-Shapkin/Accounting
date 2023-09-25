import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


// класс для чтения из файлов отчетов
public class SimpleFileReader {

    // константа с расположением файла месячного отчета без последней цифры и расширения
    private static final String MONTH_DATA_FILE_NAME = "resources/m.20210";
    private static final String FILE_EXTENSION = ".csv"; // константа расширения файла

    private static final String YEAR_DATA_FILE_NAME = "resources/y.2021.csv";    // константа с располоением файла годового отчета
    private static final String LINE_SEPARATOR = "\n";  // константа для разделения строк по enter
    private static final String COMMA_SEPARATED_VALUES_SEPARATOR = ","; // константа для разделения строк по запятым

    // для месячного отчета
    private static final int ITEM_NAME_INDEX = 0;       // константа номера ячейки с наименованием товара
    private static final int IS_EXPENSE_THIS_MONTH = 1; // константа номера ячейки с расход - true/доход - false
    private static final int QUANTITY = 2;              // константа номера ячейки с количеством товара
    private static final int SUM_OF_ONE = 3;            // константа номера ячейки со стоимостью за единицу

    // для годового отчета
    private static final int MONTH = 0;                 // константа номера ячейки с номером месяца
    private static final int AMOUNT = 1;                // константа номера ячейки с суммой
    private static final int IS_EXPENSE_THIS_YEAR = 2;  // константа номера ячейки с расход - true/доход - false



    // "1 - Считать все месячные отчеты"
    public List<MonthlyReport> countAllMonthlyExpenses() {
        List<MonthlyReport> monthlyReportList = new ArrayList<>();
        //SimpleFileReader reader = new SimpleFileReader();    // создаем объект класса чтения из файлов
        // считываем три файла с месячным отчетом
        for (int i = 1; i <=3 ; i++) {
            MonthlyReport monthlyReport = new MonthlyReport();  // создаем объект класса месячного отчета
            String contentMonthlyReport = this.readFileContentsOrNull(MONTH_DATA_FILE_NAME + i + FILE_EXTENSION);
            String[] monthlyLines = contentMonthlyReport.split(LINE_SEPARATOR); // сохраняем строки в массив разбивая по enter
            for (int j = 1; j < monthlyLines.length; j++) {     // исключаем заголовки
                String monthlyLine = monthlyLines[j];           // сохраняем строку в
                String[] monthlyParts = monthlyLine.split(COMMA_SEPARATED_VALUES_SEPARATOR); // разбиваем полученную выше строку
                String itemName = monthlyParts[ITEM_NAME_INDEX];                    // теперь itemName хранит имя строки
                boolean isExpense = Boolean.parseBoolean(monthlyParts[IS_EXPENSE_THIS_MONTH]); // хранит true - расход и false - доход
                int quantity = Integer.parseInt(monthlyParts[QUANTITY]);            // хранит количество товара
                int sumOfOne = Integer.parseInt(monthlyParts[SUM_OF_ONE]);          // хранит стоимость 1 едницы товара
                // добавляем запись месячного отчета
                MonthlyReportRecord monthlyReportRecord = new MonthlyReportRecord(itemName, isExpense, quantity, sumOfOne);
                monthlyReport.add(monthlyReportRecord); //сохраняем запись месячного отчета
            }
            monthlyReportList.add(monthlyReport);
        }
        return monthlyReportList;
    }

    // "2 - Считать годовой отчет"
    public YearlyReport readAnnualReport() {
        //SimpleFileReader reader= new SimpleFileReader();    // создаем объект класса чтения из файлов
        YearlyReport yearlyReport = new YearlyReport();     // создаем объект класса годового отчета
        // считываем файл годового отчета
        String contentYearlyReport = this.readFileContentsOrNull(YEAR_DATA_FILE_NAME);
        String[] yearlyLines = contentYearlyReport.split(LINE_SEPARATOR);
        for (int i = 1; i < yearlyLines.length; i++) {
            String yearlyLine = yearlyLines[i];
            String[] yearlyParts = yearlyLine.split(COMMA_SEPARATED_VALUES_SEPARATOR);
            int month = Integer.parseInt(yearlyParts[MONTH]); // хранит номер месяца
            int amount = Integer.parseInt(yearlyParts[AMOUNT]); // хранит сумму заказов
            boolean isExpense = Boolean.parseBoolean(yearlyParts[IS_EXPENSE_THIS_YEAR]); // хранит true - расход и false - доход
            // добавляем запись годового отчета
            YearlyReportRecord yearlyReportRecord = new YearlyReportRecord(month, amount, isExpense);
            yearlyReport.add(yearlyReportRecord); //сохраняем запись годового отчета
        }
        return yearlyReport;
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным/годовым отчётом. " +
                    "Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
