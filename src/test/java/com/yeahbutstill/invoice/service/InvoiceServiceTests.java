package com.yeahbutstill.invoice.service;

import com.yeahbutstill.invoice.entity.Invoice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InvoiceServiceTests {

    @Autowired
    private InvoiceService invoiceService;

    @Test
    void testCreateInvoice() {
        Invoice invoice = invoiceService.createInvoice();
    }
}
