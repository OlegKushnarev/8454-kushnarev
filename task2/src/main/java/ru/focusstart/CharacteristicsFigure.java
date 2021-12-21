package ru.focusstart;

import ru.focusstart.figures.Figure;
import ru.focusstart.figures.FigureCreator;
import ru.focusstart.figures.Figures;
import ru.focusstart.streams.Streams;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CharacteristicsFigure {

    public static void main(String[] args) {
        System.out.println("Для расчёта фигуры введите имя входного файла (обязательный параметр),");
        System.out.println("имя выходного файла (необязательный параметр)");

        if (!checkParameters(Arrays.asList(args))) {
            return;
        }

        Streams streams = null;
        try {
            streams = new Streams(args);
            List<String> figureOptions = streams.readToListStrings();
            if (figureOptions.isEmpty()) {
                System.out.println("Ошибка! Файл пуст.");
                return;
            }

            FigureCreator figureCreator = Figures.valueOf(figureOptions.get(0)).getFigureCreator();
            Figure figure = figureCreator.getFigure(figureOptions);
            figure.calculateFigure();

            streams.print(figure);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (streams != null) {
                    streams.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
