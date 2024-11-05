package com.emezon.stock.infra.outbound.feign.utils;

import com.emezon.stock.domain.spi.IJwtHolder;
import com.emezon.stock.infra.security.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

    private final IJwtHolder jwtHolder;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String jwtToken = jwtHolder.getToken();
        if (jwtToken != null) {
            requestTemplate.header(
                    SecurityConstants.AUTHORIZATION_HEADER,
                    SecurityConstants.AUTHENTICATION_SCHEME + jwtToken);
        }
    }

}
