package nio;

import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.rmi.server.ExportException;

public class BufferTest {


    private static void readAndWriteByBuffer() {
        try {
            String inputFilePath = "D:\\WT\\code\\git\\exercise-book\\exerciseBook\\src\\main\\java\\nio\\DataInput.txt";
            String outputFilePath = "D:\\WT\\code\\git\\exercise-book\\exerciseBook\\src\\main\\java\\nio\\DataOutput.txt";
            System.out.println(Paths.get(".").getRoot());
            RandomAccessFile aFile = new RandomAccessFile(inputFilePath, "rw");
            RandomAccessFile outFile = new RandomAccessFile(outputFilePath, "rw");
            FileChannel inChannel = aFile.getChannel();

            int bufferSize = 10;
            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(bufferSize);

            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {
                buf.flip();  //make buffer ready for read

                while (buf.hasRemaining()) {
                    byte[] temp = new byte[bufferSize];
                    buf.get(temp); // read 1 byte at a time
                    outFile.write(temp);
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
            System.out.println("all done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(". path to absolute path , path to String : " + Paths.get("target").toAbsolutePath().toString());
        System.out.println(". path to absolute path , path to file : " + Paths.get(".").toAbsolutePath().toFile().getAbsolutePath());
        System.out.println("root path: " + Paths.get(".").getRoot());
        System.out.println("parent path: " + Paths.get(".").getParent());
        System.out.println("fileName: " + Paths.get(".").getFileName());

        System.out.println("root path: " + Paths.get("D:\\WT\\code\\git\\exercise-book\\exerciseBook\\src\\main\\java\\nio").getRoot());
        System.out.println("parent path: " + Paths.get("D:\\WT\\code\\git\\exercise-book\\exerciseBook\\src\\main\\java\\nio").getParent());
        System.out.println("fileName: " + Paths.get("D:\\WT\\code\\git\\exercise-book\\exerciseBook\\src\\main\\java\\nio").getFileName());

//        readAndWriteByBuffer();
    }
}
