package com.baoxina.web.po;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baoxina.web.validation.ValidateGroup1;
import com.baoxina.web.validation.ValidateGroup2;

public class Items {
    private Integer id;

    @Size(min=1,max=30,message="{items.name.size.error}",groups={ValidateGroup1.class})
    private String name;

    @NotNull(message="{items.createtime.isNull}",groups={ValidateGroup2.class})
    private Date createtime;

    private Float price;

    private String pic;


    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}