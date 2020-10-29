package com.example.mycontactdemo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mycontactdemo.dbhelper.ContactOpenHelper;
import com.example.mycontactdemo.utils.ToastUtils;

//5.2 定义content provider  来实现数据的共享和对数据库进行访问 、增删改查
//5.3 在list view 中读取数据并显示
public class ContactProvider extends ContentProvider {
    //5.2.1定义常量
       //AUTHORITIES保存contact完整路径 ，去清单文件配置provider，登记
    public static  final  String AUTHORITIES = ContactProvider.class.getCanonicalName();
      //定义一个表
    private ContactOpenHelper openHelper;
    //统一资源标识匹配
    static UriMatcher  uriMatcher;
    //标识访问数据库的uri。以后要访问数据库就直接访问这个常量
    public  static final  Uri URI_CONTACT = Uri.parse("content://"+AUTHORITIES+"/contact");
    //
    public static final  int CONTACT = 1;
    //静态块 。不管new多少个对象，只执行一次，完成初始化工作
    static {
      uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
      //添加uri
      uriMatcher.addURI(AUTHORITIES,"/contact",CONTACT);
    }

   //5.2.2复写方法
        //创建表
    @Override
    public boolean onCreate() {
        openHelper = new ContactOpenHelper(getContext());
         if(openHelper !=null)
              return  true;
        return false;
    }
     //查询
    @Nullable
    @Override           //资源目录，       字段、                      where子句                       值
    public Cursor query(@NonNull Uri uri, @Nullable String[] colums, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //匹配uri
        int match = uriMatcher.match(uri);
        //查询返回的记录集
        Cursor cursor = null;
        switch (match){
            case CONTACT:
                //得到数据库对象
               SQLiteDatabase db = openHelper.getReadableDatabase();
                //查询
                cursor = db.query(ContactOpenHelper.T_CONTACT, colums, selection, selectionArgs, null, null, sortOrder);
                Log.i("ContactProvider", "query:success!");
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues cv) {
        //匹配uri
        int match = uriMatcher.match(uri);
        //查询返回的记录集
        switch (match) {
            case CONTACT:
                //得到数据库对象
                SQLiteDatabase db = openHelper.getWritableDatabase();
                //插入，返回id
                long id = db.insert(ContactOpenHelper.T_CONTACT, "", cv);
                //判断是否成功
                if (id != -1) {
                    Log.i("ContactProvider", "insert:success!");
                    //拼接生成新的uri
                    uri = ContentUris.withAppendedId(uri, id);
              //添加成功后，当数据库改变时,发送广播.观察者来接收
                    getContext().getContentResolver().notifyChange(ContactProvider.URI_CONTACT,null);

                }
                break;
            default:
                break;

        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClause, @Nullable String[] whereArgs) {
        //匹配uri
        int match = uriMatcher.match(uri);
        int deleteCount = 0;
        //查询返回的记录集
        switch (match) {
            case CONTACT:
                //得到数据库对象
                SQLiteDatabase db = openHelper.getWritableDatabase();
                //删除，返回id
                long id = db.delete(ContactOpenHelper.T_CONTACT, whereClause, whereArgs);
                //判断是否成功
                if (deleteCount > 0)
                    Log.i("ContactProvider", "delete:success!");
                    getContext().getContentResolver().notifyChange(ContactProvider.URI_CONTACT,null);
                break;
            default:
                break;
        }
        return deleteCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues cv, @Nullable String whereClause, @Nullable String[] whereArgs) {
        //匹配uri
        int match = uriMatcher.match(uri);
        int updateCount = 0;
        //查询返回的记录集
        switch (match) {
            case CONTACT:
                //得到数据库对象
                SQLiteDatabase db = openHelper.getWritableDatabase();
                //删除，返回id
                long id = db.update(ContactOpenHelper.T_CONTACT,cv, whereClause, whereArgs);
                //判断是否成功
                if (updateCount > 0)
                    Log.i("ContactProvider", "update:success!");
                   getContext().getContentResolver().notifyChange(ContactProvider.URI_CONTACT,null);
                break;
            default:
                break;
        }
        return 0;
    }
}
