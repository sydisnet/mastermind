package eu.sydisnet.mastermind.domain.exception;

/**
 * Defines an exception when the user sends a bad input.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class UserInputException extends IllegalArgumentException
{
    /**
     * Defines different error causes.
     */
    public enum ErrorCause
    {
        /**
         * Root Cause: unexpected length in user input.
         */
        INCORRECT_LENGTH
        {
            @Override
            public String toString()
            {
                return "L'entrée saisie n'a pas la bonne longueur ! Quatre lettres doivent être saisies !";
            }
        },

        /**
         * Root cause: the provided color is currently not supported.
         */
        COLOR_NOT_SUPPORTED
        {
            @Override
            public String toString()
            {
                return "Aucune couleur correspondante n'a été trouvée pour une des lettres !";
            }
        }
    }

    /**
     * The corresponding error cause.
     */
    private ErrorCause errorCause;

    public UserInputException(final ErrorCause errorCause)
    {
        super(errorCause.toString());
        this.errorCause = errorCause;
    }

    public UserInputException(final ErrorCause errorCause, final Throwable cause)
    {
        super(errorCause.toString(), cause);
        this.errorCause = errorCause;
    }

    public ErrorCause getErrorCause()
    {
        return this.errorCause;
    }
}
