package com.yeahbutstill.invoice.dao;

import com.yeahbutstill.invoice.entity.VirtualAccountConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VirtualAccountConfigurationDao extends PagingAndSortingRepository<VirtualAccountConfiguration, String> {
}
