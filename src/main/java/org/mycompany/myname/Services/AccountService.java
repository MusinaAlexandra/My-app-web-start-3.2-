package org.mycompany.myname.Services;

import org.mycompany.myname.Models.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, UserProfile> sessionIdToProfile = new HashMap<>();

    public static UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public static void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}