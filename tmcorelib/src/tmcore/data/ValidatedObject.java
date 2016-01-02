package tmcore.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Used to encapsulate objects into a data package that can be transmitted 
 * across a network or read from file with a reasonable assurance that the 
 * information isn't tampered with. In a network context this adds enough 
 * overhead to store a checksum, but not much more.
 * @author Doug Saylor <josephdsaylor@gmail.com>
 */
public interface ValidatedObject extends java.io.Serializable, 
        java.lang.Cloneable {
  
//  public final Validator getValidator() {
//    return new Validator(this);
//  }
  
//  @Override
//  public ValidatedObject clone() throws CloneNotSupportedException {
//    return (ValidatedObject)super.clone();
//  }
  
//  public default boolean equals(Object obj) {
//    if(obj instanceof ValidatedObject) {
//      ValidatedObject vo = (ValidatedObject)obj;
//      return vo.getValidator().isSame(getValidator());
//    }
//    return false;
//  }
  
//  public default String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("ValidatedObject:");
//    sb.append(new Validator(this).toString());
//    sb.append(":");
//    
//    return sb.toString();
//  }
  
  public default byte[] toByteArray() throws IOException {
    byte[] dataBytes;
    
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(this);
      oos.flush();
      dataBytes = bos.toByteArray();
//      valBytes = Validator.getSHA256(dataBytes);
//      allBytes = new byte[dataBytes.length + valBytes.length];
//      System.arraycopy(valBytes, 0, allBytes, 0, valBytes.length);
//      System.arraycopy(dataBytes, 0, allBytes, valBytes.length, dataBytes.length);
    } catch (IOException ex) {
      dataBytes = new byte[0];
      throw new IOException("ValidatedObject.toByteArray(): [ERROR] Unable "
          + "to convert object to byte array");
    }
    
    return dataBytes;
  }
  
  public static ValidatedObject fromByteArray(byte[] data) {
    if(data.length == 0) throw new IllegalArgumentException("Empty byte "
        + "array");
    ValidatedObject vo;
    
    ByteArrayInputStream bis = new ByteArrayInputStream(data);
    try {
      ObjectInputStream ois = new ObjectInputStream(bis);
      vo = (ValidatedObject) ois.readObject();
    } catch (IOException ex) {
      System.err.println("ValidatedObject.fromByteArray(): Error encountered "
          + "reading object from buffer");
      return null;
    } catch (ClassNotFoundException ex) {
      System.err.println("ValidatedObject.fromByteArray(): Class not found in "
          + "array");
      return null;
    }
    
    return vo;
  }
  
  public default Validator getValidator() {
    Validator v;
    
    try {
      v = Validator.fromByteArray(this.toByteArray());
    } catch (IOException ex) {
      return Validator.fromByteArray(new byte[0]);
    }
    
    return v;
  }

//  @Override
//  public final int hashCode() {
//    int hash = 5;
//    hash = 71 * hash + Objects.hashCode(getValidator());
//    return hash;
//  }
}
