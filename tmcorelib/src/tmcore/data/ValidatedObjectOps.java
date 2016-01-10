package tmcore.data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
//import ptac.core.datastructures.ValidatedBuffer;

/* TODO LIST
 * Add compression [TESTING COMPLETE]
 * Document class [PENDING]
 * Add encryption [POSTPONED]
*/

/**
 * This class handles writing ValidatedObjects to, and reading them from files
 * in a secure manner, rejecting any objects that do not match their 
 * {@code Validator}. This is an attempted safeguard to detect external 
 * changes to important files.
 * @author Doug Saylor <josephdsaylor@gmail.com>
 */
public class ValidatedObjectOps {
  public static final int VBUF_SIZE = 32;
  public static final int COMPRESSION_LVL = 9;
  public static final boolean COMPRESSION_DEBUG = false;
  public static final boolean DEBUG = false;
  
  protected byte[] vBuf;
  protected byte[] oBuf;
  protected ValidatedObject vObj;
  
  public ValidatedObjectOps() {
    vObj = null;
    vBuf = new byte[VBUF_SIZE];
    oBuf = null;
  }
  
  public ValidatedObjectOps(ValidatedObject vo) throws 
      ObjectInvalidException, IOException {
    this();
    
    if(vo == null) throw new IllegalArgumentException(
            "[ERROR] Unable to initialize with null object");
    
    vObj = vo;
    vBuf = Validator.fromByteArray(vo.toByteArray()).toByteArray();
    if(vBuf.length != VBUF_SIZE) {
      System.err.println("ValidatedObjectStream(ValidatedObject): [ERROR] "
          + "vBuf is unexpected size: " + vBuf.length + ", should be "
          + VBUF_SIZE);
      System.exit(1);
    }
    else {
      try {
        oBuf = compress(vo.toByteArray());
      } catch (IOException ex) {
        System.err.println("ValidatedObjectStream(ValidatedObject): [ERROR] "
            + "Unable to read object into internal array");
        resetVOS();
      }
    }
  }
  
  /**
   * Returns a clone of the already loaded ValidatedObject.
   * The object is cloned so that ValidatedObjectOps can retain an initial
 loaded state of the object. Will throw an IllegalStateException if no
 ValidatedObject is loaded.
   * @return cloned ValidatedObject
   */
  public final ValidatedObject getValidatedObject() {
    if(!validState()) throw new IllegalStateException(
        "[ERROR] No ValidatedObject loaded");
    if(!load(vObj)) throw new IllegalStateException(
        "[ERROR] Error refreshing ValidatedObject");
    return ValidatedObject.fromByteArray(decompress(oBuf));
  }
  
  public synchronized void read(String filename) throws ObjectInvalidException,
          FileInvalidException{
    File f = new File(filename);
    if(f == null) throw new FileInvalidException();
    read(f);
  }
  
  public synchronized void read(File f) throws ObjectInvalidException, 
      FileInvalidException {
    if(f == null) throw new IllegalArgumentException("Null argument");
    
    if(!f.exists()) {
      throw new FileInvalidException("ValidatedObjectStream.read(): File"
          + " doesn't exist");
    }
    
    try {
      byte[] tmp = read(new FileInputStream(f));
      
      if(tmp.length > VBUF_SIZE) {
        byte[] oArray = new byte[tmp.length - VBUF_SIZE];
        System.arraycopy(tmp, VBUF_SIZE, oArray, 0, oArray.length);
        if(load(ValidatedObject.fromByteArray(decompress(oArray)))) {
          if(DEBUG) System.out.println("[DEBUG] ValidatedObjectStream.read"
              + "(File): ValidatedObject successfully loaded");
        } else {
          throw new ObjectInvalidException("ValidatedObjectStream.read(File)"
              + ": Failed to load ValidatedObject");
        }
      } else {
        throw new FileInvalidException("ValidatedObjectStream.read(File): "
            + "Invalid file format");
      }
    } catch(IOException ex) {
      throw new FileInvalidException("ValidatedObjectStream.read(File): "
          + "IOException occured: " + ex.getMessage());
    }
  }
  
  protected byte[] read(FileInputStream fis) throws IOException {
    if(fis == null) return null;
    int i = 0;
    boolean closing = false;
    byte[] readBuf = new byte[1024];
    byte[] rtrnBuf;
    ArrayList<Byte> al = new ArrayList<>();
    
    try {
      while(i != -1) {
        i = fis.read(readBuf);
        if(i != -1) {
          for(int j = 0; j < i; j++) {
            al.add(readBuf[j]);
            readBuf[j] = 0;
          }
        }
      }
      closing = true;
      fis.close();
      
      Object[] os = al.toArray();
      rtrnBuf = new byte[os.length];
      for(int j = 0; j < os.length; j++) {
        rtrnBuf[j] = (Byte)os[j];
      }
    } catch (IOException ex) {
      System.err.print("ValidatedObjectStream(FileInputStream): ");
      if(closing)
        System.err.println("[ERROR] Error closing file");
      else {
        System.err.println("[ERROR] Error reading from file");
        try {
          fis.close();
        } catch(IOException ex2) {
          System.err.println("[ERROR] Error closing file");
          throw new IOException(ex.getMessage() + ", " + ex2.getMessage());
        }
      }
      throw ex;
    }
    
    return rtrnBuf;
  }
  
  public synchronized void write(String filename) throws IOException {
    write(new File(filename));
  }
  
  public synchronized void write(File f) throws IOException {
    if(f == null) throw new IllegalArgumentException("Null argument");
    
    if(!validState()) throw new IllegalStateException(
            "No ValidatedObject loaded");
    
    if(!f.exists()) f.createNewFile();
    
    if(DEBUG) System.out.println("[DEBUG] Attempting to write to: " +
        f.getPath());
    
    FileOutputStream fos = new FileOutputStream(f);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    try {
      fos.write(vBuf);
      oos.writeObject(vObj);
      oBuf = bos.toByteArray();
      fos.write(compress(oBuf));
    }
    finally {
      fos.close();
    }
  }
  
  public boolean validState() {
    return !(vObj == null || oBuf == null);
  }
  
  /**
   * Compresses the array of bytes using the standard level of compression, 
   * guaranteed not to alter the original data in any way.
   * @param data Array of bytes to compress
   * @return Returns a new array containing the compressed data
   */
  public static byte[] compress(byte[] data) {
    if(data == null) throw new IllegalArgumentException("Null data");
    if(data.length == 0) return data;
    
    if(COMPRESSION_DEBUG)return data;
    
    byte[] rtrn = new byte[data.length];
    System.arraycopy(data, 0, rtrn, 0, data.length);
    
    // Set input
    Deflater def = new Deflater(COMPRESSION_LVL);
    def.setInput(rtrn);
    def.finish();
    
    // Set loop variables
    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    byte[] readBuffer = new byte[1024];
    int bytesRead;
    
    // Loop through and compress input
    while(!(def.finished())) {
      bytesRead = def.deflate(readBuffer);
      
      if(bytesRead > 0) {
        bao.write(readBuffer, 0, bytesRead);
      }
    }
    
    def.end();
    return bao.toByteArray();
  }
  
  /**
   * Decompresses the given array, guaranteed not to alter the original data.
   * @param data Array of bytes to decompress
   * @return Returns the decompressed data
   */
  public static byte[] decompress(byte[] data) {
    if(data == null) throw new IllegalArgumentException("Null data");
    if(data.length == 0) return data;
    
    if(COMPRESSION_DEBUG)return data;
    
    byte[] rtrn = new byte[data.length];
    System.arraycopy(data, 0, rtrn, 0, data.length);
    
    Inflater inf = new Inflater();
    inf.setInput(rtrn);
    
    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    byte[] readBuffer = new byte[1024];
    int bytesRead = 0;
    
    while(!(inf.finished())) {
      try {
        bytesRead = inf.inflate(readBuffer);
      } catch (DataFormatException ex) {
        throw new IllegalArgumentException("Invalid data format");
      }
      
      if(bytesRead > 0) {
        bao.write(readBuffer, 0, bytesRead);
      }
    }
    
    inf.end();
    return bao.toByteArray();
  }
  
  private void resetVOS() {
    vObj = null;
    vBuf = new byte[VBUF_SIZE];
    oBuf = null;
  }
  
  private void refresh() throws IOException {
    if(!(validState())) throw new IllegalStateException(
      "No ValidatedObject loaded");
    
    oBuf = vObj.toByteArray();
    vBuf = vObj.getValidator().toByteArray();
  }
  
  public final boolean load(ValidatedObject vo)  {
    byte[] oTmp, vTmp;
    ValidatedObject voTmp;
    
    if(vo == null) {
      System.out.println("[ERROR] Unable to initialize with null object");
      return false;
    }
    
    voTmp = vo;
    vTmp = vo.getValidator().toByteArray();
    if(vTmp.length != VBUF_SIZE) {
      System.err.println("ValidatedObjectStream.load(ValidatedObject): [ERROR] "
          + "vBuf is unexpected size: " + vTmp.length + " should be "
          + VBUF_SIZE);
      return false;
    }
    else {
      try {
        oTmp = compress(vo.toByteArray());
      } catch (IOException ex) {
        System.err.println("ValidatedObjectStream.load(ValidatedObject): "
            + "[ERROR] Unable to read object into internal array");
        return false;
      }
    }
    
    vObj = voTmp;
    vBuf = vTmp;
    oBuf = oTmp;
    return true;
  }
  
//  /**
//   * Test method to check for the proper operation of this class. Throws all
//   * {@code Exception}s to be caught by the runtime.
//   * @param args
//   * @throws IOException
//   * @throws ObjectInvalidException
//   * @throws FileInvalidException 
//   * @throws CloneNotSupportedException
//   */
//  public static void main(String[] args) throws IOException, ObjectInvalidException, FileInvalidException, CloneNotSupportedException {
//    String testFileName = "ValidatedObjectOps.FileIO.test.txt";
//    
//    ValidatedObjectOps vos = new ValidatedObjectOps();
//    PassData pData = new PassData();
//    pData.push("Doug", "3141592654");
//    Validator val = pData.getValidator(), val2;
//    
//    vos.load(pData);
//    
//    if(DEBUG) {
//      System.out.println("[DEBUG] Printing ValidatedObjectOps "
//          + "statistics:");
//      System.out.println("  oBuf.length = " + vos.oBuf.length);
//      System.out.println("  vBuf.length = " + vos.vBuf.length);
//      System.out.println("  vObj.toByteArray().length = " +
//          vos.vObj.toByteArray().length);
//      System.out.print("  Compression level: %");
//      System.out.printf("%-1.2f\n", (1-(((float)vos.oBuf.length)/
//          ((float)vos.vObj.toByteArray().length)))*100);
//    }
//    
//    val2 = vos.getValidatedObject().getValidator();
//    if(val.isSame(vos.getValidatedObject().getValidator())) {
//      System.out.println("Validators match");
//      if(((PassData)vos.getValidatedObject()).userExists("Doug"))
//        System.out.println("User 'Doug' exists");
//      else System.out.println("Doug doesn't exist... Sad face. :(");
//    } else {
//      System.out.println("[ERROR] Validators differ!\n"
//          + "Validator 1: " + val + "\n"
//          + "Validator 2: " + val2);
//    }
//    
//    vos.write(testFileName);
//    vos = new ValidatedObjectOps();
//    vos.read(testFileName);
//    
//    if(DEBUG) {
//      System.out.println("[DEBUG] Printing File ValidatedObjectOps "
//          + "statistics:");
//      System.out.println("  oBuf.length = " + vos.oBuf.length);
//      System.out.println("  vBuf.length = " + vos.vBuf.length);
//      System.out.println("  vObj.toByteArray().length = " +
//          vos.vObj.toByteArray().length);
//      System.out.print("  Compression level: %");
//      System.out.printf("%-1.2f\n", (1-(((float)vos.oBuf.length)/
//          ((float)vos.vObj.toByteArray().length)))*100);
//    }
//    
//    val2 = vos.getValidatedObject().getValidator();
//    if(val.isSame(vos.getValidatedObject().getValidator())) {
//      System.out.println("Validators match again");
//      if(((PassData)vos.getValidatedObject()).userExists("Doug"))
//        System.out.println("User 'Doug' exists again");
//      else System.out.println("Doug doesn't exist... Sad face. :(");
//    } else {
//      System.out.println("[ERROR] Validators differ!\n"
//          + "Validator 1: " + val + "\n"
//          + "Validator 2: " + val2);
//    }
//  }
  
  public ValidatedBuffer exportData() {
    ValidatedBuffer vb = new ValidatedBuffer();
    vb.add(vBuf);
    vb.add(oBuf);
    
    return vb;
  }
  
  public ValidatedObjectOps importData(ValidatedBuffer vb) throws 
      ObjectInvalidException {
    byte[] vTmp, oTmp;
    ValidatedObject vo;
    
    if(vb.size() <= VBUF_SIZE) throw new ObjectInvalidException(
        "ValidatedObjectStream.importData(ValidatedBuffer): Buffer is"
            + "improperly formatted");
    
    vTmp = vb.getBuffer(VBUF_SIZE);
    oTmp = decompress(vb.getBuffer());
    vo = ValidatedObject.fromByteArray(oTmp);
    
    if(vo.getValidator().equals(Validator.fromByteArray(vTmp))) {
      if(DEBUG) System.out.println("[DEBUG] Object is loaded and is valid");
      vBuf = vTmp;
      oBuf = oTmp;
      vObj = vo;
    } else {
      throw new ObjectInvalidException("ValidatedObjectStream.importData("
          + "ValidatedBuffer): Object loaded doesn't match validator,"
          + "aborting.");
    }
    return this;
  }
  
  public static byte[] glueArrays(byte[]... arrays) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    
    for(byte[] a : arrays) { 
      try {
        bos.write(a);
      } catch (IOException ex) {
        Logger.getLogger(ValidatedObjectOps.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return bos.toByteArray();
  }
}
