package com.test.storage.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.test.storage.mapper.StorageMapper;
import com.test.storage.pojo.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageMapper storageMapper;

    @GetMapping("/reduceStorage/{productId}")
    public String reduceStorage(@PathVariable Integer productId) {
        Storage storage = storageMapper.selectOne(new LambdaUpdateWrapper<Storage>().eq(Storage::getProductId, productId));
        storage.setQuantity(storage.getQuantity() - 1);
        int a=10/0;
        storageMapper.updateById(storage);
        return "库存扣减成功";
    }

}

