package com.xiaofu.domain.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DemoService.
 *
 * @author Yang-o_o 2020-08-24 15:01
 */
public class Demo implements Serializable {
    private static final long serialVersionUID = -538911355985611624L;

    private Long id;

    private String name;

    public Demo() {
    }

    public Demo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Demo that = (Demo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
