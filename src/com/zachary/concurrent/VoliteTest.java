/**
* @Title: 用来发送短信验证码
* @author zhangcheng
* @version V1.0
*/
package com.zachary.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: 用来发送短信验证码
 * @author zhangcheng
 * @version V1.0
 */
public class VoliteTest {
public  int inc = 0;
    
    public synchronized void increase() {
        inc++;
    }
    
    public static void main(String[] args) {
        final VoliteTest test = new VoliteTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
        
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
    
    
    static class Test {
        public  int inc = 0;
        Lock lock = new ReentrantLock();
        
        public  void increase() {
            lock.lock();
            try {
                inc++;
            } finally{
                lock.unlock();
            }
        }
        
        public static void main(String[] args) {
            final Test test = new Test();
            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            test.increase();
                    };
                }.start();
            }
            
            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }
    }
    
    static class Test1 {
        public  AtomicInteger inc = new AtomicInteger();
         
        public  void increase() {
            inc.getAndIncrement();
        }
        
        public static void main(String[] args) {
            final Test1 test = new Test1();
            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            test.increase();
                    };
                }.start();
            }
            
            while(Thread.activeCount()>1)  //保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }
    }
}