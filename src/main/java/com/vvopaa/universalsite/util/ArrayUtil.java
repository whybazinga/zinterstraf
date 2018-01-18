package com.vvopaa.universalsite.util;

import java.util.Collection;
import java.util.Set;

public class ArrayUtil {

    public static boolean isNotEmptySet(Set<?> array) {
        return array != null && !array.isEmpty();
    }

    public static boolean isNotEmptyCollection(Collection<?> array) {
        return array != null && !array.isEmpty();
    }
}
