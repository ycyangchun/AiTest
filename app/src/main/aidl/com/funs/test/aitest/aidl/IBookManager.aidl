// IBookManager.aidl
package com.funs.test.aitest.aidl;

// Declare any non-default types here with import statements
import com.funs.test.aitest.aidl.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    String getName(in String id);
}
