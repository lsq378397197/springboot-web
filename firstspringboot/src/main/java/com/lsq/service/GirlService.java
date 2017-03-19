package com.lsq.service;

import com.lsq.domain.Girl;
import com.lsq.enums.ResultEnum;
import com.lsq.exception.GirlException;
import com.lsq.repository.GirlRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-3-19.
 */
@Service
public class GirlService {

    private final static Logger log = LoggerFactory.getLogger(GirlService.class);
    @Autowired
    private GirlRep girlRep;

    @Transactional
    public void insertTwo() {
        Girl A = new Girl();
        A.setCupSize("A");
        A.setAge(18);
        girlRep.save(A);

        Girl B = new Girl();
        A.setCupSize("B");
        A.setAge(19);
        girlRep.save(B);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRep.findOne(id);
        Integer age = girl.getAge();
        log.info("age={}", age);
        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }

    }

    public Girl findOne(int id) {
        return girlRep.findOne(id);
    }
}
