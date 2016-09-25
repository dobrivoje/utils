package org.superbapps.utils.common.Enums;

/**
 *
 * @author д06ри
 */
public enum Statuses2 {

    STARTED("started"),
    ONGOING("ongoing"),
    EXECUTED("executed"),
    FAILED("failed"),
    UNKNOWN("unknown"),
    //
    ACTIVE("active"),
    PREVIOUS("previous"),
    NOT_VALID("not valid"),
    //
    OK_COLOR("#2dd085"),
    BAD("#222222"),
    IN_PROGRESS_COLOR("#F3A344"),
    UNKNOWN_COLOR("#aa66aa"),
    //
    APPLE_RED("#EE5C56"),
    APPLE_GREEN("#62CB43"),
    APPLE_YELLOW("#F8BD32");

    private final String name;

    private Statuses2(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
