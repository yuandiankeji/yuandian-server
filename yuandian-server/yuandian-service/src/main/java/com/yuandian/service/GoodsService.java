package com.yuandian.service;

import com.yuandian.entity.GoodsPo;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/7
 */

public interface GoodsService {
    GoodsPo getGoodsById(Long id);
    List<GoodsPo> getAllGoods();
}
