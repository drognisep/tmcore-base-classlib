/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmcore.data;

/**
 *
 * @author drognisep
 */
public class ObjectInvalidException extends Exception {
  private static final long serialVersionUID = -8627126662454440733L;

  /**
   * Creates a new instance of <code>ObjectInvalidException</code> without
   * detail message.
   */
  public ObjectInvalidException() {
  }

  /**
   * Constructs an instance of <code>ObjectInvalidException</code> with the
   * specified detail message.
   *
   * @param msg the detail message.
   */
  public ObjectInvalidException(String msg) {
    super(msg);
  }
  
  public ObjectInvalidException(ValidatedObject vo) {
    super("Invalid object: " + (vo == null ? "Null ValidatedObject" : 
            vo.toString()));
  }
}
