package com.example.mycontactdemo.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;
//5.1使用SQLlite建立表结构和数据库
public class ContactOpenHelper extends SQLiteOpenHelper {
   //5.1.1 定义
     //表名常量
    public static final String T_CONTACT = "t_contact";
     //定义联系人表字段  使用内部类 （类里边再定义一个类）。继承接口BaseColumns使用该接口可以默认增加一列 id
    public class ContactTable implements BaseColumns{
          /*一共5个字段  _id,name,phone,email,qq*/
         public  static final String NMAE = "name";
         public  static final String PHONE = "phone";
         public  static final String EMAIL = "email";
         public  static final String QQ = "qq";
     }


      // a.只需要传进来一个上下文对象，其他可以规定好
    public ContactOpenHelper(@Nullable Context context) {
        super(context, "contact.db", null, 1);
    }
    //5.1.2复写构造方法
     //b.创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
     String sql = "CREATE TABLE " + T_CONTACT+" ("
                +ContactTable._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ContactTable.NMAE +" text, "
                +ContactTable.PHONE +" text, "
                +ContactTable.EMAIL +" text, "
                +ContactTable.QQ +" text)";
     //执行sql语句，创建表完成
     db.execSQL(sql);
    }

    //数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
