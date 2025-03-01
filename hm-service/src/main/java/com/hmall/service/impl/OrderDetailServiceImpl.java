package com.hmall.service.impl;

import com.hmall.domain.po.OrderDetail;
import com.hmall.mapper.OrderDetailMapper;
import com.hmall.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
