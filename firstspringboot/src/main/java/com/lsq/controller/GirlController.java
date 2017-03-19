package com.lsq.controller;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.lsq.domain.Girl;
import com.lsq.domain.Result;
import com.lsq.repository.GirlRep;
import com.lsq.service.GirlService;
import com.lsq.utils.RequestUtil;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class GirlController {
    private final static Logger log = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRep girlRep;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     *
     * @return
     */
    @RequestMapping(value = "/girls", method = RequestMethod.GET)
    public List<Girl> getGirlList() {
        return girlRep.findAll();
    }

    /**
     * 添加一个女生
     *
     * @return
     */
    @RequestMapping(value = "/girls", method = RequestMethod.POST)
    public Object addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RequestUtil.fail(1, bindingResult.getFieldError().getDefaultMessage());
        }
        Girl girlA = new Girl();
        girlA.setAge(girl.getAge());
        girlA.setCupSize(girl.getCupSize());
        return RequestUtil.success(girlRep.save(girlA));
    }

    /**
     * 根据Id查询女生
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/girls/{id}", method = RequestMethod.POST)
    public Girl getGirl(@PathVariable("id") Integer id) {
        return girlRep.findOne(id);
    }

    /**
     * 更新女生信息
     *
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @RequestMapping(value = "/girls/{id}", method = RequestMethod.PUT)
    public Girl updateGirl(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRep.save(girl);
    }

    /**
     * 删除ID
     *
     * @param id
     */
    @DeleteMapping(value = "/girls/{id}")
    public void updateGirl(@PathVariable("id") Integer id) {
        girlRep.delete(id);
    }

    @RequestMapping(value = "girls/age/{age}", method = RequestMethod.GET)
    public List<Girl> getGirls(@PathVariable("age") Integer age) {
        return girlRep.findByAge(age);
    }

    /**
     * @PathVariable("id") 这句不能少，否则读不到传过来的值
     * @param id
     * @throws Exception
     */
    @GetMapping("/verify/age/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        log.info("id={}", id);
        girlService.getAge(id);
    }
}
