package tools.descartes.teastore.entities;

public class User {
private long id;

private String userName;

private String password;

private String realName;

private String email;

public  User(){
}
public  User(User user){
setId(user.getId());
setUserName(user.getUserName());
setPassword(user.getPassword());
setRealName(user.getRealName());
setEmail(user.getEmail());
}
public  long getId() {
return id;
}

public  void setId(long id) {
this.id = id;
}

public  String getUserName() {
return userName;
}

public  void setUserName(String userName) {
this.userName = userName;
}

public  String getPassword() {
return password;
}

public  void setPassword(String password) {
this.password = password;
}

public  String getRealName() {
return realName;
}

public  void setRealName(String realName) {
this.realName = realName;
}

public  String getEmail() {
return email;
}

public  void setEmail(String email) {
this.email = email;
}

@Override
public  int hashCode() {
final int prime = 31;
int result = 1;
result = prime * result + (int) (id ^ (id >>> 32));
if (userName == null)
{
result = prime * result + 0;
}
else
{
result = prime * result + userName.hashCode();
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
User other = (User) obj;
if (id != other.id)
{
return false;
}
if (userName == null)
{
if (other.userName != null)
{
return false;
}
}
else
{
if (!userName.equals(other.userName))
{
return false;
}
}
return true;
}

}
