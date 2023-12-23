package com.dmm.task;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.dmm.task.model.UserModel;

@Mapper
@Component
public interface UserMapper {
    @Insert("INSERT INTO USER(Id, Name, Email)"
            + "VALUES(#{Id}, #{Name}, #{Email})")
    int insert(UserModel model);

    @Select("SELECT * FROM USER")
    List<UserModel> selectAll();
}