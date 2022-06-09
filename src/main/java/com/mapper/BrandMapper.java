package com.mapper;

import com.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    //Select all columns
    List<Brand> selectAll();

    //Insert a row
    void add(Brand brand);

    //Select by id
    Brand selectById(int id);

    //Update
    void update(Brand brand);

    //Delete by IDs
    void deleteByIds(@Param("ids") int[] ids);

    //Delete by id
    void deleteById(int id);

    //Select by page
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    //Get size
    int getCount();

    //Select by condition
    List<Brand> selectByPageAndCondition (@Param("begin") int begin,
                                          @Param("size") int size, @Param("brand") Brand brand);

    //Get total count by condition
    int getCountByCondition (Brand brand);
}
