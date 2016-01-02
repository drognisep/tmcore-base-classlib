/*
 * Author: drognisep
 * Version: 0.1
 * 
 * Description: A simple hash function for light-duty password verification
 * 
 * Changelog:
 * *Initial write-up, beginning testing
 * *Testing yielded favorable results, changing compare() to isSame()
 * *Testing complete, changing Value to private and making getters
 * 
 * Future:
 * *Improve hashing function/use known secure algorithm
 */
package tmcore.data;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * TODO LIST
 * Document class
*/

/**
 * A class to generate and compare hashes
 * @author Joseph D. Saylor <josephdsaylor@gmail.com>
 */
public class Validator implements java.io.Serializable, java.lang.Cloneable {
  // yyyymmdd + object number (001)
  private static final long serialVersionUID = 20141002001L;
  
  public static final int SHA256_ARYSZ = 32;
  
  protected byte[] Value;
  
  public Validator() {
    Value = null;
  }
  
  public Validator(String s) {
    this();
    Value = Validator.getSHA256(s);
  }
  
  public Validator(byte[] b) {
    this();
    
    try {
      String s = new String(b, "UTF-8");
      Value = Validator.getSHA256(s);
    } catch(UnsupportedEncodingException uee) {
      System.err.println(uee.getMessage());
      System.exit(1);
    }
  }
  
  public Validator(ValidatedObject vo) {
    this();
    byte[] tmp;
    
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(vo);
      tmp = getSHA256(new String(bos.toByteArray()));
      Value = tmp;
    } catch(IOException ex) {
      System.err.println("[ERROR] Unable to generate validator");
    }
  }
  
  Validator(String s, boolean absolute) {
    try {
      if(absolute) Value = s.getBytes("UTF-8");
      else Value = Validator.getSHA256(s);
    } catch (UnsupportedEncodingException ex) {}
  }
  
  Validator(byte[] b, boolean absolute) {
    this(new String(b), absolute);
  }
  
  /**
   * Computes a SHA-256 hash for the given String
   * @param base String representing data with which to generate a hash
   * @return 
   */
  public static final byte[] getSHA256(String base) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return digest.digest(base.getBytes("UTF-8"));
    }
    catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  public static final byte[] getSHA256(byte[] base) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      return digest.digest(base);
    }
    catch(NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  public void genHash(String s) {
    if(s == null) return;
    Value = Validator.getSHA256(s);
  }
  
  public void genHash(byte[] ba) {
    String s = new String(ba);
    this.genHash(s);
  }
  
  public static Validator genFileHash(String filename) throws NullPointerException,
          IOException {
    if(filename == null) throw new NullPointerException("Null filename");
    
    File f = new File(filename);
    return Validator.genFileHash(f);
  }
  
  public static Validator genFileHash(File f) throws NullPointerException, 
          IOException {
    StringBuilder sb = new StringBuilder();
    DataInputStream dis;
    Validator hash = new Validator();
    int i;
    
    if(f == null) throw new NullPointerException("Null File reference");
    
    if(f.isFile() && f.canRead()) {
      try {
        dis = new DataInputStream(new FileInputStream(f));
        while((i = dis.read()) != -1) {
          sb.append(i & 0xff);
        }
      }
      catch(IOException e) {
        throw new IOException(e.getMessage());
      }
      hash.genHash(sb.toString());
    }
    else {
      Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, 
              "Unable to read file or file is a directory");
      throw new IOException();
    }
    return hash;
  }
  
  public int length() {
    if(Value != null) return Value.length;
    else return 0;
  }
  
  private boolean isSame(Validator hash) {
    if((Value == null || hash.toString() == null) && 
            (!(Value == null && hash.toString() == null))) return false;
    if(this.length() != hash.length()) return false;
    String otherHash = hash.toString();
    
    return toString().equals(otherHash);
  }
  
  @Override
  public String toString() {
    StringBuilder hexString = new StringBuilder();
      
    for(int i=0; i < Value.length; i++) {
      String hex = Integer.toHexString(0xff & Value[i]);
      if(hex.length() == 1) hexString.append('0');
      hexString.append(hex);
    }

    return hexString.toString();
  }
  
  public byte[] toByteArray() {
    if(Value == null || Value.length <= 0) throw new IllegalStateException(
        "Validator.toByteArray(): Validator is uninitialized");
    else {
      byte[] rVal = new byte[Value.length];
      System.arraycopy(Value, 0, rVal, 0, rVal.length);
      return rVal;
    }
  }
  
  public static Validator fromByteArray(byte[] arry) {
    return new Validator(arry, true);
  }
  
  public static Validator fromValidatedObject(ValidatedObject vo) {
    Validator v;
    
    try {
      v = new Validator(vo.toByteArray(), false);
    } catch(IOException ex) {
      //TODO: Append debug entry to system log
      return null;
    }
    
    return v;
  }
  
  @Override
  public Validator clone() throws CloneNotSupportedException {
    Validator clone = (Validator)super.clone();
    if(!clone.isSame(this)) return new Validator(this.toByteArray(), true);
    return clone;
  }
  
  @Override
  public boolean equals(Object o) {
    if(o instanceof Validator) {
      return isSame((Validator)o);
//      byte[] ary = ((Validator)o).toByteArray();
//      byte[] myary = this.toByteArray();
//      
//      if(ary.length != myary.length) return false;
//      for(int i = 0; i < ary.length; ++i) if(ary[i] != myary[i]) return false;
//      return true;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 59 * hash + Arrays.hashCode(this.Value);
    return hash;
  }
}
