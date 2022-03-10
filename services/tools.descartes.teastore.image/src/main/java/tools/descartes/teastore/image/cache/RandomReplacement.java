package tools.descartes.teastore.image.cache;

import java.util.Random;
import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.rules.CacheAll;
import tools.descartes.teastore.image.storage.IDataStorage;
public class RandomReplacement<T extends ICachable<T>>  extends AbstractQueueCache<T> {
private Random rand = new  Random();

public  RandomReplacement(){
this(IDataCache.STD_MAX_CACHE_SIZE);
}
public  RandomReplacement(long maxCacheSize){
this(maxCacheSize, new  CacheAll<T>());
}
public  RandomReplacement(long maxCacheSize, Predicate<T> cachingRule){
this(null, maxCacheSize, cachingRule);
}
public  RandomReplacement(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
super(cachedStorage, maxCacheSize, cachingRule);
}
public  RandomReplacement(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule, long seed){
super(cachedStorage, maxCacheSize, cachingRule);
setSeed(seed);
}
public  void setSeed(long seed) {
rand.setSeed(seed);
}

@Override
protected  void removeEntryByCachingStrategy() {
dataRemovedFromCache(entries.remove(rand.nextInt(entries.size())).getByteSize());
}

}
