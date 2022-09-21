package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();

    void add(Brand brand);

    void deleteByIds(int[] ids);

    void deleteById(int id);

    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

    void editById(Brand brand);
}
