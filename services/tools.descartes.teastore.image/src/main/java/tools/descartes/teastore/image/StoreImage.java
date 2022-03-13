package tools.descartes.teastore.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.entities.ImageSize;
import tools.descartes.teastore.image.cache.entry.ICachable;
public class StoreImage implements ICachable<StoreImage> {
public static final String STORE_IMAGE_FORMAT = "png";

public static final String STORE_IMAGE_DATA_STRING = "data:image/" + STORE_IMAGE_FORMAT + ";base64,";

private final long id;

private byte[]  data;

private ImageSize size;

private Logger log = LoggerFactory.getLogger(StoreImage.class);

public  StoreImage(final long id, BufferedImage image, ImageSize size){
if (image == null)
{
log.error("Supplied image is null.");
throw new  NullPointerException("Supplied image is null.");
}
this.id = id;
setImage(image);
setImageSize(size);
}
public  StoreImage(final long id, byte[]  base64, ImageSize size){
if (base64 == null)
{
log.error("Supplied base64 encoded byte array is null.");
throw new  NullPointerException("Supplied base64 encoded byte array is null.");
}
this.id = id;
data = Arrays.copyOf(base64, base64.length);
setImageSize(size);
}
private  void setImageSize(ImageSize size) {
if (size == null)
{
log.error("Supplied image size is null.");
throw new  NullPointerException("Supplied image size is null.");
}
this.size = size;
}

public  StoreImage(StoreImage image){
if (image == null)
{
log.error("Supplied store image is null.");
throw new  NullPointerException("Store image is null.");
}
this.id = image.getId();
this.data = Arrays.copyOf(image.getByteArray(), image.getByteArray().length);
this.size = image.getSize();
}
public  long getId() {
return id;
}

private  void setImage(BufferedImage image) {
ByteArrayOutputStream stream = new  ByteArrayOutputStream();
try {
ImageIO.write(image, STORE_IMAGE_FORMAT, stream);
}
catch(IOException ioException){
log.warn("An IOException occured while trying to write image to a stream.", ioException);
}
data = Base64.getEncoder().encode(stream.toByteArray());
}

public  BufferedImage getImage() {
BufferedImage image = null;
ByteArrayInputStream stream = new  ByteArrayInputStream(Base64.getDecoder().decode(data));
try {
image = ImageIO.read(stream);
}
catch(IOException ioException){
log.warn("An IOException occured while trying to read image from stream.", ioException);
}
return image;
}

public  ImageSize getSize() {
return size;
}

@Override
public  long getByteSize() {
return data.length;
}

public  String getBase64() {
return new  String(data);
}

public  byte[]  getByteArray() {
return data;
}

@Override
public  String toString() {
return STORE_IMAGE_DATA_STRING + getBase64();
}

@Override
public  int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + (int) (id ^ (id >>> 32));
return result;
}

@Override
public  boolean equals(Object obj) {
if (this == obj)
{
return true;
}
if (obj == null)
{
return false;
}
if (getClass() != obj.getClass())
{
return false;
}
StoreImage other = (StoreImage) obj;
if (id != other.getId())
{
return false;
}
return true;
}

}
