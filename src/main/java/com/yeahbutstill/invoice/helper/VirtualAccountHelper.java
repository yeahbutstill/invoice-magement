package com.yeahbutstill.invoice.helper;

import com.yeahbutstill.invoice.dao.VirtualAccountDao;
import com.yeahbutstill.invoice.entity.PaymentProvider;
import com.yeahbutstill.invoice.entity.VirtualAccount;
import com.yeahbutstill.invoice.exception.VirtualAccountNotFoundException;

import java.util.Optional;

public class VirtualAccountHelper {

    public static VirtualAccount checkVaAda(VirtualAccountDao virtualAccountDao, PaymentProvider paymentProvider,
                                            String companyId,
                                            String accountNumber)
            throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa =
                virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(
                        paymentProvider, companyId, accountNumber
                );
        // declaration throw
        if (!optVa.isPresent()) {
            throw new VirtualAccountNotFoundException("Va [" + companyId +
                    "/" + accountNumber + "-" + paymentProvider.getCode() + "] not found");
        }
        VirtualAccount va = optVa.get();
        return va;
    }

}
