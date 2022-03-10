package tools.descartes.teastore.image.cache;

import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.storage.IDataStorage;
public interface IDataCache<T extends ICachable<T>>  extends IDataStorage<T> {
public static final long STD_MAX_CACHE_SIZE = 3 * 1024 * 1024;

public  long getMaxCacheSize() ;

public  long getCurrentCacheSize() ;

public  long getFreeSpace() ;

public  boolean hasStorageFor(long size) ;

public  void cacheData(T data) ;

public  void uncacheData(T data) ;

public  boolean dataIsCachable(T data) ;

public  boolean dataIsInCache(long id) ;

public  void clearCache() ;

public  boolean setMaxCacheSize(long cacheSize) ;

}
