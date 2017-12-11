package com.example.smeet.scrum_support.dao.impl.sql;

/**
 * Created by Smeet on 05.12.2017.
 */

public final class NumberSql {

    public final static String PARAM_NUMBER_ID = "id";
    public final static String PARAM_NUMBER_VALUE = "value";
    public final static String PARAM_NUMBER_STORY_ID = "story_id_fk";


    public final static String CREATE_NUMBER_QUERY = "INSERT INTO number(value, story_id_fk) " +
            "VALUES(?, ?);";

    public final static String GET_ALL_NUM_ON_SESSION_QUERY = "SELECT * " +
                            "FROM number n " +
                            "WHERE story_id_fk = ?;";

    public final static String UPDATE_NUMBER_QUERY = "UPDATE number " +
            "SET value = ? " +
            "WHERE id = ?;";
}
