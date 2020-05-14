package com.atguigu.gmall.sms.api;

import com.atguigu.gmall.sms.vo.SkuSaleVo;
import com.java.core.bean.Resp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface GmallSmsApi {

    @PostMapping("sms/skubounds/sku/sale/save")
    public Resp<Object> saveSale(@RequestBody SkuSaleVo skuSaleVo);

}
