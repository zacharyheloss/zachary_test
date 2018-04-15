/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 * @desc 
 * ��������м�������Ҫע�⣺(ģ��һ�����Ե����ӣ�����ʱ��Ϊ120���ӣ�30���Ӻ�ſɽ�����ʱ�䵽�ˣ���ѧ����������˿��Խ�����)
		����ʱ��Ϊ120���ӣ�30���Ӻ�ſɽ�����ʼ����������Ծ�ʱ����СӦΪ30����
		�����ܹ���120�����ڽ���Ŀ��������ʵ����Щ��������
		����120������û����ɿ��ԵĿ�������120���ӿ���ʱ�䵽����Ҫ������ǿ�ƽ���
		�����еĿ�������������Ҫ�������̹߳ر�
 */
public class DelayQueueDemo {

	static class exam {

		public static void main(String[] args) throws InterruptedException {
			int studentNumber = 20;
			CountDownLatch countDownLatch = new CountDownLatch(
					studentNumber + 1);
			DelayQueue<Student> students = new DelayQueue<Student>();

			Random random = new Random();
			for (int i = 0; i < studentNumber; i++) {
				students.put(new Student("student" + (i + 1), 30 + random
						.nextInt(120), countDownLatch));
			}

			Thread teacherThread = new Thread(new Teacher(students));
			students.put(new EndExam(students, 120, countDownLatch,
					teacherThread));
			teacherThread.start();
	        countDownLatch.await();
	        System.out.println(" ����ʱ�䵽��ȫ������");  
		}

		static class Student implements Runnable, Delayed {

			private String name;
			private long workTime;
			private long submitTime;
			private boolean isForce = false;
			private CountDownLatch countDownLatch;

			public Student() {
			}

			public Student(String name, long workTime,
					CountDownLatch countDownLatch) {
				this.name = name;
				this.workTime = workTime;
				this.submitTime = TimeUnit.NANOSECONDS.convert(workTime,
						TimeUnit.NANOSECONDS) + System.nanoTime();
				this.countDownLatch = countDownLatch;
			}

			/*
			 * ���� Javadoc��
			 * 
			 * @see java.lang.Comparable#compareTo(java.lang.Object)
			 */
			@Override
			public int compareTo(Delayed o) {
				// TODO Auto-generated method stub
				if (o == null || !(o instanceof Student))
					return 1;
				if (o == this)
					return 0;
				Student s = (Student) o;
				if (this.workTime > s.workTime) {
					return 1;
				} else if (this.workTime == s.workTime) {
					return 0;
				} else {
					return -1;
				}
			}

			/*
			 * ���� Javadoc��
			 * 
			 * @see
			 * java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit
			 * )
			 */
			@Override
			public long getDelay(TimeUnit unit) {
				// TODO Auto-generated method stub
				return unit.convert(submitTime - System.nanoTime(),
						TimeUnit.NANOSECONDS);
			}

			/*
			 * ���� Javadoc��
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				if (isForce) {
					System.out.println(name + " ����, ϣ����ʱ" + workTime + "����"
							+ " ,ʵ����ʱ 120����");
				} else {
					System.out.println(name + " ����, ϣ����ʱ" + workTime + "����"
							+ " ,ʵ����ʱ " + workTime + " ����");
				}
				countDownLatch.countDown();

			}

			/**
			 * @return isForce
			 */
			public boolean isForce() {
				return isForce;
			}

			/**
			 * @param isForce
			 *            Ҫ���õ� isForce
			 */
			public void setForce(boolean isForce) {
				this.isForce = isForce;
			}

		}

		static class Teacher implements Runnable {
			private DelayQueue<Student> students;

			public Teacher(DelayQueue<Student> students) {
				this.students = students;
			}

			/*
			 * ���� Javadoc��
			 * 
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				try {
					System.out.println("test start");
					while (!Thread.interrupted()) {
						students.take().run();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

		}

		static class EndExam extends Student {

			private DelayQueue<Student> students;
			private CountDownLatch countDownLatch;
			private Thread teacherThread;

			public EndExam(DelayQueue<Student> students, long workTime,
					CountDownLatch countDownLatch, Thread teacherThread) {
				super("ǿ���վ�", workTime, countDownLatch);
				this.students = students;
				this.countDownLatch = countDownLatch;
				this.teacherThread = teacherThread;
			}

			@Override
			public void run() {
				// TODO Auto-generated method stub

				teacherThread.interrupt();
				Student tmpStudent;
				for (Iterator<Student> iterator2 = students.iterator(); iterator2
						.hasNext();) {
					tmpStudent = iterator2.next();
					tmpStudent.setForce(true);
					tmpStudent.run();
				}
				countDownLatch.countDown();
			}
		}

	}

}
