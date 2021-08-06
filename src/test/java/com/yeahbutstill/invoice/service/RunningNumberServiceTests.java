package com.yeahbutstill.invoice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
public class RunningNumberServiceTests {

    @Autowired
    private RunningNumberService runningNumberService;

    @Test
    void testGetNumber() {

        Long hasil = runningNumberService.getNumber("Test");
        Assertions.assertNotNull(hasil);

    }

    @Test
    void testGetNumberMultiThreaded() throws InterruptedException {
        int jumlahThread = 10;
        final int iterasi = 5;
        ConcurrentHashMap hasilMap = new ConcurrentHashMap();

        for (int i = 0; i < jumlahThread; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Math.abs(new Random().nextInt(100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < iterasi; j++) {
                        List<Long> lastNumbers = (List<Long>) hasilMap.get(this.getId());
                        if (lastNumbers == null) {
                            lastNumbers = new ArrayList<>();
                        }
                        Long hasil1 = runningNumberService.getNumber("Test");
                        System.out.println("Thread [" + this.getId() + "] Last = " + hasil1);
                        Assertions.assertNotNull(hasil1);
                        lastNumbers.add(hasil1);
                        hasilMap.put(this.getId(), lastNumbers);
                    }
                }
            };
            t.start();
        }

        Thread.sleep(10 * 1000);
        Enumeration<Long> keys = hasilMap.keys();
        while (keys.hasMoreElements()) {
            Long key = keys.nextElement();
            System.out.println("====== Thread " + key + " ======");
            System.out.println(hasilMap.get(key));
        }
    }
}
