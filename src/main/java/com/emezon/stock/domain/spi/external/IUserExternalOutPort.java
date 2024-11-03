package com.emezon.stock.domain.spi.external;

import com.emezon.stock.domain.models.external.User;

public interface IUserExternalOutPort {

    public User findByEmail(String email);

}
