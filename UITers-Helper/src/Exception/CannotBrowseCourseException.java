/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author mirushi
 */
public class CannotBrowseCourseException extends Exception {
    public CannotBrowseCourseException(String msg)
    {
        super(msg);
    }
    public CannotBrowseCourseException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
