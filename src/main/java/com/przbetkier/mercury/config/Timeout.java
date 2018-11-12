package com.przbetkier.mercury.config;

import javax.validation.constraints.Min;

public class Timeout {

    @Min(1)
    private Integer connect;
    @Min(1)
    private Integer read;

    Integer getConnect() {
        return connect;
    }

    public void setConnect(Integer connect) {
        this.connect = connect;
    }

    Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }
}
