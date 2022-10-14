package es.upsa.ssi.mprest.backend.model.impl.exceptions;

import es.upsa.ssi.mprest.backend.model.impl.SQLConstraint;

import java.sql.SQLException;

public class RdbmsConstraintException extends RdbmsSQLException
{
    private SQLConstraint constraint;

    public RdbmsConstraintException(SQLConstraint constraint, SQLException sqlException)
    {
        super(constraint.getFieldKey(), constraint.getMessageKey(), sqlException);
        this.constraint = constraint;
    }

    public SQLConstraint getConstraint() {
        return constraint;
    }

}
