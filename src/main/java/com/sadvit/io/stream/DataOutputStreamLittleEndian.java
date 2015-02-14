package com.sadvit.io.stream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamLittleEndian {

    private DataOutputStream systemStream;

    public DataOutputStreamLittleEndian(String path) throws IOException {
        systemStream = new DataOutputStream(new FileOutputStream(path));
    }

    public DataOutputStreamLittleEndian(DataOutputStream systemStream) {
        this.systemStream = systemStream;
    }

    public void close() throws IOException {
        systemStream.close();
    }

    public void writeInt16(int v) throws IOException {
        systemStream.writeShort((v & 0xFF) << 8 | (v & 0xFF00) >>> 8);
    }


    public void writeInt32(int v) throws IOException {
        systemStream.writeInt(Integer.reverseBytes(v));
    }

    public void write(byte[] array) throws IOException {
        systemStream.write(array);
    }

}