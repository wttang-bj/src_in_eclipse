package NIO;

import java.net.InetSocketAddress;
import java.net.SocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class SCServer {

  private final static int BUFFER_SIZE = 1024;
  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    System.out.println("Setting up");
    Charset charSet = Charset.forName("UTF-8");
    CharsetDecoder decoder = charSet.newDecoder();
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    System.out.println("Listening at port 9999");
    serverSocketChannel.socket().bind(new InetSocketAddress(9999));
    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    while(true){
        buffer.clear();
        
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.socket().setKeepAlive(true);
        
        System.out.print("Got connection from Host <"
            + socketChannel.socket().getInetAddress().getHostAddress()
            + ">");
        
        socketChannel.read(buffer);
        int pos = buffer.position();
        
        buffer.flip();
        String gotString = decoder.decode(buffer).toString();
        System.out.print(" got msg <" + gotString + ">");
        
        buffer.clear();
        buffer.put((gotString+ " - from server.").getBytes("UTF-8"));
        
        buffer.flip();
        
        while(buffer.hasRemaining()) {
          socketChannel.write(buffer);
        }
        System.out.println(" replied");
        socketChannel.close();
    
    }


  }

}
