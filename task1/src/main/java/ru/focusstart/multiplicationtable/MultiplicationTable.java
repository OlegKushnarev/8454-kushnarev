package ru.focusstart.multiplicationtable;

import java.io.IOException;
import java.util.Scanner;

public class MultiplicationTable {

    public static void main(String[] args) {

        int size = 1;

        try {
            size = requestNumber();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String maxLengthString = Integer.toString(size * size);
        int cellWidth = maxLengthString.length();

        StringBuilder lineFormat = new StringBuilder("%d");
        lineFormat.insert(1, Integer.toString(cellWidth));

        String lineSeparator = makeLineSeparator(size, cellWidth, "-");

        printMultiplicationTable(size, lineFormat.toString(), lineSeparator);
    }

    public static int requestNumber() throws IOException {
        System.out.println("Введите размер таблицы умножения. Размер таблицы должен быть задан целым положительным числом в пределах от 1 до 32 включительно.");

        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            throw new IOException("ОШИБКА! Введённая строка не может быть интерпретирована как целое положительно число.");
        }

        int number = scanner.nextInt();
        if (number < 1 || number > 32) {
            throw new IOException("ОШИБКА! Введённое число не пределах от 1 до 32.");
        }

        return number;
    }

    public static String makeLineSeparator(int numberColumns, int sizeSeparator, String separator) {

        StringBuilder lineSeparator = new StringBuilder();

        for (int i = 1; i <= numberColumns; ++i) {
            for (int j = 1; j <= sizeSeparator; ++j) {
                lineSeparator.append(separator);
            }

            if (i < numberColumns) {
                lineSeparator.append('+');
            }
        }

        return lineSeparator.toString();
    }

    public static void printMultiplicationTable(int size, String lineFormat, String lineSeparator) {
        for (int i = 1; i <= size; ++i) {
            for (int j = 1; j <= size; ++j) {
                System.out.print(String.format(lineFormat, i * j));

                if (j < size) {
                    System.out.print('|');
                }
            }

            System.out.println();

            if (i < size) {
                System.out.println(lineSeparator);
            }
        }
    }
}
