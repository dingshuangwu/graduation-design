package dingshuangwu.graduation.graduationo2o.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Data
public class CacheObject implements Serializable {
    Object data;
    long createTime;

    public CacheObject(Object data) {
        this.data = data;
        this.createTime = System.currentTimeMillis();
    }
}
