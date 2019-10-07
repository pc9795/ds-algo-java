package util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created By: Prashant Chaubey
 * Created On: 16-08-2019 16:16
 * Purpose: Fast reader for Java
 **/
//No need of nextLine after integer as in Scanner
class Reader {
    private int LINE_LENGTH = 1000000;
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String nextLine() throws IOException {
        byte[] buf = new byte[LINE_LENGTH]; // line length
        int cnt = 0, c;
        while ((c = next()) != -1) {
            if (c == '\n')
                break;
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte c = next();
        while (c <= ' ')
            c = next();
        boolean neg = (c == '-');
        if (neg)
            c = next();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = next()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
    }

    public long nextLong() throws IOException {
        long ret = 0;
        byte c = next();
        while (c <= ' ')
            c = next();
        boolean neg = (c == '-');
        if (neg)
            c = next();
        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = next()) >= '0' && c <= '9');
        if (neg)
            return -ret;
        return ret;
    }

    public double nextDouble() throws IOException {
        double ret = 0, div = 1;
        byte c = next();
        while (c <= ' ')
            c = next();
        boolean neg = (c == '-');
        if (neg)
            c = next();

        do {
            ret = ret * 10 + c - '0';
        }
        while ((c = next()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = next()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg)
            return -ret;
        return ret;
    }

    private void fillBuffer() throws IOException {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private byte next() throws IOException {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null)
            return;
        din.close();
    }
}
