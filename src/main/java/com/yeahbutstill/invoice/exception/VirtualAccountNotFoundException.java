package com.yeahbutstill.invoice.exception;

// checked exception if declaration throw maka methodnya harus trow juga
// supaya wajib dihandle oleh yang panggil
public class VirtualAccountNotFoundException extends Exception {

    public VirtualAccountNotFoundException() {
    }

    public VirtualAccountNotFoundException(String message) {
        super(message);
    }

}
