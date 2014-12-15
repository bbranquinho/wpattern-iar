package br.com.wpattern.annotation.exception;

public class FormatFieldException extends Exception {

	private static final long serialVersionUID = 8464144343246315472L;

	public FormatFieldException() {
	}

    public FormatFieldException(String s) {
        super(s);
    }

    public FormatFieldException(Throwable throwable) {
        super(throwable);
    }
    
    public FormatFieldException(String s, Throwable throwable) {
        super(s, throwable);
    }
	
}
