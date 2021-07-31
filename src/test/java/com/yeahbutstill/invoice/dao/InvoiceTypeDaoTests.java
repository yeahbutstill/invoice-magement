package com.yeahbutstill.invoice.dao;

import com.yeahbutstill.invoice.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"/sql/delete-invoice-type.sql", "/sql/insert-inactive-invoice-type.sql"})
public class InvoiceTypeDaoTests {

    @Autowired
    InvoiceTypeDao invoiceTypeDao;

    @Test
    public void testInsertInvoiceType() throws InterruptedException {

        InvoiceType invoiceType = new InvoiceType();
        invoiceType.setCode("IT-001");
        invoiceType.setName("Invoice Type Test");
        Assertions.assertNull(invoiceType.getId());
        invoiceTypeDao.save(invoiceType);
        Assertions.assertNotNull(invoiceType.getId());
        Assertions.assertNotNull(invoiceType.getCreated());
        Assertions.assertNotNull(invoiceType.getCreatedBy());
        Assertions.assertNotNull(invoiceType.getUpdated());
        Assertions.assertNotNull(invoiceType.getUpdatedBy());
        Assertions.assertEquals(invoiceType.getCreated(), invoiceType.getUpdated());

        Thread.sleep(1000);
        invoiceType.setName("Test Update");
        invoiceType = invoiceTypeDao.save(invoiceType);
        Assertions.assertNotEquals(invoiceType.getCreated(), invoiceType.getUpdated());

    }

    @Test
    void testQuerySoftDelete() {

        Long jumlahRecord = invoiceTypeDao.count();
        Assertions.assertEquals(1, jumlahRecord); // this is true because 1 ACTIVE

    }

    @Test
    void testSoftDelete() {

        InvoiceType invoiceType = invoiceTypeDao.findById("test002").get();
        invoiceTypeDao.delete(invoiceType);

        Long jumlahRecord = invoiceTypeDao.count();
        Assertions.assertEquals(0, jumlahRecord);

    }
}
