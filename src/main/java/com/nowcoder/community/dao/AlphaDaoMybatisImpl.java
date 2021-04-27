package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary // 主实现类----便于替换，降低耦合度
public class AlphaDaoMybatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "mbatis";
    }
}
