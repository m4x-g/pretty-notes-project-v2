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
//        return jdbcTemplate.query("SELECT * FROM notes", noteRowMapper);
        return jdbcTemplate.query("SELECT " +
                "n.user_id AS n_user_id, " +
                "n.title AS n_title, " +
                "n.body AS n_body, " +
                "n.categories AS n_categories, " +
                "u.id AS u_id, " +
                "u.name AS u_name " +
                "FROM notes n LEFT JOIN users u ON n.user_id = u.id", noteRowMapper);
    }
    public List<Note> getUserNotes(long userId){
        RowMapper<Note> noteRowMapper = (resultSet, rowNumber) -> mapNote(resultSet);
        return jdbcTemplate.query("SELECT " +
                "n.user_id AS n_user_id, " +
                "n.title AS n_title, " +
                "n.body AS n_body, " +
                "n.categories AS n_categories, " +
                "u.id AS u_id, " +
                "u.name AS u_name " +
                "FROM notes n LEFT JOIN users u ON n.user_id = u.id WHERE n.user_ID = ?", noteRowMapper, userId);
    }

    private Note mapNote(ResultSet resultSet) throws SQLException{
        Note note = new Note();
        note.setNoteCreatorId(resultSet.getLong("n_user_id"));
        note.setNoteCreator(resultSet.getString("u_name"));
        note.setNoteTitle(resultSet.getString("n_title"));
        note.setNoteBody(resultSet.getString("n_body"));
        Array categories = resultSet.getArray("n_categories");
        String[] categoriesList = (String[]) categories.getArray();
        note.setCategoriesArray(categoriesList);
        return note;
    }

    public void storeNote(Note note){
//        jdbcTemplate.update("INSERT INTO notes (user_id, title, body, categories) VALUES (?, ?, ?, ?::categories)", note.getNoteCreatorId(), note.getNoteTitle(), note.getNoteBody(), note.getCategoriesArray());
//        jdbcTemplate.update("INSERT INTO notes (user_id, title, body, categories) VALUES (?, ?, ?, ?::categories[])", note.getNoteCreatorId(), note.getNoteTitle(), note.getNoteBody(), "{people, orcs,explosions}");
        jdbcTemplate.update("INSERT INTO notes (user_id, title, body, categories) VALUES (?, ?, ?, ?::categories[])", note.getNoteCreatorId(), note.getNoteTitle(), note.getNoteBody(), note.getCategoriesArrayAsString());

//        //debug start
//        for (String category : note.getCategoriesArray()
//             ) {
//            System.out.println("--> " + category);
//        }
//        System.out.println(
//                "jdbcTemplate.update(\"INSERT INTO notes (user_id, title, body, categories) VALUES (?, ?, ?, ?::categories[])\"" +
//                        note.getNoteCreatorId() + ", " +
//                        note.getNoteTitle() + ", " +
//                        note.getNoteBody() +
//                        ", \"{people, orcs,explosions}\", " +
//                        note.getCategoriesArrayAsString());
//        //debug end
    }
}
