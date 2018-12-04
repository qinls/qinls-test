package com.qinlsspringboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/5/13.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class maopao {

    @Test
    public void contextLoads() {
        int[] a = {1, 4, 32, 7};
        int swap = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            System.out.println(i);
            for (int j = i + 1; j < a.length; j++) {
                sum++;
                if (a[j] > a[i]) {

                    swap = a[i];

                    a[i] = a[j];

                    a[j] = swap;

                }
            }
            System.out.println();
        }
        System.out.println(sum);
        System.out.println(Arrays.toString(a));
    }
}
