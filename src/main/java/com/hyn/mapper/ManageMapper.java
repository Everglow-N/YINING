package com.hyn.mapper;

import com.hyn.pojo.Manage;
import com.hyn.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManageMapper {
    @Select("select * from management where name = #{name} and password=#{password}")
    Manage getManage(Manage manage);
}
