package com.example.thinking.in.spring.ioc.overview.domain;

import com.example.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * @program: thinking-in-sping
 * TODO
 * @author: Yejiaxin
 * @create: 2020-07-20 21:55
 */

@Super
public class SuperUser extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
