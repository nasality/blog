package com.bytedance.blog.util.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequest<T> implements Serializable {
    @ApiModelProperty(value = "页码", required = true)
    private long current;
    @ApiModelProperty(value = "每页有多少数据", required = true)
    private long size;

    /**
     * 封装分页
     * @return 页
     */
    @ApiModelProperty(hidden = true)
    public IPage<T> getPage() {
        return new Page<T>().setCurrent(this.current).setSize(this.size);
    }
}
