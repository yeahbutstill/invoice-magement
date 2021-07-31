package com.yeahbutstill.invoice.dao;

import com.yeahbutstill.invoice.entity.VirtualAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {
}
