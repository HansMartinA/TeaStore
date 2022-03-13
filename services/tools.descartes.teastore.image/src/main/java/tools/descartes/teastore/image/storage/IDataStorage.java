package tools.descartes.teastore.image.storage;

import tools.descartes.teastore.image.cache.entry.ICachable;
public interface IDataStorage<T extends ICachable<T>>  {
public  boolean dataExists(long id) ;

public  T loadData(long id) ;

public  boolean saveData(T data) ;

public  boolean dataIsStorable(T data) ;

public  boolean deleteData(T data) ;

}
