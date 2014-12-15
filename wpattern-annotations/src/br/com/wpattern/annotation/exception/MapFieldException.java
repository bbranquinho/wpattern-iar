package br.com.wpattern.annotation.exception;

public class MapFieldException extends Exception {

	private static final long serialVersionUID = -8531171176826142401L;

	public MapFieldException() {
    }

    public MapFieldException(String s) {
        super(s);
    }

    public MapFieldException(Throwable throwable) {
        super(throwable);
    }
    
    public MapFieldException(String s, Throwable throwable) {
        super(s, throwable);
    }
    
}
