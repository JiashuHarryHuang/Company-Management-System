package com.service;

import com.mapper.BrandMapper;
import com.pojo.Brand;
import com.pojo.PageBean;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Arrays;
import java.util.List;

public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Brand> selectAll() {
        //获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //调用方法
        mapper.add(brand);
        //提交
        sqlSession.commit();
        //释放
        sqlSession.close();
    }

    public Brand selectById(int id) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    public void update(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    public PageBean<Brand> selectByPage(int begin, int size) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = mapper.selectByPage((begin - 1) * size, size);
        int count = mapper.getCount();

        //Store brands and count into one class
        PageBean<Brand> pageBean = new PageBean<>(count, brands);
        sqlSession.close();
        return pageBean;
    }

    public PageBean<Brand> selectByPageAndCondition(int begin, int size, Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //Calculate the correct index
        int beginIndex = (begin - 1) * size;

        //Add operator for fuzzy query
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        //Call the two sql methods
        List<Brand> brands = mapper.selectByPageAndCondition(beginIndex, size, brand);
        int count = mapper.getCountByCondition(brand);

        //Store data into one class
        PageBean<Brand> pageBean = new PageBean<>(count, brands);
        sqlSession.close();
        return pageBean;
    }
}
