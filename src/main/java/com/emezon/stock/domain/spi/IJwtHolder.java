package com.emezon.stock.domain.spi;

public interface IJwtHolder {

    public String getToken();

    public void setToken(String token);

    public void clearToken();
    
}
