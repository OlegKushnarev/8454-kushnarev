package ru.focusstart.figures;

import ru.focusstart.streams.Streams;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CharacteristicsFigure {

    public static void main(String[] args) {
        System.out.println("Для расчёта фигуры введите имя входного файла (обязательный параметр),");
        System.out.println("имя выходного файла (необязательный параметр)");

        Streams streams = null;
        try {
            if (!CharacteristicsFigure.checkParameters(Arrays.asList(args))) {
                return;
            }

            streams = new Streams(args);

            List<String> figureOptions = streams.readToListStrings();

            Border border = Figures.valueOf(figureOptions.get(0)).getBorder();
            Figure figure = border.getFigure(figureOptions);
            figure.calculateFigure();

            streams.print(figure);
            streams.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка! Файл пуст.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! В файле указана неизвестная фигура.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkParameters(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Ошибка! Не указано имя входного файла.");
            return false;
        }

        if (list.size() > 2) {
            System.out.println("Ошибка! Указаны неизвестные параметры");
            return false;
        }

        return true;
    }
}
