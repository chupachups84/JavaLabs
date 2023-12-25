package lab.chernyshev;
import java.util.*;

/**
 * Класс кальулятор
 */
public class Calculator {

    private final Map<String, Double> variables = new HashMap<>();
    /**
     *Метод проверяет , что приоритет operator1 больше, чем приоритет operator2
     * @param operator1 - алгебраический оператор
     * @param operator2 - алгебраический оператор
     * @return operator1 более приоритетен ,чем operator2
     */
    private boolean hasHigherPrecedence(String operator1, String operator2) {
        return (operator1.equals("*") || operator1.equals("/")) && (operator2.equals("+") || operator2.equals("-"));
    }

    /**
     * Метод применяет опертор для операнда 1 и операнда 2
     * @param operator - алгебраический оператор, передается ввиде строки
     * @param operand2 - применяемый операнд
     * @param operand1 - изменяемый операнд
     * @return результат выражения operand1(operator)operand2
     */
    private double applyOperator(String operator, double operand2, double operand1) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    /**
     * Метод проверяет является ли пришедший токен числом с плавающей точкой
     * @param token - пришедший токен
     * @return token является числом с плавающей точкой
     */
    private boolean isNumeric(String token) {
        return token.matches("-?\\d+(\\.\\d+)?");
    }
    /**
     * Метод проверяет является ли пришедший токен оператором
     * @param token - пришедший токен
     * @return token является оператором
     */
    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }
    /**
     * Метод проверяет является ли пришедший токен переменной
     * @param token - пришедший токен
     * @return token является переменной
     */
    private boolean isVariable(String token) {
        return token.matches("[a-zA-Z]+");
    }

    /**
     * Метод считывает информацию от пользователя в введенные переменные
     * @param variableName - название переменной
     * @return значение для variableName
     */
    private double getVariableValueFromUser(String variableName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value for variable " + variableName + ": ");
        return scanner.nextDouble();
    }

    /**
     * Метод производит рассчет вводимого выражения.
     * Как работает:
     * Входная строка разбивается на массив токенов по пробелам.
     * Создаются и инициализируются 2 стека values и operators.
     * Далее идет цикл по токенам:
     * если токен число, добавляем в стек values,
     * если токен "(" , добавляем в operators,
     * если токен ")" , проверяем ,что до этого в operators лежит "(", и тогда выполняем операцию в скобках, удалив из
     * operators скобки, а в values заменяем значения для которых выполняется операция на их результат,
     * если токен оператор , выполняем операцию для значений, если он имеет высший приоритет для всех оперторов из operators,
     * если токен переменная и при этому до этого не вводилась, то вызывается функция getVariableValueFromUser, и добавляется в variables,
     * если же она до этого вводилась , то ее количество увеличивается,
     * иначе выбрасивается исключение об ошибке введенного выражения.
     * Далее в цикле пока operators не пусто:
     * применяются операторы из operators к значениям из values.
     * Если после опустошения operators у нас осталось не одно значение(результат), значит выражение не полулось посчитать ,
     * выбрасывает исключение.
     * @param expression - передаваемое выражение в виде строки ( <переменная1> <оператор1> <переменная2> )
     * @return результат выражения
     * @throws Exception - некорректность введенного выражения
     */
    public double calculateExpression(String expression) throws Exception {
        String[] tokens = expression.split("\\s+");
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token)) {
                values.push(Double.parseDouble(token));
            }
            else if (token.equals("(")) {
                operators.push(token);
            }
            else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            }
            else if (isOperator(token)) {
                while (!operators.isEmpty() && hasHigherPrecedence(token, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            }
            else if (isVariable(token)) {
                if (!variables.containsKey(token)) {
                    double value = getVariableValueFromUser(token);
                    variables.put(token, value);
                }
                values.push(variables.get(token));
            }
            else {
                throw new Exception("Invalid token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        if (values.size() != 1) {
            throw new Exception("Invalid expression");
        }

        return values.pop();
    }

}

