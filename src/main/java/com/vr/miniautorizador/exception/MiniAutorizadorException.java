package com.vr.miniautorizador.exception;

public class MiniAutorizadorException extends Exception{

    public MiniAutorizadorException(String msg) {
        super(msg);
    }
    
    public MiniAutorizadorException(Throwable t) {
        super(t);
    }

    public MiniAutorizadorException(String msg, Throwable t) {
        super(msg, t);
    }

}
