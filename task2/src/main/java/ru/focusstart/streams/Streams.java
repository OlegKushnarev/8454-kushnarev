package ru.focusstart.streams;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Streams {
    private BufferedReader bufferFromFile;
    private BufferedWriter bufferedWriter;

    public Streams(String[] args) throws FileNotFoundException {
        bufferFromFile = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
        if (args.length > 1) {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
        }
        else {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        }
    }

    public void close() throws IOException {
        bufferFromFile.close();
        bufferedWriter.close();
    }

    public List<String> readToListStrings() throws IOException {
        List<String> strings = new ArrayList<>();
        while (bufferFromFile.ready()) {
            strings.add(bufferFromFile.readLine());
        }
        return strings;
    }

    public void print(Object output) throws IOException {
        bufferedWriter.write(output.toString());
    }
}
