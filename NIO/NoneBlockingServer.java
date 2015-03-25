package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class NoneBlockingServer {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    // create object
    System.out.println("INFO: " + "Start up");
    Selector selector = Selector.open();
    ServerSocketChannel server = ServerSocketChannel.open();
    
    System.out.println("INFO: " + "Init");
    //init
    server.socket().bind(new InetSocketAddress(9999));
    server.configureBlocking(false);
    
    //register
    server.register(selector, SelectionKey.OP_ACCEPT);
    System.out.println("INFO: " + "Ready");
    while (true) {
     selector.select();
     Set<SelectionKey> keys = selector.selectedKeys();
     Iterator<SelectionKey> keysIter = keys.iterator();
     
     while(keysIter.hasNext()) {
       SelectionKey key = keysIter.next();
       //keysIter.remove();
       
       SelectableChannel channel = key.channel();
       
       if (key.isAcceptable()) {
         SocketChannel gotCh = ((ServerSocketChannel) channel).accept();  
         gotCh.socket().setKeepAlive(true);
         String clientIP = gotCh.socket().getInetAddress().getHostAddress();
         System.out.println("INFO: " + "Acceptable - "
             + "get connection requestion from <" 
             + clientIP + ">");
         gotCh.configureBlocking(false);
         gotCh.register(selector, SelectionKey.OP_READ);
       } else if (key.isConnectable()) {
         System.out.println("INFO: " + "Connectable - ");
       } else if (key.isReadable()) {
         SocketChannel sc = (SocketChannel)channel;
         Socket socket = sc.socket();
         String clientIP = socket.getInetAddress().getHostAddress();
         if (sc.isConnected()) {
           System.out.print("INFO: " + "Readable - got msg from <"
           + clientIP + ">: ");
           
           ByteBuffer buffer = ByteBuffer.allocate(64);
           int ret = sc.read(buffer);//may not read all data
           if (ret >=0)
             System.out.println(new String(buffer.array()));
           else {
             System.out.println("Closed");
             socket.close();
             key.cancel();
           }
         } else {
           System.out.println("WARN: " + "Readable - peer <"
               + clientIP + "> reset connection");
         }         
       } else if (key.isWritable()) {
         System.out.println("INFO: " + "Writable - ");
       }
       keysIter.remove();
     }
     
     
     
     
    }

  }

}
