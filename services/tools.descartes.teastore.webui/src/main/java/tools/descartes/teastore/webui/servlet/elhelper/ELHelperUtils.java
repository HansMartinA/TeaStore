package tools.descartes.teastore.webui.servlet.elhelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public final class ELHelperUtils {
public static final ELHelperUtils UTILS = new  ELHelperUtils();

private static final NumberFormat PRICE_FORMAT = new  DecimalFormat("#0.00");

private  ELHelperUtils(){
}
public  String formatToPrettyDate(String isoFormattedDate) {
return LocalDateTime.parse(isoFormattedDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy MM dd - HH:mm:ss"));
}

public  String formatPriceInCents(long price) {
return "&#36; " + PRICE_FORMAT.format((double) price / 100.0D);
}

}
