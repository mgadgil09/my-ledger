package com.myledger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyLedgerAppConfig {

    @Value("${myLedger.secret}")
    private String mySecret;

    public String getMySecret() {
        return mySecret;
    }

    public void setMySecret(String mySecret) {
        this.mySecret = mySecret;
    }
}
