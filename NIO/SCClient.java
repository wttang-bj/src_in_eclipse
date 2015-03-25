package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class SCClient {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    SocketChannel client = SocketChannel.open();
    String serverIP = "localhost";
    if (args.length > 0) {
      serverIP = args[0];
    }
    
    System.out.println("Connecting to " + serverIP);
    client.connect(new InetSocketAddress(serverIP, 9999));
    
    Charset charSet = Charset.forName("UTF-8");
    CharsetDecoder decoder = charSet.newDecoder();
    
    String newData = "From client " + System.currentTimeMillis();
    ByteBuffer buf = ByteBuffer.allocate(96);
    
    buf.put(newData.getBytes("UTF-8"));
    buf.flip();
    while(buf.hasRemaining()) {
      client.write(buf); //may not write all data
    }
    
    System.out.println("Sent msg <" + newData + ">");
    
    buf.clear();    
    client.read(buf);
    
    buf.flip();
    String output = decoder.decode(buf).toString();
    System.out.println("Got reply:<" + output +">");
    buf = null;
    
    client.close();
  }

}
