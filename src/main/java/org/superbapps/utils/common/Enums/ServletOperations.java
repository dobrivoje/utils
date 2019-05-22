package org.superbapps.utils.common.Enums;

public enum ServletOperations {

    AUTH_CONTROLLER("AUTHENTICATION_CONTROLLER"),
    AUTHENTICATED("AUTHENTICATED"),
    AUTH_SUBJECT("SHIRO_SESSION_SUBJECT"),
    AUTH_USERNAME("SHIRO_SESSION_USERNAME"),
    LOGGED_USER("SHIRO_SESSION_LOGGEDUSER"),
    LOGGED_USER_CATERING("USER.CATERING"),
    SERVLET_DESTRUCTION("SERVLET_SESSION_DESTRUCTION"),
    NEW_USER_REGISTRATION("NEW_USER_REGISTRATION");

    private String name;

    private ServletOperations(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
