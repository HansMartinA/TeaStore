package tools.descartes.teastore.entities;

public class Order {
private long id;

private long userId;

private String time;

private long totalPriceInCents;

private String addressName;

private String address1;

private String address2;

private String creditCardCompany;

private String creditCardNumber;

private String creditCardExpiryDate;

public  Order(){
}
public  Order(Order order){
setId(order.getId());
setUserId(order.getUserId());
setTime(order.getTime());
setTotalPriceInCents(order.getTotalPriceInCents());
setAddressName(order.getAddressName());
setAddress1(order.getAddress1());
setAddress2(order.getAddress2());
setCreditCardCompany(order.getCreditCardCompany());
setCreditCardNumber(order.getCreditCardNumber());
setCreditCardExpiryDate(order.getCreditCardExpiryDate());
}
public  long getId() {
return id;
}

public  void setId(long id) {
this.id = id;
}

public  long getUserId() {
return userId;
}

public  void setUserId(long userId) {
this.userId = userId;
}

public  String getTime() {
return time;
}

public  void setTime(String time) {
this.time = time;
}

public  long getTotalPriceInCents() {
return totalPriceInCents;
}

public  void setTotalPriceInCents(long totalPriceInCents) {
this.totalPriceInCents = totalPriceInCents;
}

public  String getAddressName() {
return addressName;
}

public  void setAddressName(String addressName) {
this.addressName = addressName;
}

public  String getAddress1() {
return address1;
}

public  void setAddress1(String address1) {
this.address1 = address1;
}

public  String getAddress2() {
return address2;
}

public  void setAddress2(String address2) {
this.address2 = address2;
}

public  String getCreditCardCompany() {
return creditCardCompany;
}

public  void setCreditCardCompany(String creditCardCompany) {
this.creditCardCompany = creditCardCompany;
}

public  String getCreditCardNumber() {
return creditCardNumber;
}

public  void setCreditCardNumber(String creditCardNumber) {
this.creditCardNumber = creditCardNumber;
}

public  String getCreditCardExpiryDate() {
return creditCardExpiryDate;
}

public  void setCreditCardExpiryDate(String creditCardExpiryDate) {
this.creditCardExpiryDate = creditCardExpiryDate;
}

@Override
public  int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + (int) (id ^ (id >>> 32));
result = prime * result + (int) (userId ^ (userId >>> 32));
return result;
}

@Override
public  boolean equals(Object obj) {
if (this == obj)
return true;
if (obj == null)
return false;
if (getClass() != obj.getClass())
return false;
Order other = (Order) obj;
if (id != other.id)
return false;
if (userId != other.userId)
return false;
return true;
}

}
