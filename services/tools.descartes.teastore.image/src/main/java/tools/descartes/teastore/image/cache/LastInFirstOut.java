package tools.descartes.teastore.image.cache;

import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.rules.CacheAll;
import tools.descartes.teastore.image.storage.IDataStorage;
public class LastInFirstOut<T extends ICachable<T>>  extends AbstractQueueCache<T> {
public  LastInFirstOut(){
this(IDataCache.STD_MAX_CACHE_SIZE);
}
public  LastInFirstOut(long maxCacheSize){
this(maxCacheSize, new  CacheAll<T>());
}
public  LastInFirstOut(long maxCacheSize, Predicate<T> cachingRule){
this(null, maxCacheSize, cachingRule);
}
public  LastInFirstOut(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
super(cachedStorage, maxCacheSize, cachingRule);
}
@Override
protected  void removeEntryByCachingStrategy() {
dataRemovedFromCache(entries.pollLast().getData().getByteSize());
}

}
