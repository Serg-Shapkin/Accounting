import java.util.ArrayList;
import java.util.List;


// класс месячных отчетов только хранит данные
public class YearlyReport {
    StatisticCalculator statisticCalculator = new StatisticCalculator();


    List<YearlyReportRecord> yearlyReportRecords = new ArrayList<>(); // хранит объекты годовых отчетов

    public List<YearlyReportRecord> getYearlyReportRecords() {
        return yearlyReportRecords;
    }


    public void add(YearlyReportRecord record) {

        yearlyReportRecords.add(record);
    }
}
