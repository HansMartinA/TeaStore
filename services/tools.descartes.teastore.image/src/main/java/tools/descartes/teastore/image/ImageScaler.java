package tools.descartes.teastore.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.descartes.teastore.entities.ImageSize;
public final class ImageScaler {
private static Logger log = LoggerFactory.getLogger(ImageScaler.class);

private  ImageScaler(){
}
public static  BufferedImage scale(BufferedImage image, ImageSize size) {
if (size == null)
{
log.error("The supplied image size is null.");
throw new  NullPointerException("The supplied image size is null.");
}
return scale(image, size.getWidth(), size.getHeight());
}

public static  BufferedImage scale(BufferedImage image, double scalingFactor) {
if (scalingFactor <= 0.0D)
{
log.error("The supplied scaling factor is 0 or below.");
throw new  IllegalArgumentException("The supplied scaling factor is 0 or below.");
}
return scale(image, scalingFactor, scalingFactor);
}

public static  BufferedImage scale(BufferedImage image, double widthScaling, double heightScaling) {
if (widthScaling <= 0.0D)
{
log.error("The supplied width scaling factor is 0 or below.");
throw new  IllegalArgumentException("The supplied width scaling factor is 0 or below.");
}
if (heightScaling <= 0.0D)
{
log.error("The supplied height scaling factor is 0 or below.");
throw new  IllegalArgumentException("The supplied height scaling factor is 0 or below.");
}
int newWidth = (int) (image.getWidth() * widthScaling);
if (newWidth == 0)
{
newWidth = 1;
}
int newHeight = (int) (image.getHeight() * heightScaling);
if (newHeight == 0)
{
newHeight = 1;
}
return scale(image, newWidth, newHeight);
}

public static  BufferedImage scale(BufferedImage image, int size) {
if (size <= 0)
{
log.error("The supplied pixel size is below 1.");
throw new  IllegalArgumentException("The supplied pixel size is below 1.");
}
return scale(image, size, size);
}

public static  BufferedImage scale(BufferedImage image, int width, int height) {
if (image == null)
{
log.error("The supplied image is null.");
throw new  NullPointerException("The supplied image is null.");
}
if (width <= 0)
{
log.error("The supplied pixel width is below 1.");
throw new  IllegalArgumentException("The supplied pixel width is below 1.");
}
if (height <= 0)
{
log.error("The supplied pixel height is below 1.");
throw new  IllegalArgumentException("The supplied pixel height is below 1.");
}
BufferedImage scaledImg = new  BufferedImage(width, height, BufferedImage.TRANSLUCENT);
Graphics2D graphics = scaledImg.createGraphics();
graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
graphics.drawImage(image, 0, 0, width, height, null);
graphics.dispose();
return scaledImg;
}

}
