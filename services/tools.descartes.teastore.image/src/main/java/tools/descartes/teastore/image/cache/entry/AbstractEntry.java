package tools.descartes.teastore.image.cache.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public abstract class AbstractEntry<D extends ICachable<D>>  implements ICacheEntry<D> {
private D data;

private Logger log = LoggerFactory.getLogger(AbstractEntry.class);

public  AbstractEntry(D data){
if (data == null)
{
log.error("The supplied data is null.");
throw new  NullPointerException("Supplied data is null.");
}
this.data = data;
}
@Override
public  D getData() {
return data;
}

@Override
public abstract  void wasUsed() ;

@Override
public  long getId() {
return data.getId();
}

@Override
public  long getByteSize() {
return data.getByteSize();
}

@Override
public  int hashCode() {
final int prime = 31;
int result = 1;
if (data == null)
{
result = prime * result + 0;
}
else
{
result = prime * result + data.hashCode();
}
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
AbstractEntry<?> other = (AbstractEntry<?>) obj;
if (data == null)
{
if (other.data != null)
{
return false;
}
}
else
if (!data.equals(other.data))
{
return false;
}
return true;
}

}
