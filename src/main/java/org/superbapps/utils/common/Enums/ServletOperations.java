package org.superbapps.utils.common.Enums;

public enum ServletOperations {

    SERVLET_SHIRO_INIT("SERVLET_SESSION_CREATION___SHIRO_ACCESS_CONTROL_INSTANCE"),
    SERVLET_SHIRO_AUTHENTICATED("SERVLET_SESSION_CREATION___SHIRO_AUTHENTICATED"),
    SHIRO_SUBJECT("SHIRO_SESSION_SUBJECT"),
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
