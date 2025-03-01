package com.hmall.service;

import com.hmall.domain.dto.PayApplyDTO;
import com.hmall.domain.dto.PayOrderFormDTO;
import com.hmall.domain.po.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
