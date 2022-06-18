import java.util.ArrayList;
import java.util.List;




// класс месячных отчетов только хранит данные
public class MonthlyReport {
    List<MonthlyReportRecord> monthlyReportRecords = new ArrayList<>(); // хранит объекты месячных отчетов

    List<MonthlyReportRecord> getMonthlyReportRecords() {
        return monthlyReportRecords;
    }

    public void add(MonthlyReportRecord record) {
        monthlyReportRecords.add(record); // записываем данные в месячный отчет
    }
}

