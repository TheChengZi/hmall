package com.hmall.gateway.filters;


import com.hmall.common.exception.UnauthorizedException;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;
    private final JwtTool jwtTool;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取用户信息
        ServerHttpRequest request = exchange.getRequest();

        // 2.判断是否需要做登录拦截
        if(isExclude(request.getPath().toString())){
            return chain.filter(exchange);
        }
        // 3.获取token
        List<String> token = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if(token == null || token.isEmpty()){
            return chain.filter(exchange);
        }
        Long userId;
        // 4.校验并解析 token
        try {
            userId = jwtTool.parseToken(token.get(0));
        }catch (UnauthorizedException e){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        String userInfo = userId.toString();
        // TODO 5.传递用户对象
        ServerWebExchange webExchange = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();
        // 6.放行
        return chain.filter(webExchange);
    }

    private boolean isExclude(String path) {
        List<String> excludePaths = authProperties.getExcludePaths();
        for(String excludePath : excludePaths){
            if (antPathMatcher.match(excludePath, path)){
                return true;
            }
        }
        return false;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
