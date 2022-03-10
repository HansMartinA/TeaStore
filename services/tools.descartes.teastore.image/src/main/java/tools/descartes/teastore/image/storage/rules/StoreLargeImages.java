package tools.descartes.teastore.image.storage.rules;

import java.util.function.Predicate;
import tools.descartes.teastore.entities.ImageSizePreset;
import tools.descartes.teastore.image.StoreImage;
public class StoreLargeImages implements Predicate<StoreImage> {
@Override
public  boolean test(StoreImage t) {
return t == null ? false : t.getSize().equals(ImageSizePreset.FULL.getSize());
}

}
