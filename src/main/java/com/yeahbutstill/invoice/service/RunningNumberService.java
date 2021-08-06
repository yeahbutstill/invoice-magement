package com.yeahbutstill.invoice.service;

import com.yeahbutstill.invoice.dao.RunningNumberDao;
import com.yeahbutstill.invoice.entity.RunningNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RunningNumberService {

    @Autowired
    private RunningNumberDao runningNumberDao;

    public Long getNumber(String prefix) {
        RunningNumber runningNumber = runningNumberDao.findByPrefix(prefix);

        if (runningNumber == null) {
            runningNumber = new RunningNumber();
            runningNumber.setPrefix(prefix);
        }

        runningNumber.setLastNumber(runningNumber.getLastNumber() + 1);
        runningNumberDao.save(runningNumber);

        return runningNumber.getLastNumber();
    }

}
