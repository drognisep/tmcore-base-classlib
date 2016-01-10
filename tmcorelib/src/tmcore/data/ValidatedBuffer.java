package tmcore.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
//import ptac.auth.ValidatedObject;
import java.util.Queue;
//import ptac.auth.Validator;

/**
 * A generic {@code ValidatedObject} to hold bulk data
 * @author Doug Saylor
 */
public class ValidatedBuffer implements ValidatedObject, Queue {
  protected ArrayList<Byte> buffer;
  protected Validator val;
  private static final long serialVersionUID = 20150329001L;
  
  public ValidatedBuffer() {
    buffer = new ArrayList<>();
  }
  
  public ValidatedBuffer(byte[] bs) {
    this();
    for(byte b : bs) {
      buffer.add(b);
    }
    updateValidator();
  }
  
  public final void updateValidator() { val = getValidator(); }
  
  public boolean add(byte b) {
    return buffer.add(b);
  }
  
  public boolean add(byte[] bs) {
    for(byte b : bs) {
      if(!(buffer.add(b))) return false;
    }
    return true;
  }
  
  public byte[] getBuffer() {
    Object[] os = buffer.toArray();
    byte[] bs = new byte[os.length];
    for(int i = 0; i < os.length; i++) {
      bs[i] = (Byte)os[i];
    }
    return bs;
  }
  
  public byte[] getBuffer(int size) {
    if(size() < size) {
      throw new IllegalArgumentException("ValidatedBuffer.getBuffer(int): "
          + "size < argument");
    }
    
    byte[] rVal = new byte[size];
    for(int i = 0; i < size; i++) {
      rVal[i] = remove();
    }
    
    return rVal;
  }

  @Override
  public boolean add(Object e) {
    if(e == null) throw new NullPointerException("ValidatedBuffer.add(Object):"
        + " Argument is null");
    if(e instanceof Byte) return buffer.add((Byte)e);
    else if(e instanceof ValidatedObject) {
      try {
        return add(((ValidatedObject)e).toByteArray());
      } catch (IOException ex) {
        return false;
      }
    }
    
    throw new ClassCastException("ValidatedBuffer.add(Object): Argument isn't "
        + "a valid object");
  }

  @Override
  public boolean offer(Object e) {
    return add(e);
  }

  @Override
  public Byte remove() {
    if(buffer.isEmpty()) throw new NoSuchElementException("ValidatedBuffer"
        + ".remove(): Buffer is empty");
    return buffer.remove(0);
  }

  @Override
  public Byte poll() {
    if(buffer.isEmpty()) return null;
    return buffer.remove(0);
  }

  @Override
  public Byte element() {
    if(buffer.isEmpty()) throw new NoSuchElementException("ValidatedBuffer"
        + ".element(): Buffer is empty");
    return buffer.get(0);
  }

  @Override
  public Byte peek() {
    if(buffer.isEmpty()) return null;
    return buffer.get(0);
  }

  @Override
  public int size() {
    return buffer.size();
  }

  @Override
  public boolean isEmpty() {
    return buffer.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return buffer.contains((Byte)o);
  }

  @Override
  public Iterator iterator() {
    return buffer.iterator();
  }

  @Override
  public Object[] toArray() {
    return buffer.toArray();
  }

  @Override
  public Object[] toArray(Object[] ts) {
    return buffer.toArray(ts);
  }

  @Override
  public boolean remove(Object o) {
    return buffer.remove((Byte)o);
  }

  @Override
  public boolean containsAll(Collection clctn) {
    return buffer.containsAll(clctn);
  }

  @Override
  public boolean addAll(Collection clctn) {
    return buffer.addAll(clctn);
  }

  @Override
  public boolean removeAll(Collection clctn) {
    return buffer.removeAll(clctn);
  }

  @Override
  public boolean retainAll(Collection clctn) {
    return buffer.retainAll(clctn);
  }

  @Override
  public void clear() {
    buffer.clear();
  }
}
