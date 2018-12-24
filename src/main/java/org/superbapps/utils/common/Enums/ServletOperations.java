package org.superbapps.utils.common.Enums;

public enum ServletOperations {

    AUTH_CONTROLLER("AUTHENTICATION_CONTROLLER"),
    AUTHENTICATED("AUTHENTICATED"),
    AUTH_SUBJECT("SHIRO_SESSION_SUBJECT"),
    AUTH_USERNAME("SHIRO_SESSION_USERNAME"),
    LOGGED_USER("SHIRO_SESSION_LOGGEDUSER"),
    SERVLET_DESTRUCTION("SERVLET_SESSION_DESTRUCTION");

    private final String name;

    private ServletOperations(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
