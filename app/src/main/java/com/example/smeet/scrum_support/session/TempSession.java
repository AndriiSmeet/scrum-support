package com.example.smeet.scrum_support.session;

/**
 * Created by Smeet on 12.12.2017.
 */

public class TempSession {

    private static Integer sessionId;
    private static Integer storyId;

    public static Integer getSessionId() {
        return sessionId;
    }

    public static void setSessionId(Integer sessionId) {
        TempSession.sessionId = sessionId;
    }

    public static Integer getStoryId() {
        return storyId;
    }

    public static void setStoryId(Integer storyId) {
        TempSession.storyId = storyId;
    }


}
