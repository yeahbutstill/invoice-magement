package com.yeahbutstill.invoice.exception;

// RuntimeException dipakai untuk error yang tidak bisa dihandle oleh yang memanggil (Controller API, Controller WEB,
// ISO8583 handler) contohnya pada query : select one, tapi balikannya > 1 initinya pesan error untuk client
// jangan pakai runtime exception kalau bisnis exception dilempar dari service
// gunakan check exception
public class VirtualAccountAlreadyPaidException extends Exception {

    public VirtualAccountAlreadyPaidException() {
    }

    public VirtualAccountAlreadyPaidException(String message) {
        super(message);
    }
}
