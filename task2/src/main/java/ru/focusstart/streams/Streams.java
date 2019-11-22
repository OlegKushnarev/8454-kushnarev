package ru.focusstart.streams;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Streams {
    private InputStream inStream;
    private OutputStream outStream = System.out;

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public OutputStream getOutStream() {
        return outStream;
    }

    public void setOutStream(OutputStream outStream) {
        this.outStream = outStream;
    }

    public Streams(String[] args) throws FileNotFoundException {
        this.inStream = new FileInputStream(args[0]);
        if (args.length > 1) {
            this.outStream = new FileOutputStream(args[1]);
        }
    }

    public void close() throws IOException {
        inStream.close();
        outStream.close();
    }

    public List<String> readToListStrings() throws IOException {
        List<String> strings = new ArrayList<>();
        BufferedReader bufferFromFile = new BufferedReader(new InputStreamReader(this.inStream, StandardCharsets.UTF_8));
        while (bufferFromFile.ready()) {
            strings.add(bufferFromFile.readLine());
        }
        bufferFromFile.close();

        return strings;
    }

    public void print(Object output) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.outStream));
        bufferedWriter.write(output.toString());
        bufferedWriter.close();
    }
}
