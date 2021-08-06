package com.yeahbutstill.invoice.service;

import com.yeahbutstill.invoice.exception.VirtualAccountAlreadyPaidException;
import com.yeahbutstill.invoice.exception.VirtualAccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentServiceTests {

    @Autowired
    private PaymentService paymentService;

    @Test
        // karena method test disini bisa langsung throw
        // lain cerita kalau di controller atau di iso8583 itu harus di try/catch
    void testPay() throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        // client code jangan pakai runtime exception kalau bisnis exception dilempar dari service
        paymentService.pay(null, null, null, null, null);
    }
}
