package com.funs.test.aitest.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;

import com.funs.test.aitest.aidl.Book;
import com.funs.test.aitest.aidl.IBookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2017/7/18.
 */

public class BookService extends Service {
    List<Book> books = new ArrayList<>();
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("BookService onCreate........" + "Thread: " + Thread.currentThread().getName());
        sharedPreferences  = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("BookService onBind");
        return iBookManager;
    }

    IBookManager.Stub iBookManager = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
            System.out.println("============addBook==============>" + book.getBookId());
            sharedPreferences.edit()
                    .putString(book.getBookId() +"",book.getBookId()+"_"+book.getBookName())
                    .apply();
        }

        @Override
        public String getName(String id) throws RemoteException {
            return  sharedPreferences.getString(id," 输入id ");
        }
    };


}
