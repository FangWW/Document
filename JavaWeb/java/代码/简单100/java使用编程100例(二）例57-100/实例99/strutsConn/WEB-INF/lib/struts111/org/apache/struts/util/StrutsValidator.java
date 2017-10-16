///////////////////////////////////////////////////////////
// DeJaved by mDeJava v1.0. Copyright 1999 MoleSoftware. //
//       To download last version of this software:      //
//            http://molesoftware.hypermatr.net          //
//               e-mail:molesoftware@mail.ru             //
///////////////////////////////////////////////////////////

package org.apache.struts.util;

import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.FieldChecks;

/* This class has attribute 'Deprecated' */

public class StrutsValidator
    implements Serializable
{

    public static final String FIELD_TEST_NULL = "NULL";
    public static final String FIELD_TEST_NOTNULL = "NOTNULL";
    public static final String FIELD_TEST_EQUAL = "EQUAL";

    public StrutsValidator()
    {
    }

    public static boolean validateRequired(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateRequired(bean, va, field, errors, request);
    }

    public static boolean validateMask(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateMask(bean, va, field, errors, request);
    }

    public static Byte validateByte(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateByte(bean, va, field, errors, request);
    }

    public static Short validateShort(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateShort(bean, va, field, errors, request);
    }

    public static Integer validateInteger(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateInteger(bean, va, field, errors, request);
    }

    public static Long validateLong(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateLong(bean, va, field, errors, request);
    }

    public static Float validateFloat(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateFloat(bean, va, field, errors, request);
    }

    public static Double validateDouble(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateDouble(bean, va, field, errors, request);
    }

    public static Date validateDate(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateDate(bean, va, field, errors, request);
    }

    public static boolean validateRange(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateIntRange(bean, va, field, errors, request);
    }

    public static Long validateCreditCard(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateCreditCard(bean, va, field, errors, request);
    }

    public static boolean validateEmail(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateEmail(bean, va, field, errors, request);
    }

    public static boolean validateMaxLength(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateMaxLength(bean, va, field, errors, request);
    }

    public static boolean validateMinLength(Object bean, ValidatorAction va, Field field, ActionErrors errors, HttpServletRequest request)
    {
        return FieldChecks.validateMinLength(bean, va, field, errors, request);
    }
}
