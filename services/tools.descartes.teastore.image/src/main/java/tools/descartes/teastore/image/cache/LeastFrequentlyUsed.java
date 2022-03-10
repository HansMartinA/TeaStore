package tools.descartes.teastore.image.cache;

import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.CountedEntry;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.rules.CacheAll;
import tools.descartes.teastore.image.storage.IDataStorage;
public class LeastFrequentlyUsed<T extends ICachable<T>>  extends AbstractTreeCache<T, CountedEntry<T>> {
public  LeastFrequentlyUsed(){
this(IDataCache.STD_MAX_CACHE_SIZE);
}
public  LeastFrequentlyUsed(long maxCacheSize){
this(maxCacheSize, new  CacheAll<T>());
}
public  LeastFrequentlyUsed(long maxCacheSize, Predicate<T> cachingRule){
this(null, maxCacheSize, cachingRule);
}
public  LeastFrequentlyUsed(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
super(cachedStorage, maxCacheSize, cachingRule, (a, b) -> {
if (a.getId() == b.getId())
{
return 0;
}
else
if (a.getUseCount() - b.getUseCount() != 0)
{
return a.getUseCount() - b.getUseCount();
}
else
if (a.getId() < b.getId())
{
return  - 1;
}
else
{
return 1;
}
}
);
}
@Override
protected  CountedEntry<T> createEntry(T data) {
return new  CountedEntry<T>(data);
}

}
