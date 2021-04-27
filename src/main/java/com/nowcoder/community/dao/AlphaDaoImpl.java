package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;
/*
* Bean默认名是类名的小写，但是也可以自定义，如下
* */

@Repository("alp")
public class AlphaDaoImpl implements AlphaDao{
    @Override
    public String select() {
        return "hah";
    }
}
