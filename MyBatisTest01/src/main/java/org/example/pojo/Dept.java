package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-06-08 14:00
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;
}
