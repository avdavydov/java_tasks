package calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {

            //Ожидаем ввод выражения пользователем
            System.out.println("Введите выражение: ");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();

            // После ввода выражения, создаём объект expression класса Expression,
            // в котором будет хранится выражение и вызываем для него метод проверки выражения
            // ExpressionProcessing.expressionValidation

            Expression expression = ExpressionProcessing.expressionValidation(new Expression(text));
            String result = ExpressionProcessing.calc(expression);
            System.out.println(result);
            expression = null;

        }
    }
}
