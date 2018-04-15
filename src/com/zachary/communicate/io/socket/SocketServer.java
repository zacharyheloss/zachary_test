/**
 * 
 */
package com.zachary.communicate.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhang
 * desc socket io阻塞点    1.accept 2.inputstream.read
 * 单线程只能有1个客户端，多线程可以有多个客户端，非常消耗性能，1个socket服务一个客户端
 */
public class SocketServer {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket=new ServerSocket(10101);
		System.out.println("socket服务器启动");
		while(true){
			final Socket socket=serverSocket.accept();
			System.out.println("进来一个客户端");
			//业务处理
			handle(socket);
		}
	}

	
	
	public static void handle(Socket socket){
		try{
			
			byte[] bytes=new byte[1024];
			InputStream inputStream=socket.getInputStream();
			while(true){
				//读取数据（阻塞）
				int read=inputStream.read(bytes);
				if(read!=-1){
					System.out.println(new String(bytes,0,read));
				}else{
					break;
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				System.out.println("socket 关闭");
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
