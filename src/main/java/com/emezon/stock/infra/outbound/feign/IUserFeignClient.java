package com.emezon.stock.infra.outbound.feign;

import com.emezon.stock.infra.inbound.rest.constants.RestApiConstants;
import com.emezon.stock.infra.outbound.feign.config.FeignConfig;
import com.emezon.stock.infra.outbound.feign.dtos.UserFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "http://localhost:8074",
//        path = RestApiConstants.API_FEIGN_USER,
        configuration = FeignConfig.class
)
public interface IUserFeignClient {

    @GetMapping("/api/v1/user/email/{email}")
    UserFeign findUserByEmail(@PathVariable("email") String email);

}
