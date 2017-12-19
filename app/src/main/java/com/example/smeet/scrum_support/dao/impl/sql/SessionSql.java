package com.example.smeet.scrum_support.dao.impl.sql;

/**
 * Created by Smeet on 03.12.2017.
 */

public final class SessionSql {

    public static final String PARAM_SESSION_ID = "id";
    public static final String PARAM_SESSION_NAME = "session_name";
    public static final String PARAM_SESSION_PASSWORD = "password";
    public static final String PARAM_SESSION_IS_READY = "is_ready";

    public static final String GET_ALL_SESSION_QUERY = "SELECT * " +
            "FROM session;";

    public static final String GET_SESSION_BY_ID_QUERY = "SELECT * " +
            "FROM session " +
            "WHERE id = ?;";

    public static final String UPDATE_SESSION_STATUS_QUERY = "UPDATE session " +
            "SET is_ready = ? " +
            "WHERE id = ?";

    public static final String GET_SESSION_BY_NAME_QUERY =  "SELECT * " +
            "FROM session " +
            "WHERE session_name = ?;";

    public static final String CREATE_SESSION_QUERY = "INSERT INTO session(session_name, password, is_ready) " +
            "VALUES(?, ?, ?);";

    public static final String CONNECT_SESSION_QUERY = "SELECT * " +
            "FROM session " +
            "WHERE session_name = ? " +
            "AND password = ?;";
}
