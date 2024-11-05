package com.emezon.stock.domain.spi;

public interface IJwtHolder {

    String getToken();

    void setToken(String token);

    void clearToken();
    
}
