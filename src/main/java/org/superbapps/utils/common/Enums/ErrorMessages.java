package org.superbapps.utils.common.Enums;

/**
 *
 * @author д06ри
 */
public enum ErrorMessages {

    FIELD_NOT_EMPTY_ERROR_MSG("Fields indicated by red stars, must be provided.");

    private final String name;

    private ErrorMessages(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
