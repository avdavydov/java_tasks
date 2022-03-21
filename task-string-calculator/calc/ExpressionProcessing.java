package calc;

import java.util.Arrays;

public class ExpressionProcessing {

    public static Expression expressionValidation(Expression expression) {

        //Получаем значение полей выражения из объекта expression:
        String firstOperand = expression.getFirstOperand();
        String secondOperand = expression.getSecondOperand();
        char operation = expression.getOperation();

        //Выделяем первый и последний символ в первом операнде
        char firstChar = firstOperand.charAt(0);
        char lastChar = firstOperand.charAt(firstOperand.length() - 1);

        //Если первый и последний символы в первом операнде не кавычки, бросаем исключение
        if (firstChar != '"' || lastChar != '"') {
            throw new IllegalArgumentException("Первый элемент выражения должен быть строкой в кавычках");
        }

        //Если проверка выше прошла, значит первый операнд - Строка
        String typeOfFirstOperand = "String";

        //Проверяем тип второго операнда
        String typeOfSecondOperand = null;

        //Выделяем первый и последний символ во втором операнде
        firstChar = secondOperand.charAt(0);
        lastChar = secondOperand.charAt(secondOperand.length() - 1);

        //Если первый и последний символы во втором операнде - кавычки, значит это строка
        if (firstChar == '"' && lastChar == '"') {
            typeOfSecondOperand = "String";
        }

        //Если второй операнд не строка, проверяем не является ли он целым числом?
        if (typeOfSecondOperand == null) {
            try {
                int secondOperandValue = Integer.parseInt(secondOperand);
                typeOfSecondOperand = "Int";
                //Заодно провреим, что если второй операнд - число, оно должно быть от 1 до 10
                if (secondOperandValue > 10 || secondOperandValue < 1) {
                    throw new IllegalArgumentException("Значение второго операнда должно быть от 1 до 10");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Тип второго операда должен быть либо строкой, либо целым числом");
            }


        }

        // Из условия:
        // Калькулятор умеет выполнять операции сложения строк,
        // вычитания строки из строки,
        // умножения строки на число
        // и деления строки на число

        //Проверяем:
        //Выполняем что запрошенная операция входит в число допустимых
        if (typeOfFirstOperand.equals("String") && typeOfSecondOperand.equals("String")) {
            if (Arrays.asList("+", "-").indexOf(Character.toString(operation)) == -1) {
                throw new IllegalArgumentException("Для операций с двумя строками доступно только сложение и вычитание");
            }

        } else if (typeOfFirstOperand.equals("String") && typeOfSecondOperand.equals("Int")) {
            if (Arrays.asList("*", "/").indexOf(Character.toString(operation)) == -1) {
                throw new IllegalArgumentException("Для операций между строкой и числом доступно только умножение и деление");
            }
        }

        //Заполняем поля объекта expression
        expression.setFirstOperand(firstOperand.replaceAll("\"", ""));
        expression.setSecondOperand(secondOperand.replaceAll("\"", ""));
        expression.setTypeOfSecondOperand(typeOfSecondOperand);

        //Возвращаем объект expression класса Expression
        return expression;

    }

    public static String calc(Expression expression) {
        //В этом методе будем делать подготовку для вычислений

        // Получаем значения необходимых полей из объекта expression
        // и сохраняем их в переменные
        String firstOperand = expression.getFirstOperand();
        String secondOperand = expression.getSecondOperand();
        String typeOfSecondOperation = expression.getTypeOfSecondOperand();
        char operation = expression.getOperation();

        String result = null;

        //Вычисляем результат, в зависимости от типа второго операнда
        if (typeOfSecondOperation.equals("String")) {
            result = doCalc(firstOperand, secondOperand, operation);

        } else if (typeOfSecondOperation.equals("Int")) {
            int secondOperandIntValue = Integer.parseInt(secondOperand);
            result = doCalc(firstOperand, secondOperandIntValue, operation);

        } else {
            throw new IllegalArgumentException("Тип второго операда должен быть или String или int");
        }

        return result;

    }

    private static String doCalc(String firstOperand, String secondOperand, char operation) {
        //В этом методе будем выполнять вычисления для случая, когда оба операнда - строки
        String result = null;
        switch (operation) {
            case '+':
                result = "\"" + firstOperand + secondOperand + "\"";

                if (result.length() > 40) {
                    result = reduceResult(result);
                }

                break;
            case '-':
                result = "\"" + firstOperand.replaceAll(secondOperand, "") + "\"";
                break;
        }

        return result;
    }

    private static String doCalc(String firstOperand, int secondOperand, char operation) {
        //В этом методе будем выполнять вычисления для случая, когда второй операнд - число
        String result = null;
        switch (operation) {
            case '*':
                result = firstOperand;
                for (int i = 0; i < secondOperand - 1; i++) {
                    result = result + firstOperand;
                }

                result = "\"" + result + "\"";

                if (result.length() > 40) {
                    result = reduceResult(result);
                }
                break;

            case '/':
                int lenOfFirstOperand = firstOperand.length();
                result = "\"" + firstOperand.substring(0, lenOfFirstOperand / 3) + "\"";
                break;
        }
        return result;
    }

    private static String reduceResult(String result) {
        //В этом методе обрезаем длинную строку, по условию задания
        return result.substring(0, 41) + "...\"";
    }

}
