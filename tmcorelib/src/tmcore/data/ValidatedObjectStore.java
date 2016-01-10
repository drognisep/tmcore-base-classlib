/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmcore.data;

import java.util.HashMap;

/**
 *
 * @author Doug
 */
public abstract class ValidatedObjectStore implements ValidatedObject {
  private static final long serialVersionUID = 730129844301332591L;
  private final HashMap<String, ValidatedObject> storage;
  
  public ValidatedObjectStore() { storage = new HashMap<>(); }
  
  public void addObject(String key, ValidatedObject vo) { 
    storage.put(key, vo);
  }
  
  public ValidatedObject getObject(String key) { 
    return storage.get(key);
  }
}
