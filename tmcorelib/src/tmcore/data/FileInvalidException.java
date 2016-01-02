package tmcore.data;

/**
 * Indicates that the file loaded/read from by ValidatedObjectStream is not a 
 * valid file. This exception is thrown by an IOException, improperly formatted
 * data, or other file related issues.
 * @author Joseph D. Saylor
 */
public class FileInvalidException extends Exception {

  /**
   * Creates a new instance of <code>FileInvalidException</code> without detail
   * message.
   */
  public FileInvalidException() {
  }

  /**
   * Constructs an instance of <code>FileInvalidException</code> with the
   * specified detail message.
   *
   * @param msg the detail message.
   */
  public FileInvalidException(String msg) {
    super(msg);
  }
}
