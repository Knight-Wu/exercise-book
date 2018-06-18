package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class ChargenClient {
    public static int PORT = 19;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("chargenClient host port");
            return;
        }
        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (Exception e) {
            port = PORT;
        }

        try {
            SocketAddress address = new InetSocketAddress(args[0], port);
            SocketChannel client = SocketChannel.open();
            ByteBuffer buffer = ByteBuffer.allocate(100);
            WritableByteChannel out = Channels.newChannel(System.out);

            // 可以设置 客户端读不阻塞 , 立马返回 client.configureBlocking(false);
            while (client.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
