package com.scandihealth.olympicsc.exceptions;

import org.jboss.seam.annotations.ApplicationException;
import org.jboss.seam.annotations.Name;

@ApplicationException(rollback = true)
@Name("missingUserDataException")
public class MissingUserDataException extends RuntimeException {

}
