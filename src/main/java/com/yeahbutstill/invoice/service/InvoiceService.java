package com.yeahbutstill.invoice.service;

import com.yeahbutstill.invoice.entity.Invoice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class InvoiceService {

    public Invoice createInvoice() {
        Invoice invoice = new Invoice();

        return invoice;
    }

}
