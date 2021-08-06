package com.yeahbutstill.invoice.dao;

import com.yeahbutstill.invoice.entity.RunningNumber;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface RunningNumberDao extends CrudRepository<RunningNumber, String> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    RunningNumber findByPrefix(String prefix);

}
