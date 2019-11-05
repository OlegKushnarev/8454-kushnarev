import java.util.Scanner;

public class Multiplication_table {

    private static int numberLines = 0;

    public static void main(String[] args) {

        try {
            numberLines = requestNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String maxLengthString = Integer.toString(numberLines * numberLines);

        int cellWidth = maxLengthString.length();

        StringBuilder lineFormat = new StringBuilder("%d|");

        lineFormat.insert(1, Integer.toString(cellWidth));

        printMultiplicationTable(lineFormat.toString(), makeLineSeparator(cellWidth, '-'));
    }

    public static int requestNumber() throws Exception {
        System.out.println("Введите размер таблицы умножения. Размер таблицы должен быть задан целым положительным числом в пределах от 1 до 32 включительно.");

        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt())
        {
            throw new Exception("ОШИБКА! Введённая строка не может быть интерпретирована как целое положительно число.");
        }

        int number = scanner.nextInt();

        if (number < 1 || number > 32)
        {
            throw new Exception("ОШИБКА! Введённое число не пределах от 1 до 32.");
        }

        return number;
    }

    public static String makeLineSeparator(int sizeSeparator, char separator) {
        StringBuilder lineSeparatorFormat = new StringBuilder("%.s+");

        lineSeparatorFormat.insert(2, Integer.toString(sizeSeparator));

        StringBuilder lineSeparator = new StringBuilder(separator);

        for (int i = 1; i <= numberLines; ++i) {
            for (int j = 1; j <= sizeSeparator; ++j) {
                lineSeparator.append(separator);
            }

            if (i < numberLines) {
                lineSeparator.append('+');
            }
        }

        return lineSeparator.toString();
    }

    public static void printMultiplicationTable(String lineFormat, String lineSeparator) {
        for (int i = 1; i <= numberLines; ++i) {
            for (int j = 1; j <= numberLines; ++j) {
                System.out.print(String.format(lineFormat, i * j));
            }

            System.out.println();

            if (i < numberLines)
            {
                System.out.println(lineSeparator);
            }
        }
    }
}
