package com.app.dao;

import com.app.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Note> getAllNotes(){
        RowMapper<Note> noteRowMapper = (resultSet, rowNumber) -> mapNote(resultSet);
        return jdbcTemplate.query("SELECT * FROM notes", noteRowMapper);
    }

    private Note mapNote(ResultSet resultSet) throws SQLException{
        Note note = new Note();
        note.setNoteCreatorId(resultSet.getLong("user_id"));
        note.setNoteTitle(resultSet.getString("title"));
        note.setNoteBody(resultSet.getString("body"));
        Array categories = resultSet.getArray("categories");
        String[] categoriesList = (String[]) categories.getArray();
        note.setCategoriesArray(categoriesList);
        return note;
    }

    public void storeNote(Note note){
//        jdbcTemplate.update("INSERT INTO notes (user_id, title, body, categories) VALUES (?, ?, ?, ?::categories)", note.getNoteCreatorId(), note.getNoteTitle(), note.getNoteBody(), note.getCategoriesArray());
        jdbcTemplate.update("INSERT INTO notes (user_id, title, body) VALUES (?, ?, ?)", note.getNoteCreatorId(), note.getNoteTitle(), note.getNoteBody());
    }
}
