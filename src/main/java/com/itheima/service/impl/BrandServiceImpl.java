package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory sqlSessionFactory=SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands=brandMapper.selectAll();
        sqlSession.close();
        return brands;
    }

    /**
     * 添加数据
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        brandMapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 通过ID数组删除数据
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 通过单个ID删除行
     * @param id
     */
    @Override
    public void deleteById(int id) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        List<Brand> rows = brandMapper.selectByPage((currentPage - 1) * pageSize, pageSize);
        int totalCount=brandMapper.selectTotalCount();
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    /**
     * 条件和分页查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        if(brandName !=null&& brandName !=""){
            brand.setBrandName("%"+brandName+"%");
        }
        if (companyName != null && companyName != "") {
            brand.setCompanyName("%"+companyName+"%");
        }
        List<Brand> rows = brandMapper.selectByPageAndCondition((currentPage - 1) * pageSize, pageSize,brand);
        int totalCount=brandMapper.selectTotalCountByCondition(brand);
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    /**
     * 通过ID编辑行
     * @param brand
     */
    @Override
    public void editById(Brand brand) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        brandMapper.editById(brand);
        sqlSession.commit();
        sqlSession.close();
    }

}
