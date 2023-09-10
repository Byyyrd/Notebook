package Helper;

import java.lang.reflect.Array;

public class ArrayHelper {
    public static <T> void arrayCompress(T[] array){
        int nullIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null && nullIndex == -1){
                nullIndex = i;
            }
            if(nullIndex != -1 && i > nullIndex){
                array[i - 1] = array[i];
                array[i] = null;
            }
        }
        array[array.length-1] = null;
    }
    public static <T> T[] arrayAdd(Class<?> clazz,T[] array,T toAdd){
        if (array != null) {
            T[] oldArray = array;
            array = (T[]) Array.newInstance(clazz, oldArray.length + 5);
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
            array[oldArray.length] = toAdd;
        }else {
            array = (T[]) Array.newInstance(clazz,1);
            array[0] = toAdd;
        }
        return array;
    }
}
