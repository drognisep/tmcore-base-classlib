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
 * @param <K>
 * @param <V>
 */
public abstract class DataStore<K,V> implements ValidatedObject {
  private static final long serialVersionUID = 730129844301332591L;
  HashMap<K,V> storage;
  
  
}
