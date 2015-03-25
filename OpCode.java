import java.io.File;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.platform.mapreduce.shuffle.lib.ShuffleLibWrapper;


public class OpCode {

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    InetSocketAddress sAddr = new InetSocketAddress(args[0], Integer.parseInt(args[1]));
    Socket sock = new Socket();
    sock.setKeepAlive(false);
    sock.setTcpNoDelay(true);
    sock.setSendBufferSize(65536);
    sock.setReceiveBufferSize(65536);
    sock.connect(sAddr,10000);
    System.out.println("Connected to [" + args[0] + ":" + args[1]);
    
    byte[] copyRequest = ShuffleLibWrapper.instance().makeCopyFileSegmentRequest("NO_EXIST",
        0,
       10,
       Integer.parseInt(args[2]));    
    
    sock.getOutputStream().write(copyRequest);
    System.out.println("Sent opcode " + Integer.parseInt(args[2]) +" and waiting for server");
    byte[] target = new byte[1024];
    int read = 1;
    while (read > 0)
    {
       read = sock.getInputStream().read(target, 0, 1024);
    }
    
    sock.close();
    System.out.println("done");
    
  }

}
