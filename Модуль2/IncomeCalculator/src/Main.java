import java.util.Scanner;

public class Main
{
    private static int minIncome = 200000;
    private static int maxIncome = 900000;

    private static int officeRentCharge = 140000;
    private static int telephonyCharge = 12000;
    private static int internetAccessCharge = 7200;

    private static int assistantSalary = 45000;
    private static int financeManagerSalary = 90000;

    private static double mainTaxPercent = 0.24;
    private static double managerPercent = 0.15;

    private static double minInvestmentsAmount = 100000;

    public static void main(String[] args)
    {
        //работает, пока вводят действительные числа
        while(true)
        {
            //выводим на экран сообщение
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");
            //записываем в переменную income значение, полученное с экрана
            int income = (new Scanner(System.in)).nextInt();
            //если переменная checkIncomeRange не истина, тогда исполняется
            if(!checkIncomeRange(income)) {
                continue;
            }
            //вычисляем зарплату менеджера
            double managerSalary = income * managerPercent;
            //вычисляем, что останется после вычета зарплаты и ежемесячных платежей
            double pureIncome = income - managerSalary -
                calculateFixedCharges();
            //вычисляем сумму налога
            double taxAmount = mainTaxPercent * pureIncome;
            //вычисляем остаток после оплаты налога
            double pureIncomeAfterTax = pureIncome - taxAmount;
            //создаем булеву переменную, сравнив остаток с минимальной суммой инвестирования
            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount;
//            if (canMakeInvestments)
//            {
//                //создаем переменную, равную сумме возможного инвестирования
//                double pureInvestIncome = pureIncomeAfterTax
//            }
            //выводим полученные значения
            System.out.println("Зарплата менеджера: " + managerSalary);
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет"));
            if(pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
            else if (canMakeInvestments){
                System.out.println("Сумма инвестирования: " + pureIncomeAfterTax);
            }
            else {
                System.out.println("Недостаточно для инвестирования");
            }
        }

    }
    //проверяем величину вводимых доходов
    private static boolean checkIncomeRange(int income)
    {
        if(income < minIncome)
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome)
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }
    //вычисляем переменную фиксированных расходов
    private static int calculateFixedCharges()
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
