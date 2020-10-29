package com.example.mycontactdemo.utils;

import android.os.Handler;

//4.2  线程
// 定义一个线程类
public class ThreadUtils {
    //4.2.1启动一个普通线程      一个静态方法通过类名调用   多线程要用Runnable接口参数
   public static void runInThread(Runnable task){
       new Thread(task).start();
   }

     //4.2.2启动一个UI主线程 在UI主线程下执行一个子线程task
    public static Handler handler = new Handler();
   //handler手柄，在主线程下边通过handler去执行task，可以完成一些界面的改变
    public static void runInUIThread(Runnable task){
       handler.post(task);

    }



}
