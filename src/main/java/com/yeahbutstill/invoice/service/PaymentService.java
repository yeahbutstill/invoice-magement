package com.yeahbutstill.invoice.service;

import com.yeahbutstill.invoice.dao.VirtualAccountDao;
import com.yeahbutstill.invoice.entity.PaymentProvider;
import com.yeahbutstill.invoice.entity.VirtualAccount;
import com.yeahbutstill.invoice.exception.VirtualAccountAlreadyPaidException;
import com.yeahbutstill.invoice.exception.VirtualAccountNotFoundException;
import com.yeahbutstill.invoice.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class PaymentService {

    @Autowired
    VirtualAccountDao virtualAccountDao;

    // method yang di throw
    public void pay(PaymentProvider paymentProvider,
                    String companyId,
                    String accountNumber,
                    BigDecimal amount,
                    String reference)
            throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {

        // 1. check apakah VA ada?
        VirtualAccount va = VirtualAccountHelper.checkVaAda(virtualAccountDao, paymentProvider, companyId, accountNumber);

        // 2. check apakah VA sudah lunas atau belum?
        checkVaLunas(paymentProvider, companyId, accountNumber, va);

        // 3. check apakah amount pembayaran > nilai tagihan


        /**
         *
         *
         *
         *
         * jika dari 3 itu kelar maka lanjut menjalankan ini
         * 4. update status VA menjadi lunas
         * 5. update status invoice menjadi lunas
         * 6. insert ke table payment
         * 7. notifikasi
         *
         */
    }

    private void checkVaLunas(PaymentProvider paymentProvider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException("Va [" + companyId +
                    "/" + accountNumber + "-" + paymentProvider.getCode() + "] already paid");
        }
    }


}
