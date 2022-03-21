package calc;

// Объект данного класса будет хранить в себе само выражение, которое ввёл пользователь
// плюс дополнительные поля, значения которых будем вычислять отдельно
// Также в этом классе будет проводиться первчиная валидация выражения

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {

    private String firstOperand;
    private String secondOperand;
    private char operation;
    private String firstOperandValue;
    private String secondOperandValue;
    private String typeOfSecondOperand;

    public Expression(String inputString) {

        ArrayList<String> operands = new ArrayList<String>();

        //Анализируем строку.
        //Ругулярка ищет: либо строки в кавычках, либо знаки (+-/*) либо цифры
        Matcher matcher = Pattern.compile("(\\\"[^\\\"]+\")|([+-\\/*])|(\\d+)").matcher(inputString);

        while (matcher.find()) {
            operands.add(matcher.group());
        }

        //В листе operands должно быть только три значения: два операнда и знак между ними.
        //Если это не так, значит выражение введено некорректное
        if (operands.size() != 3) {
            throw new IllegalArgumentException("Строка должна состоять из двух операндов и знака между ними");
        }

        //Проверяем длинну вводимых строк
        if (operands.get(0).replaceAll("\"", "").length() > 10
                || operands.get(2).replaceAll("\"", "").length() > 10) {
            throw new IllegalArgumentException("Длина каждой строки не должна превышать 10 символов");
        }

        this.firstOperand = operands.get(0);
        this.secondOperand = operands.get(2);
        this.operation = operands.get(1).charAt(0);

    };


    public String getFirstOperand() {
        return this.firstOperand;
    }

    public void setTypeOfSecondOperand(String typeOfSecondOperand) {
        this.typeOfSecondOperand = typeOfSecondOperand;
    }

    public String getTypeOfSecondOperand() {
        return this.typeOfSecondOperand;
    }

    public String getSecondOperand() {
        return this.secondOperand;
    }

    public char getOperation() {
        return this.operation;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getFirstOperandValue() {
        return this.firstOperandValue;
    }

    public String getSecondOperandValue() {
        return this.secondOperandValue;
    }


}
