package tools.descartes.teastore.registryclient.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tools.descartes.teastore.registryclient.Service;
import tools.descartes.teastore.registryclient.loadbalancers.LoadBalancerTimeoutException;
import tools.descartes.teastore.registryclient.loadbalancers.ServiceLoadBalancer;
import tools.descartes.teastore.registryclient.util.NotFoundException;
import tools.descartes.teastore.entities.ImageSize;
import tools.descartes.teastore.entities.ImageSizePreset;
import tools.descartes.teastore.entities.Product;
public final class LoadBalancedImageOperations {
private  LoadBalancedImageOperations(){
}
public static  String getProductImage(Product product)throws NotFoundException, LoadBalancerTimeoutException {
return getProductImage(product, ImageSizePreset.FULL.getSize());
}

public static  String getProductImage(Product product, ImageSize size)throws NotFoundException, LoadBalancerTimeoutException {
return getProductImages(Stream.of(product).collect(Collectors.toList()), size).getOrDefault(product.getId(), "");
}

public static  HashMap<Long, String> getProductPreviewImages(List<Product> products)throws NotFoundException, LoadBalancerTimeoutException {
return getProductImages(products, ImageSizePreset.PREVIEW.getSize());
}

public static  HashMap<Long, String> getProductImages(List<Product> products, ImageSize size)throws NotFoundException, LoadBalancerTimeoutException {
HashMap<Long, String> img = new  HashMap<>();
for (Product p : products)
{
img.put(p.getId(), size.toString());
}
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.IMAGE, "image", HashMap.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("getProductImages")).post(Entity.entity(img, MediaType.APPLICATION_JSON))));
if (r == null)
{
return new  HashMap<Long, String>();
}
HashMap<Long, String> result = null;
if (r.getStatus() < 400)
{
result = r.readEntity(new  GenericType<HashMap<Long, String>>(){
}
);
}
else
{
r.bufferEntity();
}
if (result == null)
{
return new  HashMap<Long, String>();
}
return result;
}

public static  String getWebImage(String name, ImageSize size)throws NotFoundException, LoadBalancerTimeoutException {
return getWebImages(Stream.of(name).collect(Collectors.toList()), size).getOrDefault(name, "");
}

public static  HashMap<String, String> getWebImages(List<String> names, ImageSize size)throws NotFoundException, LoadBalancerTimeoutException {
HashMap<String, String> img = new  HashMap<>();
for (String name : names)
{
img.put(name, size.toString());
}
Response r = ServiceLoadBalancer.loadBalanceRESTOperation(Service.IMAGE, "image", HashMap.class, client -> ResponseWrapper.wrap(HttpWrapper.wrap(client.getEndpointTarget().path("getWebImages")).post(Entity.entity(img, MediaType.APPLICATION_JSON))));
if (r == null)
{
return new  HashMap<String, String>();
}
HashMap<String, String> result = null;
if (r.getStatus() < 400)
{
result = r.readEntity(new  GenericType<HashMap<String, String>>(){
}
);
}
else
{
r.bufferEntity();
}
if (result == null)
{
return new  HashMap<String, String>();
}
return result;
}

public static  List<Integer> regenerateImages() {
List<Response> r = ServiceLoadBalancer.multicastRESTOperation(Service.IMAGE, "image", null, client -> client.getEndpointTarget().path("regenerateImages").request().get());
if (r == null)
{
return new  ArrayList<Integer>();
}
List<Integer> statuses = r.stream().filter(response -> response != null).map(response -> response.getStatus()).collect(Collectors.toList());
r.stream().filter(response -> response != null).forEach(response -> response.bufferEntity());
return statuses;
}

}
