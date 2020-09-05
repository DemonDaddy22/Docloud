package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    public List<File> getFiles(Integer userId);

    @Insert("INSERT INTO FILES(filename, contenttype, filesize, userId, filedata) VALUES (#{filename}, #{contenttype}, #{filesize}, #{userId}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    public int addFile(File file);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    public File getFileById(Integer fileId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    public int deleteFile(Integer fileId);

    @Select("SELECT FROM FILES WHERE userId = ${userId} AND filename = #{filename} AND filedata = #{filedata}")
    public List<File> getDuplicateFiles(File file);
}
