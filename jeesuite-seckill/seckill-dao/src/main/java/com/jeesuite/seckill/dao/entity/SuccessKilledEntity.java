package com.jeesuite.seckill.dao.entity;

import com.jeesuite.mybatis.core.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author YUMING
 * @create 2017年02月10日 15:19
 */
@Table(name = "success_killed")
public class SuccessKilledEntity extends BaseEntity {
    @Id
    private long seckillId;
    @Id
    private long userPhone;
    private short state;
    private Date createTime;

    //多对一,因为一件商品在库存中有很多数量，对应的购买明细也有很多。
    private SeckillEntity seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SeckillEntity getSeckill() {
        return seckill;
    }

    public void setSeckill(SeckillEntity seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public Serializable getId() {
        return null;
    }
}