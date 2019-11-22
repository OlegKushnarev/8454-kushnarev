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
        try {
            assert inStream != null;
            inStream.close();
        } catch (IOException e) {
            throw e;
        }

        try {
            assert outStream != null;
            outStream.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public List<String> readToListStrings() throws IOException {
        List<String> strings = new ArrayList<>();
        BufferedReader bufferFromFile = null;

        try {
            bufferFromFile = new BufferedReader(new InputStreamReader(this.inStream, StandardCharsets.UTF_8));

            while (bufferFromFile.ready())
            {
                strings.add(bufferFromFile.readLine());
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла.");
            throw e;
        }finally {
            try {
                assert bufferFromFile != null;
                bufferFromFile.close();
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return strings;
    }

    public void print(Object output) throws IOException {
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.outStream, StandardCharsets.UTF_8));

            bufferedWriter.write(output.toString());

        } catch (IOException e) {
            System.out.println("Ошибка записи файла.");
            throw e;
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
