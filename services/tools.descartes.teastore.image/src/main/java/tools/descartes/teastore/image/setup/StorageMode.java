package tools.descartes.teastore.image.setup;

import java.util.Arrays;
public enum StorageMode {
DRIVE ("Drive"),
;

public static final StorageMode STD_STORAGE_MODE = DRIVE;

private final String strRepresentation;

private  StorageMode(String strRepresentation){
this.strRepresentation = strRepresentation;
}
public  String getStrRepresentation() {
return strRepresentation;
}

public static  StorageMode getStorageModeFromString(String strStorageMode) {
return Arrays.asList(StorageMode.values()).stream().filter(mode -> mode.strRepresentation.equals(strStorageMode)).findFirst().orElse(STD_STORAGE_MODE);
}

}
