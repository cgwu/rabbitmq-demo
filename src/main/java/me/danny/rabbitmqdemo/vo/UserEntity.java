package me.danny.rabbitmqdemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by test5d on 2018/11/2.
 */
@Data
@AllArgsConstructor
public class UserEntity implements Serializable {
    private int id;
    private String name;
    private String pass;
}
