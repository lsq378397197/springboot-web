package com.lsq;

import com.lsq.domain.Girl;
import com.lsq.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * Created by Administrator on 2017-3-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {
    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest() {
        Girl girl=girlService.findOne(3);
        Assert.assertEquals(new Integer(15),girl.getAge());
    }
}
