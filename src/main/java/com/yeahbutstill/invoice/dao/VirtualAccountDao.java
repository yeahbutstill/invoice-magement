package com.yeahbutstill.invoice.dao;

import com.yeahbutstill.invoice.entity.PaymentProvider;
import com.yeahbutstill.invoice.entity.VirtualAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {
    Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(PaymentProvider paymentProvider, String companyId, String accountNumber);
}

