package com.hmall.api.clients.fallback;

import com.hmall.api.clients.ItemClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;

@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
    @Override
    public ItemClient create(Throwable cause) {
        return new ItemClient() {

            @Override
            public List<ItemDTO> queryItemsByIds(List<Long> ids) {
                log.info("queryItemsByIds:"+cause.getMessage());
                return List.of();
            }

            @Override
            public void deductStock(List<OrderDetailDTO> items) {

            }
        };
    }
}
