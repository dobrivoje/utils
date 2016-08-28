package org.superbapps.utils.common.Enums;

/**
 *
 * @author д06ри
 */
public enum ErrorMessages {

    FIELD_NOT_EMPTY_ERROR_MSG("Polja označena crvenom zvezdicom ne smeju biti prazna.");

    private final String name;

    private ErrorMessages(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
