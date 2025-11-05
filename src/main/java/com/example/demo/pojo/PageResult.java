package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T>
{
    private long total;
    private List<?> data;

    public static PageResult success(long total,List<?> data)
    {
        PageResult pageResult = new PageResult();
        pageResult.total = total;
        pageResult.data = data;
        return pageResult;
    }
    public static PageResult error(String msg)
    {
        PageResult pageResult = new PageResult();
        pageResult.total = 0;
        pageResult.data = null;
        return pageResult;
    }
}
