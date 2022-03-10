package tools.descartes.teastore.image.cache;

import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.entry.TimedEntry;
import tools.descartes.teastore.image.cache.rules.CacheAll;
import tools.descartes.teastore.image.storage.IDataStorage;
public class LeastRecentlyUsed<T extends ICachable<T>>  extends AbstractTreeCache<T, TimedEntry<T>> {
public  LeastRecentlyUsed(){
this(IDataCache.STD_MAX_CACHE_SIZE);
}
public  LeastRecentlyUsed(long maxCacheSize){
this(maxCacheSize, new  CacheAll<T>());
}
public  LeastRecentlyUsed(long maxCacheSize, Predicate<T> cachingRule){
this(null, maxCacheSize, cachingRule);
}
public  LeastRecentlyUsed(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
super(cachedStorage, maxCacheSize, cachingRule, (a, b) -> {
if (a.getTime() - b.getTime() < 0)
{
return  - 1;
}
else
if (a.getTime() - b.getTime() > 0)
{
return 1;
}
else
if (a.getId() < b.getId())
{
return  - 1;
}
else
if (a.getId() == b.getId())
{
return 0;
}
else
{
return 1;
}
}
);
}
@Override
protected  TimedEntry<T> createEntry(T data) {
return new  TimedEntry<>(data);
}

}
