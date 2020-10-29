package com.example.mycontactdemo.utils;

import android.content.Context;
import android.widget.Toast;

//4.2.3定义一个Toast类 Toast经常使用
public class ToastUtils {
   public  static  void showToastSafe(final Context context,final String str){ //
       ThreadUtils.runInUIThread(new Runnable() {   //new一个Runnable是匿名内部类，当要使用外部参数时，参数要求时finnal类型
           @Override
           public void run() {
               Toast.makeText(context, str, Toast.LENGTH_LONG).show();  //生成土司
           }
       });


   }

}
