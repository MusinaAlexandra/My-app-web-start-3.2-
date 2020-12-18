package org.mycompany.myname.Services;

public class DBException extends Exception {
    public DBException(Throwable throwable) {
        super(throwable);
    }
}