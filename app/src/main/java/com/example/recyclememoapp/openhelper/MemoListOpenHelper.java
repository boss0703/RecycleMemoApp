package com.example.recyclememoapp.openhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoListOpenHelper extends SQLiteOpenHelper {
    // データベース名
    static final private String DBName = "MEMO_DB";
    // データベースのバージョン(2,3と挙げていくとonUpgradeメソッドが実行される)
    static final private int VERSION = 1;

    // コンストラクタ　以下のように呼ぶこと
    public MemoListOpenHelper(Context context){
        super(context, DBName, null, VERSION);
    }

    // データベースが作成された時に実行される処理
    // データベースはアプリを開いた時に存在しなかったら作成され、すでに存在していれば何もしない
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * テーブルを作成する
         * execSQLメソッドにCREATET TABLE命令を文字列として渡すことで実行される
         * 引数で指定されているものの意味は以下の通り
         * 引数1 ・・・ id：列名 , INTEGER：数値型 主キー制約
         * 引数2 ・・・ title：列名 , TEXT：文字列型
         * 引数3 ・・・ detail：列名 , TEXT：文字列型
         * 引数5 ・・・ priority：列名 , INTEGER：数値型
         * 引数6 ・・・ created_datetime：列名 , TEXT：文字列型
         * 引数7 ・・・ updated_datetime：列名 , TEXT：文字列型
         */
        db.execSQL("CREATE TABLE MEMO_TABLE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "detail TEXT, " +
                "priority INTEGER, " +
                "created_datetime TIMESTAMP DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')), " +
                "updated_datetime TIMESTAMP DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')) )");

    }

    // データベースをバージョンアップした時に実行される処理
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * テーブルを削除する
         */
        db.execSQL("DROP TABLE IF EXISTS MEMO_TABLE");

        // 新しくテーブルを作成する
        onCreate(db);
    }
}
