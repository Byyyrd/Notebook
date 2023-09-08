package Helper;

import java.lang.reflect.Array;

public class ArrayHelper {
    public static <T> void arrayCompress(T[] array){
        int nullIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null){
                nullIndex = i;
            }
            if(nullIndex != -1 && i > nullIndex){
                array[i - 1] = array[i];
            }
        }
        array[array.length-1] = null;
    }
    public static <T> T[] arrayAdd(T[] array,T toAdd){
        if (array != null) {
            T[] altesArray = array;

            array = (T[]) Array.newInstance(array.getClass(), altesArray.length + 5);
            System.arraycopy(altesArray, 0, array, 0, altesArray.length);
            array[altesArray.length] = toAdd;
        }else {
            array = (T[]) Array.newInstance(array.getClass(),1);
            array[0] = toAdd;
        }
        return array;
    }
}
