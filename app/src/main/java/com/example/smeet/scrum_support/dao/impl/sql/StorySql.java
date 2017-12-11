package com.example.smeet.scrum_support.dao.impl.sql;

/**
 * Created by Smeet on 05.12.2017.
 */

public class StorySql {

    public final static String PARAM_STORY_ID = "id";
    public final static String PARAM_STORY_TITLE = "title";
    public final static String PARAM_STORY_DESCRIBE = "describe";
    public final static String PARAM_STORY_SAVE_DATE = "date_of_save";
    public final static String PARAM_STORY_IS_ACTIVE = "is_active";
    public final static String PARAM_STORY_SESSION_ID = "session_id_fk";

    public final static String SELECT_ALL_STORY = "SELECT * FROM story";

    public final static String SELECT_STORY_BY_ID = "SELECT * FROM story WHERE id =?";

    public final static String CREATE_STORY = "INSERT INTO story(title, describe, is_active, session_id_fk) " +
                        "VALUES(?, ?, ?, ?); ";

    public final static String SELECT_ALL_STORY_BY_ID_SESSION = "SELECT * FROM story WHERE session_id_fk = ?";

    public final static String SELECT_ALL_STORY_BY_TRUE_OR_FALSE = "SELECT * FROM story " +
            "WHERE is_active = ? " +
            "AND session_id_fk = ?;";


}
