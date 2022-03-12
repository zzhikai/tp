package seedu.linkedout.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    /* consider changing from stage to round */
    public static final Prefix PREFIX_STAGE = new Prefix("r/");
    public static final Prefix PREFIX_JOB = new Prefix("j/");
    public static final Prefix PREFIX_SKILL = new Prefix("s/");

}

