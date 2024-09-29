package com.test.storage.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.test.storage.mapper.StorageMapper;
import com.test.storage.pojo.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/updatePurchase")
    public void updatePurchase(@RequestBody Map<String,String> updateKafkaMap){
        System.out.println(updateKafkaMap.get("id")+"---"+updateKafkaMap.get("number"));
    }
}

