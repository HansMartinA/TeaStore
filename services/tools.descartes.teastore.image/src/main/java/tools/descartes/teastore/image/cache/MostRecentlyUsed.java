package tools.descartes.teastore.image.cache;

import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.entry.TimedEntry;
import tools.descartes.teastore.image.cache.rules.CacheAll;
import tools.descartes.teastore.image.storage.IDataStorage;
public class MostRecentlyUsed<T extends ICachable<T>>  extends AbstractTreeCache<T, TimedEntry<T>> {
public  MostRecentlyUsed(){
this(IDataCache.STD_MAX_CACHE_SIZE);
}
public  MostRecentlyUsed(long maxCacheSize){
this(maxCacheSize, new  CacheAll<T>());
}
public  MostRecentlyUsed(long maxCacheSize, Predicate<T> cachingRule){
this(null, maxCacheSize, cachingRule);
}
public  MostRecentlyUsed(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
super(cachedStorage, maxCacheSize, cachingRule, (a, b) -> a.getTime() - b.getTime() < 0 ? 1 : (a.getTime() - b.getTime() > 0 ?  - 1 : (a.getId() < b.getId() ?  - 1 : (a.getId() == b.getId() ? 0 : 1))));
}
@Override
protected  TimedEntry<T> createEntry(T data) {
return new  TimedEntry<>(data);
}

}
