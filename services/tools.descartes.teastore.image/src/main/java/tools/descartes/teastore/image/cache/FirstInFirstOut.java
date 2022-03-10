package tools.descartes.teastore.image.cache;

import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.rules.CacheAll;
import tools.descartes.teastore.image.storage.IDataStorage;
public class FirstInFirstOut<T extends ICachable<T>>  extends AbstractQueueCache<T> {
public  FirstInFirstOut(){
this(IDataCache.STD_MAX_CACHE_SIZE);
}
public  FirstInFirstOut(long maxCacheSize){
this(maxCacheSize, new  CacheAll<T>());
}
public  FirstInFirstOut(long maxCacheSize, Predicate<T> cachingRule){
this(null, maxCacheSize, cachingRule);
}
public  FirstInFirstOut(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
super(cachedStorage, maxCacheSize, cachingRule);
}
@Override
protected  void removeEntryByCachingStrategy() {
dataRemovedFromCache(entries.pollFirst().getData().getByteSize());
}

}
