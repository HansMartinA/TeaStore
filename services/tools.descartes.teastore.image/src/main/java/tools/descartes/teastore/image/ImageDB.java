package tools.descartes.teastore.image;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.entities.ImageSize;
public class ImageDB {
private HashMap<Long, Map<Long, ImageSize>> products = new  HashMap<>();

private HashMap<String, Map<Long, ImageSize>> webui = new  HashMap<>();

private HashMap<Long, ImageSize> sizes = new  HashMap<>();

private final Logger log = LoggerFactory.getLogger(ImageDB.class);

private final ReadWriteLock lock = new  ReentrantReadWriteLock();

public  ImageDB(){
}
public  ImageDB(ImageDB copy){
if (copy == null)
{
log.error("The supplied image database to copy is null.");
throw new  NullPointerException("The supplied image database to copy is null.");
}
this.products = new  HashMap<>(copy.products);
this.webui = new  HashMap<>(copy.webui);
this.sizes = new  HashMap<>(copy.sizes);
}
public  boolean hasImageID(ImageDBKey imageKey, ImageSize imageSize) {
if (imageKey == null)
{
log.error("The supplied image key is null.");
throw new  NullPointerException("The supplied image key is null.");
}
if (imageKey.isProductKey())
{
return hasImageID(imageKey.getProductID(), imageSize);
}
return hasImageID(imageKey.getWebUIName(), imageSize);
}

public  boolean hasImageID(long productID, ImageSize imageSize) {
return findImageID(productID, imageSize, products) != 0;
}

public  boolean hasImageID(String name, ImageSize imageSize) {
return findImageID(name, imageSize, webui) != 0;
}

public  long getImageID(ImageDBKey imageKey, ImageSize imageSize) {
if (imageKey == null)
{
log.error("The supplied image key is null.");
throw new  NullPointerException("The supplied image key is null.");
}
if (imageKey.isProductKey())
{
return getImageID(imageKey.getProductID(), imageSize);
}
return getImageID(imageKey.getWebUIName(), imageSize);
}

public  long getImageID(long productID, ImageSize imageSize) {
return findImageID(productID, imageSize, products);
}

public  long getImageID(String name, ImageSize imageSize) {
return findImageID(name, imageSize, webui);
}

private <K>  long findImageID(K key, ImageSize imageSize, HashMap<K, Map<Long, ImageSize>> db) {
Optional<Map.Entry<Long, ImageSize>> img = null;
lock.readLock().lock();
try {
img = db.getOrDefault(key, new  HashMap<>()).entrySet().stream().filter(t -> t.getValue().equals(imageSize)).findFirst();
}
finally {
lock.readLock().unlock();
}
if (img.isPresent())
{
return img.get().getKey();
}
return 0;
}

public  ImageSize getImageSize(long imageID) {
ImageSize result = null;
lock.readLock().lock();
try {
result = sizes.getOrDefault(imageID, null);
}
finally {
lock.readLock().unlock();
}
return result;
}

public  void setImageMapping(ImageDBKey imageKey, long imageID, ImageSize imageSize) {
if (imageKey == null)
{
log.error("The supplied image key is null.");
throw new  NullPointerException("The supplied image key is null.");
}
if (imageKey.isProductKey())
{
setImageMapping(imageKey.getProductID(), imageID, imageSize);
}
else
{
setImageMapping(imageKey.getWebUIName(), imageID, imageSize);
}
}

public  void setImageMapping(long productID, long imageID, ImageSize imageSize) {
map(productID, imageID, imageSize, products);
}

public  void setImageMapping(String name, long imageID, ImageSize imageSize) {
if (name == null)
{
log.error("The supplied image name is null.");
throw new  NullPointerException("The supplied image name is null.");
}
map(name, imageID, imageSize, webui);
}

private <K>  void map(K key, long imageID, ImageSize imageSize, HashMap<K, Map<Long, ImageSize>> db) {
if (imageSize == null)
{
log.error("Supplied image size is null.");
throw new  NullPointerException("Supplied image size is null.");
}
Map<Long, ImageSize> images = new  HashMap<>();
lock.writeLock().lock();
try {
if (db.containsKey(key))
{
images = db.get(key);
}
images.put(imageID, imageSize);
db.put(key, images);
sizes.put(imageID, imageSize);
}
finally {
lock.writeLock().unlock();
}
}

public  void removeImageMapping(long imageID) {
lock.writeLock().lock();
try {
unmap(imageID, webui);
unmap(imageID, products);
sizes.remove(imageID);
}
finally {
lock.writeLock().unlock();
}
}

private <K>  void unmap(long imageID, HashMap<K, Map<Long, ImageSize>> db) {
Map.Entry<String, Map<Long, ImageSize>> img = webui.entrySet().stream().filter(entry -> entry.getValue().containsKey(imageID)).findFirst().orElse(null);
if (img != null)
{
webui.remove(img.getKey());
}
}

}
