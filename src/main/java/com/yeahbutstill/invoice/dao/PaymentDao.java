package com.yeahbutstill.invoice.dao;

import com.yeahbutstill.invoice.entity.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentDao extends PagingAndSortingRepository<Payment, String> {
}
