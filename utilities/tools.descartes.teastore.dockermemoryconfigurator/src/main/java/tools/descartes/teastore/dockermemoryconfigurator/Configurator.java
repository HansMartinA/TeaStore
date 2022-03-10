package tools.descartes.teastore.dockermemoryconfigurator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
public final class Configurator {
private static final long DEFAULT_PERCENTAGE = 80;

private static final String CATALINA_SH_PATH = "/usr/local/tomcat/bin/catalina.sh";

private  Configurator(){
}
public static  void main(String[]  args) {
long percentage = readPercentage(args);
long totalkb = readTotalMemoryInKB();
long cgroupkb = readCGroupMemoryInKB();
System.out.println("Total Host Memory = " + totalkb + " KiB");
System.out.println("Container CGroup Limit = " + cgroupkb + " KiB");
if (cgroupkb != 0 && totalkb != 0 && cgroupkb < totalkb)
{
long heapkb = (cgroupkb * percentage) / 100L;
System.out.println("Setting heap space to " + heapkb + " KiB");
modifyCatalinash(heapkb);
}
}

private static  long readPercentage(String[]  args) {
long percentage = DEFAULT_PERCENTAGE;
if (args.length > 0)
{
String arg0 = args[0].trim();
if (!arg0.isEmpty())
{
try {
percentage = Long.parseLong(arg0);
}
catch(NumberFormatException e){
percentage = DEFAULT_PERCENTAGE;
}
}
}
return percentage;
}

private static  long readTotalMemoryInKB() {
File meminfo = new  File("/proc/meminfo");
if (!meminfo.exists())
{
return 0;
}
try(Scanner scan = new  Scanner(meminfo)) {
while (scan.hasNextLine())
{
String line = scan.nextLine().trim();
if (line.startsWith("MemTotal:"))
{
String[]  tokens = line.split(" ");
String kbs = tokens[tokens.length - 2].trim();
try {
return Long.parseLong(kbs);
}
catch(NumberFormatException e){
return 0;
}
}
}
}
catch(IOException e){
return 0;
}
return 0;
}

private static  long readCGroupMemoryInKB() {
File cgroupbytes = new  File("/sys/fs/cgroup/memory/memory.limit_in_bytes");
if (!cgroupbytes.exists())
{
return 0;
}
try(BufferedReader br = new  BufferedReader(new  FileReader(cgroupbytes))) {
try {
double bytes = Double.parseDouble(br.readLine().trim());
return (long) (bytes / 1024.0D);
}
catch(NumberFormatException e){
return 0;
}
}
catch(IOException e){
return 0;
}
}

private static  void modifyCatalinash(long heapkb) {
File catalinaout = new  File(CATALINA_SH_PATH + ".tmp");
File catalinain = new  File(CATALINA_SH_PATH);
try(PrintWriter out = new  PrintWriter(catalinaout, "UTF-8")) {
try(Scanner in = new  Scanner(catalinain)) {
while (in.hasNextLine())
{
String line = in.nextLine();
if (line.contains("CATALINA_OPTS=\""))
{
line = line.replaceFirst("CATALINA_OPTS=\"", "CATALINA_OPTS=\"-Xmx" + heapkb + "k ");
}
out.write(line + "\n");
}
out.flush();
}
}
catch(FileNotFoundException e){
System.out.println("File not found exception: " + e.getMessage());
}
catch(UnsupportedEncodingException e){
System.out.println("UTF-8 unsupported.");
}
if (catalinain.exists() && catalinaout.exists())
{
catalinain.delete();
catalinaout.renameTo(catalinain);
try {
Runtime.getRuntime().exec("chmod +x " + CATALINA_SH_PATH);
}
catch(IOException e){
System.out.println("Cannot chmod " + CATALINA_SH_PATH);
}
}
}

}
