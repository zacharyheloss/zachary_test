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
 * desc socket io������    1.accept 2.inputstream.read
 * ���߳�ֻ����1���ͻ��ˣ����߳̿����ж���ͻ��ˣ��ǳ��������ܣ�1��socket����һ���ͻ���
 */
public class SocketServer {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket=new ServerSocket(10101);
		System.out.println("socket����������");
		while(true){
			final Socket socket=serverSocket.accept();
			System.out.println("����һ���ͻ���");
			//ҵ����
			handle(socket);
		}
	}

	
	
	public static void handle(Socket socket){
		try{
			
			byte[] bytes=new byte[1024];
			InputStream inputStream=socket.getInputStream();
			while(true){
				//��ȡ���ݣ�������
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
				System.out.println("socket �ر�");
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
