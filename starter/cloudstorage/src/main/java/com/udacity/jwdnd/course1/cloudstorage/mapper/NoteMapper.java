package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    public List<Note> getNotes(Integer userId);

    @Insert("INSERT INTO NOTES(notetitle, notedescription, userId) VALUES (#{notetitle}, #{notedescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    public int addNote(Note note);

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    public Note getNodeById(Integer noteId);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    public int deleteNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteId = #{noteId}")
    public int updateNote(Note note);
}
