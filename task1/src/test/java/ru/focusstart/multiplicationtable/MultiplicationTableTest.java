package ru.focusstart.multiplicationtable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiplicationTableTest {

    @Test
    public void testMakeLineSeparator() {

        String lineSeparator = MultiplicationTable.makeLineSeparator(2, 1, "-");

        assertEquals("-+-", lineSeparator);
    }

}