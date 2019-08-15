package com.ls.wedding.dao;

import com.ls.wedding.model.Congratulation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CongratulationDao {

    @Select("select * from congratulation")
    Congratulation findList();

    @Insert("insert into congratulation(open_id, nick_name, head_imgurl, msg, create_time) values(#{open_id}, "
        + "#{nick_name}, #{head_imgurl}, #{msg}, now())")
    void insert(@Param("msg") String msg, @Param("open_id") String openId,
        @Param("nick_name") String nickName,
        @Param("head_imgurl") String headImgUrl);
}
