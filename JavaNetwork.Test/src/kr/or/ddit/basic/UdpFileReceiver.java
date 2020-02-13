package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
  
public class UdpFileReceiver {
	
    public static final int DEFAULT_BUFFER_SIZE = 10000;
    
    public static void main(String[] args) throws Exception {
    	
        int port = 8888;
 
        long fileSize;
        long totalReadBytes = 0;
         
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int nReadSize = 0;
        System.out.println("파일 수신 대기중...");
          
        DatagramSocket ds = new DatagramSocket(port);
        FileOutputStream fos = null;       
        fos = new FileOutputStream("e:/D_Other/Tulips3.jpg");
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp);
        String str = new String(dp.getData()).trim();
         
        if (str.equals("start")){ // sender에서 전송을 시작한 경우...
        	
            dp = new DatagramPacket(buffer, buffer.length);
            ds.receive(dp);
            str = new String(dp.getData()).trim();
            fileSize = Long.parseLong(str);
            double startTime = System.currentTimeMillis(); 
            while (true) {
                ds.receive(dp);
                str = new String(dp.getData()).trim();
                nReadSize = dp.getLength();
                fos.write(dp.getData(), 0, nReadSize);
                totalReadBytes+=nReadSize;
                System.out.println("In progress: " + totalReadBytes + "/"
                        + fileSize + " Byte(s) ("
                        + (totalReadBytes * 100 / fileSize) + " %)");
                if(totalReadBytes>=fileSize) {
                	break;
                }
            }
            double endTime = System.currentTimeMillis();
            double diffTime = (endTime - startTime)/ 1000;;
            double transferSpeed = (fileSize / 1000)/ diffTime;
             
            System.out.println("time: " + diffTime+ " second(s)");
            System.out.println("Average transfer speed: " + transferSpeed + " KB/s");
            System.out.println("File transfer completed");
            
            System.out.println("수신 처리 완료...");
            fos.close();
            ds.close();
            
        }else{
            System.out.println("수신 처리 불가!!!");
            fos.close();
            ds.close();
        }
  
    }
}

