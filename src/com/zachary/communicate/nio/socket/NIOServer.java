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
 * @author zhang desc :1���߳�ͬʱ�������ͻ���
 */
public class NIOServer {

	private int port = 10102;
	private Selector selector;

	/**
	 * 
	 * @desc: ��ȡһ��serverSocketͨ�ţ����Ը�ͨ����һЩ��ʼ������
	 * @throws IOException
	 * @return void
	 */
	public void initServer() throws IOException {
		// ���serverSocketͨѶ
		ServerSocketChannel sschannel = ServerSocketChannel.open();
		// ����ͨѶΪ������
		sschannel.configureBlocking(false);
		// ͨ����Ӧ��serversocket�󶨵���Ӧport�˿�
		sschannel.socket().bind(new InetSocketAddress(port));
		// ��ȡһ��ͨѶ������
		this.selector = Selector.open();
		// ͨ�Ź�������ͨ���󶨣���δ��ͨ��ע��selectorKey.op_accept�¼�
		// �����¼������selector.select()�᷵�أ������һֱ����
		sschannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	/**
	 * 
	 * @desc: ������ѯ��ʽ����selector���Ƿ�����Ҫ������¼�������У�����д���
	 * @param args
	 * @return void
	 * @throws IOException
	 */
	public void listen() throws IOException {
		System.out.println("���������");
		while (true) {
			// ��ע���¼�����ʱ���������أ�����һֱ����
			selector.select();

			// ��ȡselectorѡ���еĵ�������ѡ�е���Ϊע���¼�
			Iterator<SelectionKey> ite = this.selector.selectedKeys()
					.iterator();
			while (ite.hasNext()) {
				SelectionKey selKey = ite.next();
				// ɾ����ѡ��key����ֹ�ظ�����
				ite.remove();
				handle(selKey);
			}
		}
	}

	/**
	 * 
	 * @desc: ��������
	 * @param args
	 * @throws IOException
	 * @return void
	 */
	public void handle(SelectionKey key) throws IOException {
		// �ͻ������������¼�
		if (key.isAcceptable()) {
			handleAccept(key);
		} else if (key.isReadable()) {
			handleRead(key);
		}

	}

	/**
	 * 
	 * @desc: ��������
	 * @param args
	 * @throws IOException
	 * @return void
	 */
	public void handleAccept(SelectionKey key) throws IOException {
		ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
		// ��ȡ�Ϳͻ������ӵ�ͨ��
		SocketChannel socketChannel = ssChannel.accept();
		// ���÷�����
		socketChannel.configureBlocking(false);
		// ���Ը��ͻ��˷�����Ϣ
		System.out.println("�µĿͻ��˷�����Ϣ");
		// �Ϳͻ������ӳɹ���Ϊ�˽��ܿͻ��˵���Ϣ����Ҫ��ͨ�����ö���Ȩ��
		socketChannel.register(this.selector, SelectionKey.OP_READ);
	}

	public void handleRead(SelectionKey key) {
		try {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int readDate = socketChannel.read(byteBuffer);
			if (readDate == -1) {
				System.out.println("�ͻ��˹ر�");
				key.cancel();
			} else {

				byte[] data = byteBuffer.array();
				String msg = new String(data).trim();
				System.out.println("������յ���Ϣ:" + msg);

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
