package Abstractions;

import java.io.*;

/**
 * Created by MARKAN on 28.06.2017.
 */
public final class MemoryStorage<T> {
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    public MemoryStorage(File mainDir, String path) {
        try {
            File file = new File(mainDir, path);
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            inputStream = new ObjectInputStream(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T readData() {
        try {
            T object=(T) inputStream.readObject();
            inputStream.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeData(T t) {
        try {
            outputStream.writeObject(t);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
