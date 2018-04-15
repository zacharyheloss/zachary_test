/**
 * 
 */
package com.zachary.communicate.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author zhang desc :1个线程同时服务多个客户端
 */
public class NIOServer {

	private int port = 10102;
	private Selector selector;

	/**
	 * 
	 * @desc: 获取一个serverSocket通信，并对该通道做一些初始化工作
	 * @throws IOException
	 * @return void
	 */
	public void initServer() throws IOException {
		// 获得serverSocket通讯
		ServerSocketChannel sschannel = ServerSocketChannel.open();
		// 设置通讯为非阻塞
		sschannel.configureBlocking(false);
		// 通道对应的serversocket绑定到对应port端口
		sschannel.socket().bind(new InetSocketAddress(port));
		// 获取一个通讯管理器
		this.selector = Selector.open();
		// 通信管理器和通道绑定，并未该通道注册selectorKey.op_accept事件
		// 当该事件到达后，selector.select()会返回，否则会一直阻塞
		sschannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	/**
	 * 
	 * @desc: 采用轮询方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * @param args
	 * @return void
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("服务端启动");
		while (true) {
			// 当注册事件到达时，方法返回，否则一直阻塞
			selector.select();

			// 获取selector选项中的迭代器，选中的项为注册事件
			Iterator<SelectionKey> ite = this.selector.selectedKeys()
					.iterator();
			while (ite.hasNext()) {
				SelectionKey selKey = ite.next();
				// 删除已选的key，防止重复处理
				ite.remove();
				handle(selKey);
			}
		}
	}

	/**
	 * 
	 * @desc: 处理请求
	 * @param args
	 * @throws IOException
	 * @return void
	 */
	public void handle(SelectionKey key) throws IOException {
		// 客户端请求连接事件
		if (key.isAcceptable()) {
			handleAccept(key);
		} else if (key.isReadable()) {
			handleRead(key);
		}

	}

	/**
	 * 
	 * @desc: 处理连接
	 * @param args
	 * @throws IOException
	 * @return void
	 */
	public void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
		// 获取和客户端连接的通道
		SocketChannel socketChannel = ssChannel.accept();
		// 设置非阻塞
		socketChannel.configureBlocking(false);
		// 可以给客户端发送消息
		System.out.println("新的客户端发送消息");
		// 和客户端连接成功后，为了接受客户端的消息，需要给通道设置读的权限
		socketChannel.register(this.selector, SelectionKey.OP_READ);
	}

	public void handleRead(SelectionKey key) {
		try {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int readDate = socketChannel.read(byteBuffer);
			if (readDate == -1) {
				System.out.println("客户端关闭");
				key.cancel();
			} else {

				byte[] data = byteBuffer.array();
				String msg = new String(data).trim();
				System.out.println("服务端收到消息:" + msg);

				ByteBuffer outbuffer = ByteBuffer.wrap(msg.getBytes());
				socketChannel.write(outbuffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		NIOServer nIOServer = new NIOServer();
		nIOServer.initServer();
		nIOServer.listen();

	}
}
