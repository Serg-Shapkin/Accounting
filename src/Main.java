import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimpleFileReader simpleFileReader = new SimpleFileReader();  // считываем данные
        StatisticCalculator statisticCalculator = new StatisticCalculator();
        MonthlyReportsCalculator monthlyReportsCalculator = new MonthlyReportsCalculator();
        List<MonthlyReport> monthlyReport = simpleFileReader.countAllMonthlyExpenses(); // месячный отчет
        YearlyReport yearlyReport = simpleFileReader.readAnnualReport(); // годовой отчет

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                System.out.println("Все отчеты считаны.");
                simpleFileReader.countAllMonthlyExpenses();
            } else if (command == 2) {
                System.out.println("Годовой отчет считан.");
                simpleFileReader.readAnnualReport();
            } else if (command == 3) {
                System.out.println("Сверяем отчеты.");
                statisticCalculator.verificationReports(monthlyReport, yearlyReport);
            } else if (command == 4) {
                if (monthlyReport != null) { // проверка на пустой отчет
                    System.out.println("Выводим информацию о всех месячных отчетах.");
                    monthlyReportsCalculator.informationMonthlyReports();
                } else {
                    System.out.println("Необходимо считать отчет за месяцы, нажмите цифру 1");
                }
            } else if (command == 5) {
                if (yearlyReport != null) { // проверка на пустой отчет
                    System.out.println("Выводим информацию о годовом отчете.");
                    yearlyReport.informationYearlyReports();
                } else {
                    System.out.println("Необходмо считать годовой отчет, нажмите цифру 2");
                }
            } else if (command == 0) {
                System.out.println("Возвращайтесь!");
                break;
            } else {
                System.out.println("Такой команды нет");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выход из программы");
    }
}

