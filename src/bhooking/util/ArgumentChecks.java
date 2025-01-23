package bhooking.util;

import java.util.Collection;


/**
 * This class assists in validating arguments. The validation methods throw an
 * InvalidArgumentException on every case. Therefore this class should be only used to validate
 * arguments and not states nor other preconditions.
 * <p>
 * 
 * @see https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/Validate.java
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public final class ArgumentChecks {

  private static final String DEFAULT_EXCEPTION_MESSAGE = "Argument validation did not succed.";

  /**
   * Validate that the argument condition is {@code true}; otherwise throwing an exception with the
   * specified message. This method is useful when validating according to an arbitrary boolean
   * expression, such as validating a primitive number or using your own custom validation
   * expression. This method throws an IllegalArgumentException with the default message
   * {@see DEFAULT_EXCEPTION_MESSAGE}.
   * <p>
   * The use of the default message is discouraged and therefore should only be used in very
   * particular locations.
   * 
   * @param expression the boolean expression to check.
   * @throws IllegalArgumentException with DEFAULT_EXCEPTION_MESSAGE if the expression is not true.
   */
  public static void isTrue(final boolean expression) {
    isTrue(expression, DEFAULT_EXCEPTION_MESSAGE);
  }

  /**
   * Validate that the argument condition is {@code true}; otherwise throwing an exception with the
   * specified message. This method is useful when validating according to an arbitrary boolean
   * expression, such as validating a primitive number or using your own custom validation
   * expression. This method throws an IllegalArgumentException with the given message.
   * 
   * @param expression the boolean expression to check.
   * @param message the message to throw within the exception.
   * @throws IllegalArgumentException with the given message if the expression is not true.
   */
  public static void isTrue(final boolean expression, String message) {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }

  /**
   * This method overloads the isTrue method to check that the value of the object is not null. In
   * case the value is null, an IllegalArgumentException is thrown with the message given as
   * parameter.
   * 
   * @param object to check whether is null or not.
   * @param messsage is the message to throw within the exception if the object is null.
   * @throws IllegalArgumentException if the validation is not fulfilled.
   */
  public static void notNull(final Object object, String messsage) {
    isTrue(object != null, messsage);
  }

  /**
   * This method overloads the isTrue method to check that the value of the given string is not
   * empty. In case the value is empty, an IllegalArgumentException is thrown with the message given
   * as parameter.
   * 
   * @param object to check whether is empty or not.
   * @param messsage is the message to throw within the exception if the object is empty.
   * @throws IllegalArgumentException if the validation is not fulfilled.
   */
  public static void notEmpty(final String string, String message) {
    isTrue(!string.isEmpty(), message);
  }

  /**
   * This method overloads the isTrue method to check that the value of the given string is not
   * blank. In case the value is blank, an IllegalArgumentException is thrown with the message given
   * as parameter.
   * 
   * @param object to check whether is blank or not.
   * @param messsage is the message to throw within the exception if the object is blank.
   * @throws IllegalArgumentException if the validation is not fulfilled.
   */
  public static void notBlank(final String string, String message) {
    isTrue(!string.isBlank(), message);
  }

  /**
   * Checks that there is no null object in the given array. To do this it first checks that the
   * array is not null and then that each of the elements is not null either.
   * 
   * @param array to check that does not contain any null element.
   * @param message to throw within the exception if array does contain any null element.
   * @throws IllegalArgumentException if the validation is not fulfilled.
   */
  public static void noNullElements(final Object[] array, final String message) {
    notNull(array, message);
    for (int i = 0; i < array.length; i++) {
      notNull(array[i], message);
    }
  }

  /**
   * Checks that there is no null object in the given collection. To do this it first checks that
   * the collection is not null and then that each of the elements is not null either.
   * 
   * @param collection to check that does not contain any null element.
   * @param message to throw within the exception if collection does contain any null element.
   * @throws IllegalArgumentException if the validation is not fulfilled.
   */
  public static void noNullElements(final Collection<Object> collection, final String message) {
    notNull(collection, message);
    for (Object object : collection) {
      notNull(object, message);
    }
  }

  /**
   * This method checks that the given index is within the range of possible indices of the given
   * array. To do so, the given array must be non-null. If the index is not within the possible
   * ranges then an IllegalArgumentException is thrown.
   * 
   * @param array is the array defining the range of indices to be used to check whether the given
   *        index is valid or not.
   * @param index is the index we want to check if it is within the range of valid indexes defined
   *        by the array.
   * @param message is the message that is thrown together with the exception in case the index is
   *        invalid.
   * @throws IllegalArgumentException if the validation is not fulfilled.
   */
  public static void validIndex(final Object[] array, final int index, final String message) {
    notNull(array, message);
    isTrue(0 <= index && index < array.length, message);
  }

}
