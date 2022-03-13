package tools.descartes.teastore.image.cache;

import java.util.Collection;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.entry.ICacheEntry;
import tools.descartes.teastore.image.storage.IDataStorage;
import tools.descartes.teastore.image.storage.NoStorage;
public abstract class AbstractCache<S extends Collection<F>, T extends ICachable<T>, F extends ICacheEntry<T>>  implements IDataCache<T> {
private IDataStorage<T> cachedStorage;

private S entries;

private long maxCacheSize;

private long currentCacheSize;

private Predicate<T> cachingRule;

private Logger log = LoggerFactory.getLogger(AbstractCache.class);

private final ReadWriteLock lock = new  ReentrantReadWriteLock();

public  AbstractCache(S entries, IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
if (entries == null)
{
log.error("The provided internal storage object is null.");
throw new  NullPointerException("The provided internal storage object is null.");
}
if (cachingRule == null)
{
log.error("The provided caching rule is null.");
throw new  NullPointerException("The provided caching rule is null.");
}
if (cachedStorage == null)
{
log.info("No underlying disk storage supplied, assuming no data is stored on disk.");
this.cachedStorage = new  NoStorage<T>();
}
else
{
this.cachedStorage = cachedStorage;
}
this.entries = entries;
this.cachingRule = cachingRule;
setMaxCacheSize(maxCacheSize);
}
protected  IDataStorage<T> getCachedStorage() {
return cachedStorage;
}

protected  S getEntries() {
return entries;
}

private  F findInEntries(long id) {
return entries.stream().filter(entry -> entry.getId() == id).findFirst().orElse(null);
}

private  T getData(long id, boolean markUsed) {
F data = null;
if (markUsed)
{
lock.writeLock().lock();
try {
data = findInEntries(id);
if (data != null)
{
reorderAndTag(data);
}
}
finally {
lock.writeLock().unlock();
}
}
else
{
lock.readLock().lock();
try {
data = findInEntries(id);
}
finally {
lock.readLock().unlock();
}
}
if (data != null)
{
return data.getData();
}
return null;
}

@Override
public  long getMaxCacheSize() {
return maxCacheSize;
}

@Override
public  boolean setMaxCacheSize(long maxCacheSize) {
if (maxCacheSize <= 0)
{
log.error("The provided cache size is negative. Must be positive.");
throw new  IllegalArgumentException("The provided cache size is negative. Must be positive.");
}
lock.writeLock().lock();
try {
this.maxCacheSize = maxCacheSize;
while (getFreeSpace() < 0)
{
removeEntryByCachingStrategy();
}
}
finally {
lock.writeLock().unlock();
}
return true;
}

@Override
public  long getCurrentCacheSize() {
long size = 0;
lock.readLock().lock();
try {
size = currentCacheSize;
}
finally {
lock.readLock().unlock();
}
return size;
}

@Override
public  long getFreeSpace() {
return maxCacheSize - getCurrentCacheSize();
}

@Override
public  boolean hasStorageFor(long size) {
return size <= getFreeSpace();
}

@Override
public  void cacheData(T data) {
if (!dataIsCachable(data) || dataIsInCache(data.getId()))
{
return;
}
if (data.getByteSize() > maxCacheSize)
{
return;
}
lock.writeLock().lock();
try {
while (!hasStorageFor(data.getByteSize()))
{
removeEntryByCachingStrategy();
}
addEntry(createEntry(data));
}
finally {
lock.writeLock().unlock();
}
}

@Override
public  void uncacheData(T data) {
lock.writeLock().lock();
try {
if (entries.remove(createEntry(data)))
{
dataRemovedFromCache(data.getByteSize());
}
}
finally {
lock.writeLock().unlock();
}
}

@Override
public  boolean dataIsCachable(T data) {
return cachingRule.test(data);
}

@Override
public  boolean dataIsInCache(long id) {
return getData(id, false) != null;
}

@Override
public  void clearCache() {
lock.writeLock().lock();
try {
entries.clear();
currentCacheSize = 0;
}
finally {
lock.writeLock().unlock();
}
}

@Override
public  boolean dataExists(long id) {
boolean result = false;
lock.readLock().lock();
try {
if (dataIsInCache(id))
{
result = true;
}
else
{
result = cachedStorage.dataExists(id);
}
}
finally {
lock.readLock().unlock();
}
return result;
}

@Override
public  T loadData(long id) {
T entry = getData(id, true);
if (entry == null)
{
entry = cachedStorage.loadData(id);
if (entry == null)
{
return null;
}
cacheData(entry);
}
return entry;
}

@Override
public  boolean saveData(T data) {
if (data == null)
{
return false;
}
cacheData(data);
return cachedStorage.saveData(data);
}

@Override
public  boolean dataIsStorable(T data) {
return cachedStorage.dataIsStorable(data);
}

@Override
public  boolean deleteData(T data) {
uncacheData(data);
return cachedStorage.deleteData(data);
}

protected  void dataRemovedFromCache(long size) {
lock.writeLock().lock();
try {
if (size > currentCacheSize)
{
currentCacheSize = 0;
}
else
{
currentCacheSize -= size;
}
}
finally {
lock.writeLock().unlock();
}
}

protected  void dataAddedToCache(long size) {
lock.writeLock().lock();
try {
currentCacheSize += size;
}
finally {
lock.writeLock().unlock();
}
}

protected abstract  F createEntry(T data) ;

protected  void addEntry(F data) {
if (entries.add(data))
{
dataAddedToCache(data.getByteSize());
}
}

protected abstract  void removeEntryByCachingStrategy() ;

protected  void reorderAndTag(F data) {
data.wasUsed();
}

}
