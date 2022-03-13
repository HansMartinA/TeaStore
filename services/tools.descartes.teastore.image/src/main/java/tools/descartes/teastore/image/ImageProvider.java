package tools.descartes.teastore.image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.entities.ImageSize;
import tools.descartes.teastore.entities.ImageSizePreset;
import tools.descartes.teastore.image.setup.ImageIDFactory;
import tools.descartes.teastore.image.storage.IDataStorage;
public enum ImageProvider {
IP ,
;

public static final String IMAGE_NOT_FOUND = "notFound";

private ImageDB db;

private IDataStorage<StoreImage> storage;

private Logger log = LoggerFactory.getLogger(ImageProvider.class);

private  ImageProvider(){
}
public  void setImageDB(ImageDB imgDB) {
db = imgDB;
}

public  void setStorage(IDataStorage<StoreImage> imgStorage) {
storage = imgStorage;
}

public  Map<Long, String> getProductImages(Map<Long, ImageSize> images) {
Map<Long, String> result = new  HashMap<>();
for (Map.Entry<Long, ImageSize> entry : images.entrySet())
{
String imgStr = getImageFor(new  ImageDBKey(entry.getKey()), entry.getValue());
if (imgStr == null)
{
continue;
}
result.put(entry.getKey(), imgStr);
}
return result;
}

public  Map<String, String> getWebUIImages(Map<String, ImageSize> images) {
Map<String, String> result = new  HashMap<>();
for (Map.Entry<String, ImageSize> entry : images.entrySet())
{
String imgStr = getImageFor(new  ImageDBKey(entry.getKey()), entry.getValue());
if (imgStr == null)
{
continue;
}
result.put(entry.getKey(), imgStr);
}
return result;
}

private  StoreImage scaleAndRegisterImg(BufferedImage image, ImageDBKey key, ImageSize size) {
StoreImage storedImg = new  StoreImage(ImageIDFactory.ID.getNextImageID(), ImageScaler.scale(image, size), size);
db.setImageMapping(key, storedImg.getId(), size);
storage.saveData(storedImg);
return storedImg;
}

private  String getImageFor(ImageDBKey key, ImageSize size) {
if (db == null || storage == null)
{
log.warn("Image provider not correctly initialized. Missing image database and storage.");
return null;
}
if (key == null || size == null)
{
log.info("Supplied image key or size are null.");
return null;
}
if (!key.isProductKey() && (key.getWebUIName() == null || key.getWebUIName().isEmpty()))
{
log.info("Supplied image key invalid. Is neither web image nor product image.");
return null;
}
ImageSize stdSize = ImageSizePreset.STD_IMAGE_SIZE;
StoreImage storedImg = null;
long imgID = db.getImageID(key, size);
if (imgID != 0)
{
storedImg = storage.loadData(imgID);
}
if (storedImg == null)
{
storedImg = storage.loadData(db.getImageID(key, stdSize));
if (storedImg != null)
{
storedImg = scaleAndRegisterImg(storedImg.getImage(), key, size);
}
else
{
storedImg = storage.loadData(db.getImageID(IMAGE_NOT_FOUND, size));
if (storedImg == null)
{
storedImg = storage.loadData(db.getImageID(IMAGE_NOT_FOUND, stdSize));
if (storedImg == null)
{
return null;
}
storedImg = scaleAndRegisterImg(storedImg.getImage(), new  ImageDBKey(IMAGE_NOT_FOUND), size);
}
}
}
return storedImg.toString();
}

}
