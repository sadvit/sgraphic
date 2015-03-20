package com.sadvit.io.stream;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamLittleEndian {

    private DataInputStream systemStream;

    public DataInputStreamLittleEndian(String path) throws IOException {
        systemStream = new DataInputStream(new FileInputStream(path));
    }

    public DataInputStreamLittleEndian(DataInputStream systemStream) {
        this.systemStream = systemStream;
    }

    public void close() throws IOException {
        systemStream.close();
    }

    public String readString(int numberOfCharacters) throws IOException {
        byte[] bytesRead = new byte[numberOfCharacters];
        if (systemStream.read(bytesRead) > 0) {
            return new String(bytesRead);
        } else {
            return null;
        }
    }

    public int readInt16() throws IOException {  // Returns unsigned int16
        int x = systemStream.readShort();
        return (x & 0xFF) << 8 | (x & 0xFF00) >>> 8;
    }


    public int readInt32() throws IOException {
        return Integer.reverseBytes(systemStream.readInt());
    }

    public void skip(int len) throws IOException {
        while (len > 0) {
            long temp = systemStream.skip(len);
            if (temp == 0)
                throw new EOFException();
            len -= temp;
        }
    }

    // readBMP from stream byte array
    public void read(byte[] bytes) throws IOException {
        int off = 0;
        while (off < bytes.length) {
            int temp = systemStream.read(bytes, off, bytes.length - off);
            if (temp == -1)
                throw new EOFException();
            off += temp;
        }
    }

}