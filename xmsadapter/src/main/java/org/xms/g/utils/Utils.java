package org.xms.g.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for Utils
 */
public class Utils {
    private static final Class XMS_BOX = XBox.class;

    private static Map<String, String> map = new HashMap<>();

    private static Map<String, String> mlGMSMap = new HashMap<>();

    private static Map<Class, Constructor> wrapperCache = new ConcurrentHashMap<>();

    private static Map<Class, Method> getZInstCache = new ConcurrentHashMap<>();

    private static final String G = "g";

    private static final String H = "h";

    /**
     * org.xms.g.utils.Utils.mapArray2GH(R[] array, Class<T> cls, boolean isH) transfer map array to GH.<br/>
     *
     * @param array array name
     * @param cls   class name
     * @param isH   is hms or not
     * @param <T>   T generic type
     * @param <R>   R generic type
     * @return array of T type
     */
    public static <T, R> T[] mapArray2GH(R[] array, Class<T> cls, boolean isH) {
        if (null == array) {
            org.xms.g.utils.XmsLog.w("1", "array is null");
            return null;
        }
        T[] result = (T[]) Array.newInstance(cls, array.length);
        for (int i = 0; i < array.length; i++) {
            result[i] = Utils.getInstanceInInterface(array[i], isH);
        }
        String arrayType = array.getClass().getName();
        String resultType = result.getClass().getName();
        org.xms.g.utils.XmsLog.i("2", "array : " + arrayType + " isH : " + isH + " result : " + resultType);
        return result;
    }

    private static class MappedIterator<R, T> implements Iterator<T> {
        Iterator<R> origin;

        Function<R, T> mapper;

        MappedIterator(Iterator<R> origin, Function<R, T> mapper) {
            this.origin = origin;
            this.mapper = mapper;
        }

        @Override
        public boolean hasNext() {
            return origin.hasNext();
        }

        @Override
        public T next() {
            return mapper.apply(origin.next());
        }

        @Override
        public void remove() {
            origin.remove();
        }
    }

    /**
     * org.xms.g.utils.Utils.transformIterable(Iterable<R> iterable, Function<R, T> mapper) trans form Iterable<R> to Iterable<T>.<br/>
     *
     * @param iterable iterable collection
     * @param mapper   instance of Function<R, T>
     * @param <R>      R generic type
     * @param <T>      T generic type
     * @return Iterable<T> collection
     */
    public static <R, T> Iterable<T> transformIterable(Iterable<R> iterable, Function<R, T> mapper) {
        if (iterable == null) {
            org.xms.g.utils.XmsLog.w("1", "iterable is null");
            return null;
        }
        Iterator<T> iter = new MappedIterator<>(iterable.iterator(), mapper);
        String iteratorType = iter == null ? null : iter.getClass().getName();
        org.xms.g.utils.XmsLog.i("1", "iterable : " + iterable.getClass().getName() + " result : " + iteratorType);
        return () -> iter;
    }

    /**
     * org.xms.g.utils.Utils.transformIterator(Iterator<R> iterator, Function<R, T> mapper) transform Iterator.<br/>
     *
     * @param iterator   iterator of R type
     * @param mapper mapper name
     * @param <T>    T generic type
     * @param <R>    R generic type
     * @return Iterator of T type
     */
    public static <R, T> Iterator<T> transformIterator(Iterator<R> iterator, Function<R, T> mapper) {
        if (iterator == null) {
            org.xms.g.utils.XmsLog.w("1", "iterator is null");
            return null;
        }
        Iterator<T> iter = new MappedIterator<>(iterator, mapper);
        String iteratorType = iter == null ? null : iter.getClass().getName();
        org.xms.g.utils.XmsLog.i("1", "iterator : " + iterator.getClass().getName() + " result : " + iteratorType);
        return iter;
    }

    /**
     * org.xms.g.utils.Utils.genericArrayCopy(R[] array, Class<T> type, Function<R, T> mapper) Copy generic array.<br/>
     *
     * @param array  array name
     * @param type   type name
     * @param mapper mapper name
     * @param <T>    T generic type
     * @param <R>    R generic type
     * @return array of T type
     */
    public static <T, R> T[] genericArrayCopy(R[] array, Class<T> type, Function<R, T> mapper) {
        if (array == null) {
            org.xms.g.utils.XmsLog.w("1", "array is null");
            return null;
        }
        T[] arr = (T[]) Array.newInstance(type, array.length);
        for (int i = 0; i < array.length; i++) {
            arr[i] = array[i] == null ? null : mapper.apply(array[i]);
        }
        String typeType = type == null ? null : type.getClass().getName();
        org.xms.g.utils.XmsLog.i("1",
                "array : " + array.getClass().getName() + " type : " + typeType + " result : " + arr.getClass().getName());
        return arr;
    }

    /**
     * org.xms.g.utils.Utils.convertMap(Map<T, V> map, Function<V, K> mapper) convert map from Map<T, V> to Map<T, K>.<br/>
     *
     * @param map    map name
     * @param mapper mapper name
     * @param <K>    K generic type
     * @param <V>    V generic type
     * @param <T>    T generic type
     * @return map with key of T type and value of K type
     */
    public static <K, V, T> Map<T, K> convertMap(Map<T, V> map, Function<V, K> mapper) {
        if (map == null) {
            org.xms.g.utils.XmsLog.w("1", "map is null");
            return null;
        }
        Map<T, K> returnMap = new HashMap<>();
        for (Map.Entry<T, V> entry : map.entrySet()) {
            returnMap.put(entry.getKey(), mapper.apply(map.get(entry.getKey())));
        }
        org.xms.g.utils.XmsLog.i("1",
                "map : " + map.getClass().getName() + " result : " + returnMap.getClass().getName());
        return returnMap;
    }

    /**
     * org.xms.g.utils.Utils.genericArrayCopy(android.util.SparseArray<R> array,Function<R, T> mapper) Copy generic array.<br/>
     *
     * @param array  array name
     * @param mapper mapper name
     * @param <T>    T generic type
     * @param <R>    R generic type
     * @return SparseArray of T type
     */
    public static <T, R> android.util.SparseArray<T> genericArrayCopy(android.util.SparseArray<R> array,
        Function<R, T> mapper) {
        if (array == null) {
            org.xms.g.utils.XmsLog.w("1", "array is null");
            return null;
        }
        android.util.SparseArray<T> arr = new android.util.SparseArray<>(array.size());
        for (int i = 0; i < array.size(); i++) {
            int key = array.keyAt(i);
            arr.put(key, mapper.apply(array.get(key)));
        }
        org.xms.g.utils.XmsLog.i("1",
                "array : " + array.getClass().getName() + " result : " + arr.getClass().getName());
        return arr;
    }

    /**
     * org.xms.g.utils.Utils.mapList(List<R> list, Function<R, T> mapper) Combine the list and the mapper to the mapList.<br/>
     *
     * @param list   list name
     * @param mapper mapper name
     * @param <T>    T generic type
     * @param <R>    R generic type
     * @return List of T type
     */
    public static <T, R> List<T> mapList(List<R> list, Function<R, T> mapper) {
        if (list == null) {
            org.xms.g.utils.XmsLog.i("1", "list is null");
            return null;
        }
        List<T> result = new ArrayList<>(list.size());
        if (list instanceof LinkedList) {
            result = new LinkedList();
        }
        for (R r : list) {
            result.add(mapper.apply(r));
        }
        String resultType = result.getClass().getName();
        org.xms.g.utils.XmsLog.i("2", "list : " + list.getClass().getName() + " result : " + resultType);
        return result;
    }

    /**
     * org.xms.g.utils.Utils.mapList2GH(List<R> list, boolean isH) tranfer from mapList to GH.<br/>
     *
     * @param list list name
     * @param isH  is hms or not
     * @param <T>  T generic type
     * @param <R>  R generic type
     * @return List of T type
     */
    public static <T, R> List<T> mapList2GH(List<R> list, boolean isH) {
        List<T> result = mapList(list, it -> getInstanceInInterface(it, isH));
        String listType = list == null ? null : list.getClass().getName();
        String resultType = result == null ? null : result.getClass().getName();
        org.xms.g.utils.XmsLog.i("1", "list : " + listType + " isH : " + isH + " result : " + resultType);
        return result;
    }

    /**
     * org.xms.g.utils.Utils.mapList2X(List<R> list, boolean isH) tranfer from mapList to X.<br/>
     *
     * @param list list name
     * @param isH  is hms or not
     * @param <T>  T generic type
     * @param <R>  R generic type
     * @return List of T type
     */
    public static <T, R> List<T> mapList2X(List<R> list, boolean isH) {
        List<T> result = isH ? mapList(list, it -> (T) getXmsObjectWithHmsObject(it))
                : mapList(list, it -> (T) getXmsObjectWithGmsObject(it));
        String listType = list == null ? null : list.getClass().getName();
        String resultType = result == null ? null : result.getClass().getName();
        org.xms.g.utils.XmsLog.i("1", "list : " + listType + " isH : " + isH + " result : " + resultType);
        return result;
    }

    /**
     * org.xms.g.utils.Utils.mapCollection(Collection<? extends R> collection, Function<R, T> mapper) get map collection.<br/>
     *
     * @param collection collection name
     * @param mapper     mapper name
     * @param <T>        T generic type
     * @param <R>        R generic type
     * @return Collection of T type
     */
    public static <T, R> Collection<T> mapCollection(Collection<? extends R> collection, Function<R, T> mapper) {
        if (collection == null) {
            org.xms.g.utils.XmsLog.i("0", "collection : null");
            return null;
        }
        String collectionType = collection.getClass().getName();
        Collection<T> result;
        if (collection instanceof Set) {
            int capacity = Math.max((int) ((float) collection.size() / 0.75F) + 1, 16);
            result = new HashSet<>(capacity);
        } else {
            result = new ArrayList<>(collection.size());
        }
        for (R item : collection) {
            result.add(mapper.apply(item));
        }
        String resultType = result.getClass().getName();
        org.xms.g.utils.XmsLog.i("1", "collection : " + collectionType + " result : " + resultType);
        return result;
    }

    /**
     * org.xms.g.utils.Utils.mapCollection2GH(Collection<R> collection, boolean isH) transfer mapCollection to GH.<br/>
     *
     * @param collection collection name
     * @param isH        is hms or not
     * @param <T>        T generic type
     * @param <R>        R generic type
     * @return Collection of T type
     */
    public static <T, R> Collection<T> mapCollection2GH(Collection<R> collection, boolean isH) {
        Collection<T> result = mapCollection(collection, it -> getInstanceInInterface(it, isH));
        String collectionType = collection == null ? null : collection.getClass().getName();
        String resultType = result == null ? null : result.getClass().getName();
        org.xms.g.utils.XmsLog.i("1", "collection : " + collectionType + " isH : " + isH + " result : " + resultType);
        return result;
    }

    /**
     * org.xms.g.utils.Utils.mapCollection2X(Collection<R> collection, boolean isH) transfer mapCollection to X.<br/>
     *
     * @param collection collection name
     * @param isH        is hms or not
     * @param <T>        T generic type
     * @param <R>        R generic type
     * @return Collection of T type
     */
    public static <T, R> Collection<T> mapCollection2X(Collection<R> collection, boolean isH) {
        Collection<T> result = isH ? mapCollection(collection, it -> (T) getXmsObjectWithHmsObject(it))
                : mapCollection(collection, it -> (T) getXmsObjectWithGmsObject(it));
        String collectionType = collection == null ? null : collection.getClass().getName();
        String resultType = result == null ? null : result.getClass().getName();
        org.xms.g.utils.XmsLog.i("1", "collection : " + collectionType + " isH : " + isH + " result : " + resultType);
        return result;
    }

    private static Object transformList2X(Object object, boolean isH, Map<String, String> xmsMap)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (!(object instanceof List)) {
            org.xms.g.utils.XmsLog.i("1", "object is not List");
            return object;
        }
        List result = (List) object.getClass().newInstance();
        for (int i = 0; i < ((List) object).size(); i++) {
            Object o = ((List) object).get(i);
            if (o == null || !xmsMap.containsKey(o.getClass().getCanonicalName())) {
                result.add(o);
                continue;
            }
            String xName = xmsMap.get(o.getClass().getCanonicalName());
            Class clazz = Class.forName(xName);
            result.add(getOrCreateInstance(clazz, o, isH));
        }
        String objectType = object == null ? null : object.getClass().getName();
        String resultType = result == null ? null : result.getClass().getName();
        org.xms.g.utils.XmsLog.i("2", "object : " + objectType + " isH : " + isH + " result : " + resultType);
        return result;
    }

    /**
     * org.xms.g.utils.Utils.getXmsObjectWithGmsObject(Object object) get the xms Object from the gms Object.<br/>
     *
     * @param object gms Object
     * @return xms Object
     */
    public static Object getXmsObjectWithGmsObject(Object object) {
        return getXmsObjectWithGmsObject(object, getXmsMap("NORMAL"));
    }

    public static Object getXmsObjectWithGmsObject(Object object, Map<String, String> xmsMap) {
        if (object == null) {
            return null;
        }
        if (object instanceof List) {
            try {
                return transformList2X(object, false, xmsMap);
            } catch (IllegalAccessException e) {
                org.xms.g.utils.XmsLog.e("2", e.getMessage(), e);
            } catch (InstantiationException e) {
                org.xms.g.utils.XmsLog.e("2", e.getMessage(), e);
            } catch (ClassNotFoundException e) {
                org.xms.g.utils.XmsLog.e("2", e.getMessage(), e);
            }
        }
        if (!isGmsType(object)) {
            return object;
        }
        org.xms.g.utils.XmsLog.i("1", "inObject : " + object.getClass().getName());
        return getXmsObject(object, G, xmsMap);
    }

    /**
     * org.xms.g.utils.Utils.getXmsObjectWithHmsObject(Object object) get the xms Object from the hms Object.<br/>
     *
     * @param object hms Object
     * @return xms Object
     */
    public static Object getXmsObjectWithHmsObject(Object object) {
        return getXmsObjectWithHmsObject(object, getXmsMap("NORMAL"));
    }

    public static Object getXmsObjectWithHmsObject(Object object, Map<String, String> xmsMap) {
        if (object == null) {
            return null;
        }
        if (object instanceof List) {
            try {
                return transformList2X(object, true, xmsMap);
            } catch (IllegalAccessException e) {
                org.xms.g.utils.XmsLog.e("2", e.getMessage(), e);
            } catch (InstantiationException e) {
                org.xms.g.utils.XmsLog.e("2", e.getMessage(), e);
            } catch (ClassNotFoundException e) {
                org.xms.g.utils.XmsLog.e("2", e.getMessage(), e);
            }
        }
        if (!isHmsType(object)) {
            return object;
        }
        org.xms.g.utils.XmsLog.i("1", "inObject : " + object.getClass().getName());
        return getXmsObject(object, H, xmsMap);
    }

    private static String getXmsInterfaceName(List<Class> interfaces, Map<String, String> xmsMap) {
        String result = null;
        if (interfaces == null || interfaces.isEmpty()) {
            return null;
        }
        for (Class oneInterface : interfaces) {
            String interfacesStr = oneInterface.getName().replaceAll("\\$", ".");
            if (xmsMap.containsKey(interfacesStr)) {
                result = xmsMap.get(interfacesStr);
                break;
            } else {
                result = getXmsInterfaceName(Arrays.asList(oneInterface.getInterfaces()), xmsMap);
                if (result != null) {
                    break;
                }
            }
        }
        return result;
    }

    public static Map<String, String> getXmsMap(String mapType) {
        switch (mapType) {
            case "ML_GMS":
                return mlGMSMap;
            case "NORMAL":
            default:
                return map;
        }
    }

    private static Object getXmsObject(Object object, String GorH, Map<String, String> xmsMap) {
        String interfaceClass = null;
        Class inSuperClass = object.getClass().getSuperclass();
        List<Class> interfaces = new ArrayList<>(Arrays.asList(object.getClass().getInterfaces()));
        String inClassName = object.getClass().getName();
        inClassName = inClassName.replaceAll("\\$", ".");
        while (!xmsMap.containsKey(inClassName)) {
            inClassName = inSuperClass.getName().replaceAll("\\$", ".");
            if (inClassName.equals("java.lang.Object")) {
                interfaceClass = getXmsInterfaceName(interfaces, xmsMap);
                org.xms.g.utils.XmsLog.d("2", "interfaceClass : " + interfaceClass);
                break;
            } else {
                Collections.addAll(interfaces, inSuperClass.getInterfaces());
                inSuperClass = inSuperClass.getSuperclass();
            }
        }
        String xmsClassName = xmsMap.get(inClassName);
        org.xms.g.utils.XmsLog.i("1", "inClassName : " + inClassName + ", xmsClassName : " + xmsClassName);
        if (xmsClassName == null) {
            if (interfaceClass != null) {
                xmsClassName = interfaceClass;
                org.xms.g.utils.XmsLog.i("5", "xmsClassName : " + xmsClassName);
            } else {
                org.xms.g.utils.XmsLog.i("6", "xmsClassName is null");
                return object;
            }
        }

        try {
            Class clazz = Class.forName(xmsClassName);
            org.xms.g.utils.XmsLog.i("7", "clazz : " + clazz.getName());
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                if (constructor.getParameterTypes().length == 1
                        && constructor.getParameterTypes()[0] == XMS_BOX) {
                    return "g".equals(GorH) ? constructor.newInstance(new XBox(object, null))
                            : constructor.newInstance(new XBox(null, object));
                }
            }
        } catch (ClassNotFoundException e) {
            org.xms.g.utils.XmsLog.e("8", e.getMessage(), e);
        } catch (IllegalAccessException e) {
            org.xms.g.utils.XmsLog.e("9", e.getMessage(), e);
        } catch (InstantiationException e) {
            org.xms.g.utils.XmsLog.e("10", e.getMessage(), e);
        } catch (InvocationTargetException e) {
            org.xms.g.utils.XmsLog.e("11", e.getMessage(), e);
        }
        return null;
    }

    /**
     * org.xms.g.utils.Utils.isGmsClass(String className) judge if the class is of gms class or not.<br/>
     *
     * @param className input class name
     * @return true if the class is of gms class, otherwise false.
     */
    public static boolean isGmsClass(String className) {
        if (className.startsWith("com.google.android.gms") || className.startsWith("com.google.firebase")
                || className.startsWith("com.google.ads") || className.startsWith("com.android.installreferrer")
                || className.startsWith("com.google.android.libraries") || className.startsWith("com.google.api")) {
            org.xms.g.utils.XmsLog.i("1", "true");
            return true;
        }
        org.xms.g.utils.XmsLog.i("2", "false");
        return false;
    }

    /**
     * org.xms.g.utils.Utils.isHmsClass(String className) judge if the class is of hms class or not.<br/>
     *
     * @param className input class name
     * @return true if the class is of hms class, otherwise false.
     */
    public static boolean isHmsClass(String className) {
        if (className.startsWith("com.huawei.hms") || className.startsWith("com.huawei.hmf")
                || className.startsWith("com.huawei.agconnect")) {
            org.xms.g.utils.XmsLog.i("1", "true");
            return true;
        }
        org.xms.g.utils.XmsLog.i("2", "false");
        return false;
    }

    /**
     * org.xms.g.utils.Utils.isGmsType(Object obj) judge if the class is of gms type or not.<br/>
     *
     * @param obj input object name
     * @return true if the object is of gms type, otherwise false.
     */
    public static boolean isGmsType(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().isAnonymousClass() || obj.getClass().isMemberClass()) {
            if (isGmsClass(obj.getClass().getName())) {
                return true;
            }
            if (obj.getClass().getSuperclass().getName().equals("java.lang.Object")) {
                Class[] superInterfaces = obj.getClass().getInterfaces();
                for (Class inter : superInterfaces) {
                    return isGmsClass(inter.getName());
                }
            } else {
                Class superClassName = obj.getClass().getSuperclass();
                return isGmsClass(superClassName.getName());
            }
        }
        return isGmsClass(obj.getClass().getName());
    }

    /**
     * org.xms.g.utils.Utils.isHmsType(Object obj) judge if the class is of hms type or not.<br/>
     *
     * @param obj input object name
     * @return true if the object is of hms type, otherwise false.
     */
    public static boolean isHmsType(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().isAnonymousClass() || obj.getClass().isMemberClass()) {
            if (isHmsClass(obj.getClass().getName())) {
                return true;
            }
            if (obj.getClass().getSuperclass().getName().equals("java.lang.Object")) {
                Class[] superInterfaces = obj.getClass().getInterfaces();
                for (Class inter : superInterfaces) {
                    return isHmsClass(inter.getName());
                }
            } else {
                Class superClassName = obj.getClass().getSuperclass();
                return isHmsClass(superClassName.getName());
            }
        }
        return isHmsClass(obj.getClass().getName());
    }

    /**
     * org.xms.g.utils.Utils.getGmsClassWithXmsClass(Class xmsClass) get the gms class from the xms class.<br/>
     *
     * @param xmsClass xms class name
     * @return gms class
     */
    public static Class getGmsClassWithXmsClass(Class xmsClass) {
        return getGmsClassWithXmsClass(xmsClass, getXmsMap("NORMAL"));
    }

    public static Class getGmsClassWithXmsClass(Class xmsClass, Map<String, String> xmsMap) {
        String xmsName = xmsClass.getName();
        if (!xmsMap.containsValue(xmsName)) {
            return xmsClass;
        }
        for (Map.Entry<String, String> entry : xmsMap.entrySet()) {
            String targetKey = entry.getKey();
            if (xmsName.equals(entry.getValue()) && isGmsClass(targetKey)) {
                try {
                    return Class.forName(targetKey);
                } catch (ClassNotFoundException e) {
                    return xmsClass;
                }
            }
        }
        return xmsClass;
    }

    /**
     * org.xms.g.utils.Utils.getHmsClassWithXmsClass(Class xmsClass) get the hms class from the xms class.<br/>
     *
     * @param xmsClass xms class name
     * @return hms class
     */
    public static Class getHmsClassWithXmsClass(Class xmsClass) {
        return getHmsClassWithXmsClass(xmsClass, getXmsMap("NORMAL"));
    }

    public static Class getHmsClassWithXmsClass(Class xmsClass, Map<String, String> xmsMap) {
        String xmsName = xmsClass.getName();
        if (!xmsMap.containsValue(xmsName)) {
            return xmsClass;
        }
        for (Map.Entry<String, String> entry : xmsMap.entrySet()) {
            String targetKey = entry.getKey();
            if (xmsName.equals(entry.getValue()) && isHmsClass(targetKey)) {
                try {
                    return Class.forName(targetKey);
                } catch (ClassNotFoundException e) {
                    return xmsClass;
                }
            }
        }
        return xmsClass;
    }

    /**
     * org.xms.g.utils.Utils.isXmsType(Class clazz) Tell a clazz is xms type or not.<br/>
     *
     * @param clazz the clazz need to be identified.
     * @return if clazz is xms type, return true.
     */
    public static boolean isXmsType(Class clazz) {
        boolean result = XInterface.class.isAssignableFrom(clazz);
        org.xms.g.utils.XmsLog.i("1", "isXmsType : " + result);
        return result;
    }

    /**
     * org.xms.g.utils.Utils.getOrCreateInstance(Class clazz, Object zInst, boolean isH) Create an instance from its Class, and we MUST use its wrapper constructor.<br>
     *
     * @param clazz Create an instance from clazz.
     * @param zInst parameter for constructor.
     * @return the instance.
     */
    public static Object getOrCreateInstance(Class clazz, Object zInst, boolean isH) {
        if (zInst == null) {
            org.xms.g.utils.XmsLog.i("1", "instance : null");
            return null;
        }
        if (zInst instanceof List) {
            org.xms.g.utils.XmsLog.i("2", "instance is List");
            return mapList2X((List) zInst, isH);
        }

        if (!isXmsType(clazz)) {
            org.xms.g.utils.XmsLog.i("3", "instance : " + zInst.getClass().getName());
            if (isH) {
                return getXmsObjectWithHmsObject(zInst);
            }
            return getXmsObjectWithGmsObject(zInst);
        }

        String className = "";
        if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
            className = clazz.getName();
            className += "$XImpl";
            try {
                org.xms.g.utils.XmsLog.d("4", "className : " + className);
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                org.xms.g.utils.XmsLog.e("5", e.getMessage(), e);
            }
        }

        Constructor constructor = getWrapperConstructor(clazz);
        XBox param = isH ? new XBox(null, zInst) : new XBox(zInst, null);
        if (constructor != null) {
            try {
                return constructor.newInstance(param);
            } catch (InstantiationException e) {
                org.xms.g.utils.XmsLog.e("6", e.getMessage(), e);
            } catch (IllegalAccessException e) {
                org.xms.g.utils.XmsLog.e("7", e.getMessage(), e);
            } catch (InvocationTargetException e) {
                org.xms.g.utils.XmsLog.e("8", e.getMessage(), e);
            }
        }

        return null;
    }

    /**
     * org.xms.g.utils.Utils.getWrapperConstructor(Class xmsType) Find wrapper constructor for an xms class.<br/>
     *
     * @param xmsType the class whose wrapper constructor need to be found.
     * @return xmsType's wrapper constructor.
     */
    public static Constructor getWrapperConstructor(Class xmsType) {
        if (wrapperCache.containsKey(xmsType)) {
            org.xms.g.utils.XmsLog.i("1", "wrapperCache.get(xmsType) xmsType : " + xmsType.getName());
            return wrapperCache.get(xmsType);
        }

        Constructor[] constructors = xmsType.getConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.getParameterTypes().length != 1) {
                continue;
            }

            if (constructor.getParameterTypes()[0] == XMS_BOX) {
                wrapperCache.put(xmsType, constructor);
                XmsLog.i("2",
                        "wrapperCache.put(xmsType, constructors[i]) xmsType : " + xmsType.getName());
                return constructor;
            } else {
                XmsLog.w("3",
                        "map not containsKey " + constructor.getParameterTypes()[0].getCanonicalName());
            }
        }

        return null;
    }

    /**
     * org.xms.g.utils.Utils.getInstanceInInterface(Object o, boolean isH) If an object is xms instance, get its G instance or H instance.An xms object may be an XGettable instance, then it has a concrete g instance,also, it may be an XInterface but not XGettable, we must call these methods by reflection.<br/>
     *
     * @param o   object to get its g instance.
     * @param isH show we need its g or h instance.
     * @return xms object's g/h instance.
     */
    public static <T> T getInstanceInInterface(Object o, boolean isH) {
        if (!(o instanceof XInterface)) {
            String inObjectType = o == null ? null : o.getClass().getName();
            org.xms.g.utils.XmsLog.i("1", "inObject : " + inObjectType);
            return (T) o;
        }

        if (o instanceof XGettable) {
            if (isH) {
                org.xms.g.utils.XmsLog.i("2", "hInstance : " + ((XGettable) o).getHInstance().getClass().getName());
                return (T) ((XGettable) o).getHInstance();
            } else {
                org.xms.g.utils.XmsLog.i("3", "gInstance : " + ((XGettable) o).getGInstance().getClass().getName());
                return (T) ((XGettable) o).getGInstance();
            }
        }

        return (T) reflectiveGetInstance(o, isH);
    }

    private static Object reflectiveGetInstance(Object o, boolean isH) {
        if (getZInstCache.containsKey(o.getClass())) {
            org.xms.g.utils.XmsLog.i("1", "inObject : " + o.getClass());
            try {
                return getZInstCache.get(o.getClass()).invoke(o);
            } catch (IllegalAccessException e) {
                org.xms.g.utils.XmsLog.i("2", "inObject : " + o.getClass(), e);
            } catch (InvocationTargetException e) {
                org.xms.g.utils.XmsLog.i("3", "inObject : " + o.getClass(), e);
            }
        }

        Method[] methods = o.getClass().getMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length > 0) {
                continue;
            }

            if (isH && (!method.getName().startsWith("getHInstance"))) {
                continue;
            }

            if (!isH && (!method.getName().startsWith("getGInstance"))) {
                continue;
            }

            XmsLog.i("2", "inObject : " + o.getClass() + ", methods[i] : " + method.getName());
            getZInstCache.put(o.getClass(), method);
            try {
                return method.invoke(o);
            } catch (IllegalAccessException e) {
                XmsLog.e("3", e.getMessage(), e);
            } catch (InvocationTargetException e) {
                XmsLog.e("4", e.getMessage(), e);
            }
        }

        return null;
    }

    private static Object[] wrapZType(Object[] objects, Class[] types, boolean isH) {
        Object[] xmsObj = new Object[objects.length];
        for (int i = 0; i < objects.length; i++) {
            xmsObj[i] = Utils.getOrCreateInstance(types[i], objects[i], isH);
        }
        if (xmsObj.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < xmsObj.length; i++) {
                String argsType = xmsObj[i] == null ? null : xmsObj[i].getClass().getName();
                sb.append("XMS Types [").append(i).append("] : ").append(argsType).append(", ");
            }
            org.xms.g.utils.XmsLog.i("1", sb.toString());
        }
        return xmsObj;
    }

    /**
     * org.xms.g.utils.Utils.getBridgedMethod(Object receiver, String methodName, Class[] paramTypes) get bridged method.<br/>
     *
     * @param receiver   receiver name
     * @param methodName method name
     * @param paramTypes param types
     * @return bridged method
     * @throws NoSuchMethodException can not find such method
     */
    public static Method getBridgedMethod(Object receiver, String methodName, Class[] paramTypes) throws NoSuchMethodException {
        Method bridgeMethod = BridgeMethodUtils.getBridgeMethod(receiver.getClass(), methodName, paramTypes);
        org.xms.g.utils.XmsLog.d("1", "get bridge method " + bridgeMethod.toString());
        Method bridgedMethod = BridgeMethodUtils.getBridgedMethod(bridgeMethod);
        org.xms.g.utils.XmsLog.d("2", "get bridged method " + bridgedMethod.toString());
        String bridgeMethodType = bridgeMethod.getName();
        String bridgedMethodMethodType = bridgedMethod.getName();
        org.xms.g.utils.XmsLog.d("3",
                "bridgeMethod : " + bridgeMethodType + ", bridgedMethod : " + bridgedMethodMethodType);
        return bridgedMethod;
    }

    /**
     * org.xms.g.utils.Utils.getXmsRetObj(Object receiver, String methodName, Object returnObj, Class[] paramTypes, boolean isH) Get xms type by Invoking the bridge method with the original return types.<br/>
     *
     * @param receiver   the invoke target
     * @param methodName the method name
     * @param returnObj  return object
     * @param paramTypes the declaration types of parameters (the upper bound type for the generic type)
     * @param isH        HMS if true; GMS, otherwise
     * @return xms object
     */
    public static Object getXmsRetObj(Object receiver, String methodName, Object returnObj, Class[] paramTypes, boolean isH) {
        if (returnObj == null) {
            org.xms.g.utils.XmsLog.w("1", "returnObj == null");
            throw new IllegalArgumentException("return object can not be null!");
        }

        if (paramTypes == null) {
            org.xms.g.utils.XmsLog.w("2", "paramTypes == null");
            throw new IllegalArgumentException("parameter types object can not be null!");
        }

        Method bridgedMethod;
        try {
            bridgedMethod = getBridgedMethod(receiver, methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            return isH ? getXmsObjectWithHmsObject(returnObj) : getXmsObjectWithGmsObject(returnObj);
        }
        Class<?> returnType = bridgedMethod.getReturnType();
        return wrapZType(new Object[]{returnObj}, new Class[]{returnType}, isH)[0];
    }

    public static Object invokeMethod(Object receiver, String methodName, Object[] params, Class[] paramTypes,
                                      boolean isH) throws IllegalStateException {
        return invokeMethod(receiver, methodName, params, paramTypes, isH, getXmsMap("NORMAL"));
    }

    /**
     * org.xms.g.utils.Utils.invokeMethod(Object receiver, String methodName, Object[] params, Class[] paramTypes,boolean isH) Invoke the bridge method with the original parameter types.<br/>
     *
     * @param receiver   the invoke target
     * @param methodName the method name
     * @param params     parameters
     * @param paramTypes the declaration types of parameters (the upper bound type for the generic type)
     * @param isH        HMS if true; GMS, otherwise
     * @param xmsMap     XMS relation map
     * @return the return value
     * @throws IllegalStateException capsuling the real refection exception
     */
    public static Object invokeMethod(Object receiver, String methodName, Object[] params, Class[] paramTypes,
                                      boolean isH, Map<String, String> xmsMap) throws IllegalStateException {
        if (params == null) {
            org.xms.g.utils.XmsLog.w("1", "params == null");
            throw new IllegalArgumentException("null params");
        }

        if (paramTypes == null) {
            org.xms.g.utils.XmsLog.w("2", "paramTypes == null");
            throw new IllegalArgumentException("null paramTypes");
        }

        if (params.length != paramTypes.length) {
            org.xms.g.utils.XmsLog.w("3", "params.length != paramTypes.length");
            throw new IllegalArgumentException("mismatched params and paramTypes");
        }

        Method bridgedMethod;
        try {
            bridgedMethod = getBridgedMethod(receiver, methodName, paramTypes);
        } catch (NoSuchMethodException e) {
            return invokeImprecise(receiver, methodName, params, paramTypes, isH, xmsMap);
        }
        org.xms.g.utils.XmsLog.i("4", "receiver : " + receiver.getClass().getName());
        try {
            Class<?>[] types = bridgedMethod.getParameterTypes();
            Object[] args = wrapZType(params, types, isH);
            bridgedMethod.setAccessible(true);
            return bridgedMethod.invoke(receiver, args);
        } catch (Exception ex) {
            org.xms.g.utils.XmsLog.e("5", ex.getMessage(), ex);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * org.xms.g.utils.Utils.invokeImprecise(Object receiver, String methodName, Object[] paramObj, Class[] upperBoundType, boolean isH) invoke imprecise.<br/>
     *
     * @param receiver       the invoke target
     * @param methodName     the method name
     * @param paramObj       parameters
     * @param upperBoundType type of upper boundary
     * @param isH            is hms or not
     * @return Object
     */
     public static Object invokeImprecise(Object receiver, String methodName, Object[] paramObj, Class[] upperBoundType, boolean isH, Map<String, String> xmsMap) {
        Object[] xmsObj = new Object[paramObj.length];
        for (int i = 0; i < paramObj.length; i++) {
            xmsObj[i] = isH ? getXmsObjectWithHmsObject(paramObj[i], xmsMap) : getXmsObjectWithGmsObject(paramObj[i], xmsMap);
        }
        Method[] methods = receiver.getClass().getMethods();
        Method method = getDeclaredMethod(methods, methodName, upperBoundType);
        try {
            return method.invoke(receiver, xmsObj);
        } catch (Exception ex) {
            org.xms.g.utils.XmsLog.e("1", ex.getMessage(), ex);
            throw new IllegalStateException(ex);
        }
    }

    private static Method getDeclaredMethod(Method[] methods, String methodName, Class[] upperBoundType) {
        for (Method method : methods) {
            if (!method.getName().equals(methodName) || method.getParameterTypes().length != upperBoundType.length) {
                continue;
            }
            Class[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (!upperBoundType[i].isAssignableFrom(paramTypes[i])) {
                    break;
                }
                if (i == paramTypes.length - 1) {
                    return method;
                }
            }
        }
        throw new IllegalStateException("No such method!");
    }

    /**
     * org.xms.g.utils.Utils.handleInvokeBridgeReturnValue(Object receiver, boolean isH) handle invokeBridge method return value.return value need type cast.<br/>
     *
     * @param receiver the invoke target
     * @param isH      HMS if true; GMS, otherwise
     * @return the return value
     */
    public static Object handleInvokeBridgeReturnValue(Object receiver, boolean isH) {
        if (!isXmsType(receiver.getClass())) {
            org.xms.g.utils.XmsLog.d("1", "receiver : " + receiver.getClass().getName());
            return receiver;
        }
        if (receiver instanceof XGettable) {
            if (isH) {
                org.xms.g.utils.XmsLog.i("2",
                        "hInstance : " + ((XGettable) receiver).getHInstance().getClass().getName());
                return ((XGettable) receiver).getHInstance();
            }
            org.xms.g.utils.XmsLog.i("3", "gInstance : " + ((XGettable) receiver).getGInstance().getClass().getName());
            return ((XGettable) receiver).getGInstance();
        }
        Method[] methods = receiver.getClass().getMethods();
        int cnt = 0;
        String prefix = isH ? "getHInstance" : "getGInstance";
        Method target = null;
        for (Method m : methods) {
            if (m.getName().startsWith(prefix)) {
                cnt++;
                target = m;
            }
        }
        if (cnt == 1) {
            try {
                org.xms.g.utils.XmsLog.i("4", "receiver : " + receiver.getClass().getName());
                return target.invoke(receiver);
            } catch (IllegalAccessException e) {
                org.xms.g.utils.XmsLog.e("5", e.getMessage(), e);
            } catch (InvocationTargetException e) {
                org.xms.g.utils.XmsLog.e("6", e.getMessage(), e);
            }
        }
        IllegalStateException illegalStateException = new IllegalStateException("multiple getInstance methods found.");
        org.xms.g.utils.XmsLog.w("7", illegalStateException.getMessage());
        throw illegalStateException;
    }

    /**
     * org.xms.g.utils.Utils.invokeProtectMethod(Object receiver, Class targetClass, String methodName, Class[] methodParametersType, Object[] args) invoke protect method.<br/>
     *
     * @param receiver             the invoke target
     * @param targetClass          the target class
     * @param methodName           the method name
     * @param methodParametersType the type of method parameters
     * @param args                 the arguments
     * @return the Object
     */
    public static Object invokeProtectMethod(Object receiver, Class targetClass, String methodName,
                                             Class[] methodParametersType, Object[] args) {
        if (targetClass == null) {
            throw new IllegalStateException("null class.");
        }
        if (methodName == null || methodName.isEmpty()) {
            throw new IllegalStateException("methodName does not exist.");
        }
        try {
            Method method = targetClass.getDeclaredMethod(methodName, methodParametersType);
            if (method == null) {
                throw new IllegalStateException("method does not exist.");
            }
            method.setAccessible(true);
            return method.invoke(receiver, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    static {
        map.put("com.google.firebase.FirebaseApiNotAvailableException", "org.xms.f.ExtensionApiNotAvailableException");
        map.put("com.google.firebase.FirebaseApp", "org.xms.f.ExtensionApp");
        map.put("com.google.firebase.FirebaseApp", "org.xms.f.ExtensionApp");
        map.put("com.google.firebase.FirebaseException", "org.xms.f.ExtensionException");
        map.put("com.google.firebase.FirebaseNetworkException", "org.xms.f.ExtensionNetworkException");
        map.put("com.google.firebase.FirebaseOptions", "org.xms.f.ExtensionOptions");
        map.put("com.google.firebase.FirebaseOptions.Builder", "org.xms.f.ExtensionOptions$Builder");
        map.put("com.google.firebase.FirebaseTooManyRequestsException", "org.xms.f.ExtensionTooManyRequestsException");
        map.put("com.google.firebase.analytics.FirebaseAnalytics", "org.xms.f.analytics.ExtensionAnalytics");
        map.put("com.huawei.hms.analytics.HiAnalyticsInstance", "org.xms.f.analytics.ExtensionAnalytics");
        map.put("com.google.firebase.analytics.FirebaseAnalytics.Event", "org.xms.f.analytics.ExtensionAnalytics$Event");
        map.put("com.huawei.hms.analytics.type.HAEventType", "org.xms.f.analytics.ExtensionAnalytics$Event");
        map.put("com.google.firebase.analytics.FirebaseAnalytics.Param", "org.xms.f.analytics.ExtensionAnalytics$Param");
        map.put("com.huawei.hms.analytics.type.HAParamType", "org.xms.f.analytics.ExtensionAnalytics$Param");
        map.put("com.google.firebase.analytics.FirebaseAnalytics.UserProperty", "org.xms.f.analytics.ExtensionAnalytics$UserProperty");
        map.put("com.huawei.hms.analytics.type.HAParamType", "org.xms.f.analytics.ExtensionAnalytics$UserProperty");
        map.put("com.google.firebase.dynamiclinks.DynamicLink", "org.xms.f.dynamiclinks.DynamicLink");
        map.put("com.huawei.agconnect.applinking.AppLinking", "org.xms.f.dynamiclinks.DynamicLink");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.AndroidParameters", "org.xms.f.dynamiclinks.DynamicLink$AndroidParameters");
        map.put("com.huawei.agconnect.applinking.AppLinking.AndroidLinkInfo", "org.xms.f.dynamiclinks.DynamicLink$AndroidParameters");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.AndroidParameters.Builder", "org.xms.f.dynamiclinks.DynamicLink$AndroidParameters$Builder");
        map.put("com.huawei.agconnect.applinking.AppLinking.AndroidLinkInfo.Builder", "org.xms.f.dynamiclinks.DynamicLink$AndroidParameters$Builder");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.Builder", "org.xms.f.dynamiclinks.DynamicLink$Builder");
        map.put("com.huawei.agconnect.applinking.AppLinking.Builder", "org.xms.f.dynamiclinks.DynamicLink$Builder");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.GoogleAnalyticsParameters", "org.xms.f.dynamiclinks.DynamicLink$ExtensionAnalyticsParameters");
        map.put("com.huawei.agconnect.applinking.AppLinking.CampaignInfo", "org.xms.f.dynamiclinks.DynamicLink$ExtensionAnalyticsParameters");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.GoogleAnalyticsParameters.Builder", "org.xms.f.dynamiclinks.DynamicLink$ExtensionAnalyticsParameters$Builder");
        map.put("com.huawei.agconnect.applinking.AppLinking.CampaignInfo.Builder", "org.xms.f.dynamiclinks.DynamicLink$ExtensionAnalyticsParameters$Builder");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.IosParameters", "org.xms.f.dynamiclinks.DynamicLink$IosParameters");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.IosParameters.Builder", "org.xms.f.dynamiclinks.DynamicLink$IosParameters$Builder");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.ItunesConnectAnalyticsParameters", "org.xms.f.dynamiclinks.DynamicLink$ItunesConnectAnalyticsParameters");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.ItunesConnectAnalyticsParameters.Builder", "org.xms.f.dynamiclinks.DynamicLink$ItunesConnectAnalyticsParameters$Builder");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.NavigationInfoParameters", "org.xms.f.dynamiclinks.DynamicLink$NavigationInfoParameters");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.NavigationInfoParameters.Builder", "org.xms.f.dynamiclinks.DynamicLink$NavigationInfoParameters$Builder");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.SocialMetaTagParameters", "org.xms.f.dynamiclinks.DynamicLink$SocialMetaTagParameters");
        map.put("com.huawei.agconnect.applinking.AppLinking.SocialCardInfo", "org.xms.f.dynamiclinks.DynamicLink$SocialMetaTagParameters");
        map.put("com.google.firebase.dynamiclinks.DynamicLink.SocialMetaTagParameters.Builder", "org.xms.f.dynamiclinks.DynamicLink$SocialMetaTagParameters$Builder");
        map.put("com.huawei.agconnect.applinking.AppLinking.SocialCardInfo.Builder", "org.xms.f.dynamiclinks.DynamicLink$SocialMetaTagParameters$Builder");
        map.put("com.google.firebase.dynamiclinks.FirebaseDynamicLinks", "org.xms.f.dynamiclinks.ExtensionDynamicLinks$XImpl");
        map.put("com.huawei.agconnect.applinking.AGConnectAppLinking", "org.xms.f.dynamiclinks.ExtensionDynamicLinks$XImpl");
        map.put("com.google.firebase.dynamiclinks.PendingDynamicLinkData", "org.xms.f.dynamiclinks.PendingDynamicLinkData");
        map.put("com.huawei.agconnect.applinking.ResolvedLinkData", "org.xms.f.dynamiclinks.PendingDynamicLinkData");
        map.put("com.google.firebase.dynamiclinks.ShortDynamicLink.Suffix", "org.xms.f.dynamiclinks.ShortDynamicLink$Suffix$XImpl");
        map.put("com.google.firebase.dynamiclinks.ShortDynamicLink.Warning", "org.xms.f.dynamiclinks.ShortDynamicLink$Warning$XImpl");
        map.put("com.google.firebase.dynamiclinks.ShortDynamicLink", "org.xms.f.dynamiclinks.ShortDynamicLink$XImpl");
        map.put("com.google.firebase.iid.FirebaseInstanceId", "org.xms.f.iid.ExtensionInstanceId");
        map.put("com.huawei.hms.aaid.HmsInstanceId", "org.xms.f.iid.ExtensionInstanceId");
        map.put("com.google.firebase.iid.FirebaseInstanceIdReceiver", "org.xms.f.iid.ExtensionInstanceIdReceiver");
        map.put("com.google.firebase.iid.InstanceIdResult", "org.xms.f.iid.InstanceIdResult$XImpl");
        map.put("com.huawei.hms.aaid.entity.AAIDResult", "org.xms.f.iid.InstanceIdResult$XImpl");
        map.put("com.google.firebase.messaging.FirebaseMessaging", "org.xms.f.messaging.ExtensionMessaging");
        map.put("com.huawei.hms.push.HmsMessaging", "org.xms.f.messaging.ExtensionMessaging");
        map.put("com.google.firebase.messaging.RemoteMessage", "org.xms.f.messaging.RemoteMessage");
        map.put("com.huawei.hms.push.RemoteMessage", "org.xms.f.messaging.RemoteMessage");
        map.put("com.google.firebase.messaging.RemoteMessage.Builder", "org.xms.f.messaging.RemoteMessage$Builder");
        map.put("com.huawei.hms.push.RemoteMessage.Builder", "org.xms.f.messaging.RemoteMessage$Builder");
        map.put("com.google.firebase.messaging.RemoteMessage.Notification", "org.xms.f.messaging.RemoteMessage$Notification");
        map.put("com.huawei.hms.push.RemoteMessage.Notification", "org.xms.f.messaging.RemoteMessage$Notification");
        map.put("com.google.firebase.messaging.SendException", "org.xms.f.messaging.SendException");
        map.put("com.huawei.hms.push.SendException", "org.xms.f.messaging.SendException");
        map.put("com.google.firebase.provider.FirebaseInitProvider", "org.xms.f.provider.ExtensionInitProvider");
        map.put("com.google.android.gms.actions.ItemListIntents", "org.xms.g.actions.ItemListIntents");
        map.put("com.google.android.gms.actions.NoteIntents", "org.xms.g.actions.NoteIntents");
        map.put("com.google.android.gms.actions.ReserveIntents", "org.xms.g.actions.ReserveIntents");
        map.put("com.google.android.gms.actions.SearchIntents", "org.xms.g.actions.SearchIntents");
        map.put("com.huawei.hms.actions.SearchIntents", "org.xms.g.actions.SearchIntents");
        map.put("com.google.android.gms.analytics.AnalyticsJobService", "org.xms.g.analytics.AnalyticsJobService");
        map.put("com.google.android.gms.analytics.AnalyticsReceiver", "org.xms.g.analytics.AnalyticsReceiver");
        map.put("com.google.android.gms.analytics.AnalyticsService", "org.xms.g.analytics.AnalyticsService");
        map.put("com.google.android.gms.analytics.CampaignTrackingReceiver", "org.xms.g.analytics.CampaignTrackingReceiver");
        map.put("com.google.android.gms.analytics.CampaignTrackingService", "org.xms.g.analytics.CampaignTrackingService");
        map.put("com.google.android.gms.analytics.ExceptionParser", "org.xms.g.analytics.ExceptionParser$XImpl");
        map.put("com.google.android.gms.analytics.ExceptionReporter", "org.xms.g.analytics.ExceptionReporter");
        map.put("com.google.android.gms.analytics.GoogleAnalytics", "org.xms.g.analytics.ExtensionAnalytics");
        map.put("com.google.android.gms.analytics.HitBuilders", "org.xms.g.analytics.HitBuilders");
        map.put("com.google.android.gms.analytics.HitBuilders.AppViewBuilder", "org.xms.g.analytics.HitBuilders$AppViewBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.EventBuilder", "org.xms.g.analytics.HitBuilders$EventBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.ExceptionBuilder", "org.xms.g.analytics.HitBuilders$ExceptionBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.HitBuilder", "org.xms.g.analytics.HitBuilders$HitBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.ItemBuilder", "org.xms.g.analytics.HitBuilders$ItemBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder", "org.xms.g.analytics.HitBuilders$ScreenViewBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.SocialBuilder", "org.xms.g.analytics.HitBuilders$SocialBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.TimingBuilder", "org.xms.g.analytics.HitBuilders$TimingBuilder");
        map.put("com.google.android.gms.analytics.HitBuilders.TransactionBuilder", "org.xms.g.analytics.HitBuilders$TransactionBuilder");
        map.put("com.google.android.gms.analytics.Logger.LogLevel", "org.xms.g.analytics.Logger$LogLevel");
        map.put("com.google.android.gms.analytics.Logger", "org.xms.g.analytics.Logger$XImpl");
        map.put("com.google.android.gms.analytics.StandardExceptionParser", "org.xms.g.analytics.StandardExceptionParser");
        map.put("com.google.android.gms.analytics.Tracker", "org.xms.g.analytics.Tracker");
        map.put("com.google.android.gms.analytics.ecommerce.Product", "org.xms.g.analytics.ecommerce.Product");
        map.put("com.google.android.gms.analytics.ecommerce.ProductAction", "org.xms.g.analytics.ecommerce.ProductAction");
        map.put("com.google.android.gms.analytics.ecommerce.Promotion", "org.xms.g.analytics.ecommerce.Promotion");
        map.put("com.google.android.gms.auth.AccountChangeEvent", "org.xms.g.auth.AccountChangeEvent");
        map.put("com.google.android.gms.auth.AccountChangeEventsRequest", "org.xms.g.auth.AccountChangeEventsRequest");
        map.put("com.google.android.gms.auth.AccountChangeEventsResponse", "org.xms.g.auth.AccountChangeEventsResponse");
        map.put("com.google.android.gms.auth.CookieUtil", "org.xms.g.auth.CookieUtil");
        map.put("com.huawei.hms.support.hwid.tools.NetworkTool", "org.xms.g.auth.CookieUtil");
        map.put("com.google.android.gms.auth.GoogleAuthException", "org.xms.g.auth.ExtensionAuthException");
        map.put("com.huawei.hms.support.hwid.common.HuaweiIdAuthException", "org.xms.g.auth.ExtensionAuthException");
        map.put("com.google.android.gms.auth.GoogleAuthUtil", "org.xms.g.auth.ExtensionAuthUtil");
        map.put("com.huawei.hms.support.hwid.tools.HuaweiIdAuthTool", "org.xms.g.auth.ExtensionAuthUtil");
        map.put("com.google.android.gms.auth.GooglePlayServicesAvailabilityException", "org.xms.g.auth.ExtensionPlayServicesAvailabilityException");
        map.put("com.google.android.gms.auth.UserRecoverableAuthException", "org.xms.g.auth.UserRecoverableAuthException");
        map.put("com.google.android.gms.auth.UserRecoverableNotifiedException", "org.xms.g.auth.UserRecoverableNotifiedException");
        map.put("com.google.android.gms.auth.account.WorkAccount", "org.xms.g.auth.account.WorkAccount");
        map.put("com.google.android.gms.auth.account.WorkAccountApi.AddAccountResult", "org.xms.g.auth.account.WorkAccountApi$AddAccountResult$XImpl");
        map.put("com.google.android.gms.auth.account.WorkAccountApi", "org.xms.g.auth.account.WorkAccountApi$XImpl");
        map.put("com.google.android.gms.auth.account.WorkAccountClient", "org.xms.g.auth.account.WorkAccountClient");
        map.put("com.google.android.gms.auth.api.Auth", "org.xms.g.auth.api.Auth");
        map.put("com.huawei.hms.support.hwid.HuaweiIdAuthAPIManager", "org.xms.g.auth.api.Auth");
        map.put("com.google.android.gms.auth.api.Auth.AuthCredentialsOptions", "org.xms.g.auth.api.Auth$AuthCredentialsOptions");
        map.put("com.google.android.gms.auth.api.Auth.AuthCredentialsOptions.Builder", "org.xms.g.auth.api.Auth$AuthCredentialsOptions$Builder");
        map.put("com.google.android.gms.auth.api.accounttransfer.AccountTransfer", "org.xms.g.auth.api.accounttransfer.AccountTransfer");
        map.put("com.google.android.gms.auth.api.accounttransfer.AccountTransferClient", "org.xms.g.auth.api.accounttransfer.AccountTransferClient");
        map.put("com.google.android.gms.auth.api.accounttransfer.AccountTransferException", "org.xms.g.auth.api.accounttransfer.AccountTransferException");
        map.put("com.google.android.gms.auth.api.accounttransfer.AccountTransferStatusCodes", "org.xms.g.auth.api.accounttransfer.AccountTransferStatusCodes");
        map.put("com.google.android.gms.auth.api.accounttransfer.AuthenticatorTransferCompletionStatus", "org.xms.g.auth.api.accounttransfer.AuthenticatorTransferCompletionStatus$XImpl");
        map.put("com.google.android.gms.auth.api.accounttransfer.DeviceMetaData", "org.xms.g.auth.api.accounttransfer.DeviceMetaData");
        map.put("com.google.android.gms.auth.api.credentials.Credential", "org.xms.g.auth.api.credentials.Credential");
        map.put("com.google.android.gms.auth.api.credentials.Credential.Builder", "org.xms.g.auth.api.credentials.Credential$Builder");
        map.put("com.google.android.gms.auth.api.credentials.CredentialPickerConfig", "org.xms.g.auth.api.credentials.CredentialPickerConfig");
        map.put("com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Builder", "org.xms.g.auth.api.credentials.CredentialPickerConfig$Builder");
        map.put("com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt", "org.xms.g.auth.api.credentials.CredentialPickerConfig$Prompt");
        map.put("com.google.android.gms.auth.api.credentials.CredentialRequest", "org.xms.g.auth.api.credentials.CredentialRequest");
        map.put("com.google.android.gms.auth.api.credentials.CredentialRequest.Builder", "org.xms.g.auth.api.credentials.CredentialRequest$Builder");
        map.put("com.google.android.gms.auth.api.credentials.CredentialRequestResponse", "org.xms.g.auth.api.credentials.CredentialRequestResponse");
        map.put("com.google.android.gms.auth.api.credentials.CredentialRequestResult", "org.xms.g.auth.api.credentials.CredentialRequestResult$XImpl");
        map.put("com.google.android.gms.auth.api.credentials.Credentials", "org.xms.g.auth.api.credentials.Credentials");
        map.put("com.google.android.gms.auth.api.credentials.CredentialsApi", "org.xms.g.auth.api.credentials.CredentialsApi$XImpl");
        map.put("com.google.android.gms.auth.api.credentials.CredentialsClient", "org.xms.g.auth.api.credentials.CredentialsClient");
        map.put("com.google.android.gms.auth.api.credentials.CredentialsOptions", "org.xms.g.auth.api.credentials.CredentialsOptions");
        map.put("com.google.android.gms.auth.api.credentials.CredentialsOptions.Builder", "org.xms.g.auth.api.credentials.CredentialsOptions$Builder");
        map.put("com.google.android.gms.auth.api.credentials.HintRequest", "org.xms.g.auth.api.credentials.HintRequest");
        map.put("com.google.android.gms.auth.api.credentials.HintRequest.Builder", "org.xms.g.auth.api.credentials.HintRequest$Builder");
        map.put("com.google.android.gms.auth.api.credentials.IdToken", "org.xms.g.auth.api.credentials.IdToken");
        map.put("com.google.android.gms.auth.api.credentials.IdentityProviders", "org.xms.g.auth.api.credentials.IdentityProviders");
        map.put("com.google.android.gms.auth.api.identity.BeginSignInRequest", "org.xms.g.auth.api.identity.BeginSignInRequest");
        map.put("com.google.android.gms.auth.api.identity.BeginSignInRequest.Builder", "org.xms.g.auth.api.identity.BeginSignInRequest$Builder");
        map.put("com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions", "org.xms.g.auth.api.identity.BeginSignInRequest$ExtensionIdTokenRequestOptions");
        map.put("com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder", "org.xms.g.auth.api.identity.BeginSignInRequest$ExtensionIdTokenRequestOptions$Builder");
        map.put("com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions", "org.xms.g.auth.api.identity.BeginSignInRequest$PasswordRequestOptions");
        map.put("com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.Builder", "org.xms.g.auth.api.identity.BeginSignInRequest$PasswordRequestOptions$Builder");
        map.put("com.google.android.gms.auth.api.identity.BeginSignInResult", "org.xms.g.auth.api.identity.BeginSignInResult");
        map.put("com.google.android.gms.auth.api.identity.Identity", "org.xms.g.auth.api.identity.Identity");
        map.put("com.google.android.gms.auth.api.identity.SignInClient", "org.xms.g.auth.api.identity.SignInClient$XImpl");
        map.put("com.google.android.gms.auth.api.identity.SignInCredential", "org.xms.g.auth.api.identity.SignInCredential");
        map.put("com.google.android.gms.auth.api.identity.SignInOptions", "org.xms.g.auth.api.identity.SignInOptions");
        map.put("com.google.android.gms.auth.api.identity.SignInOptions.Builder", "org.xms.g.auth.api.identity.SignInOptions$Builder");
        map.put("com.google.android.gms.auth.api.phone.SmsCodeAutofillClient.PermissionState", "org.xms.g.auth.api.phone.SmsCodeAutofillClient$PermissionState$XImpl");
        map.put("com.google.android.gms.auth.api.phone.SmsCodeAutofillClient", "org.xms.g.auth.api.phone.SmsCodeAutofillClient$XImpl");
        map.put("com.google.android.gms.auth.api.phone.SmsCodeRetriever", "org.xms.g.auth.api.phone.SmsCodeRetriever");
        map.put("com.google.android.gms.auth.api.phone.SmsRetriever", "org.xms.g.auth.api.phone.SmsRetriever");
        map.put("com.huawei.hms.support.sms.common.ReadSmsConstant", "org.xms.g.auth.api.phone.SmsRetriever");
        map.put("com.google.android.gms.auth.api.phone.SmsRetrieverApi", "org.xms.g.auth.api.phone.SmsRetrieverApi$XImpl");
        map.put("com.google.android.gms.auth.api.phone.SmsRetrieverClient", "org.xms.g.auth.api.phone.SmsRetrieverClient$XImpl");
        map.put("com.google.android.gms.auth.api.phone.SmsRetrieverStatusCodes", "org.xms.g.auth.api.phone.SmsRetrieverStatusCodes");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignIn", "org.xms.g.auth.api.signin.ExtensionSignIn");
        map.put("com.huawei.hms.support.hwid.HuaweiIdAuthManager", "org.xms.g.auth.api.signin.ExtensionSignIn");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInAccount", "org.xms.g.auth.api.signin.ExtensionSignInAccount");
        map.put("com.huawei.hms.support.hwid.result.AuthHuaweiId", "org.xms.g.auth.api.signin.ExtensionSignInAccount");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInApi", "org.xms.g.auth.api.signin.ExtensionSignInApi$XImpl");
        map.put("com.huawei.hms.support.hwid.service.HuaweiIdAuthAPIService", "org.xms.g.auth.api.signin.ExtensionSignInApi$XImpl");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInClient", "org.xms.g.auth.api.signin.ExtensionSignInClient");
        map.put("com.huawei.hms.support.hwid.service.HuaweiIdAuthService", "org.xms.g.auth.api.signin.ExtensionSignInClient");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInOptions", "org.xms.g.auth.api.signin.ExtensionSignInOptions");
        map.put("com.huawei.hms.support.hwid.request.HuaweiIdAuthParams", "org.xms.g.auth.api.signin.ExtensionSignInOptions");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder", "org.xms.g.auth.api.signin.ExtensionSignInOptions$Builder");
        map.put("com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper", "org.xms.g.auth.api.signin.ExtensionSignInOptions$Builder");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension", "org.xms.g.auth.api.signin.ExtensionSignInOptionsExtension$XImpl");
        map.put("com.huawei.hms.support.hwid.request.HuaweiIdAuthExtendedParams", "org.xms.g.auth.api.signin.ExtensionSignInOptionsExtension$XImpl");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInResult", "org.xms.g.auth.api.signin.ExtensionSignInResult");
        map.put("com.huawei.hms.support.hwid.result.HuaweiIdAuthResult", "org.xms.g.auth.api.signin.ExtensionSignInResult");
        map.put("com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes", "org.xms.g.auth.api.signin.ExtensionSignInStatusCodes");
        map.put("com.huawei.hms.support.hwid.result.HuaweiIdAuthResultCode", "org.xms.g.auth.api.signin.ExtensionSignInStatusCodes");
        map.put("com.google.android.gms.auth.api.signin.RevocationBoundService", "org.xms.g.auth.api.signin.RevocationBoundService");
        map.put("com.google.android.gms.common.AccountPicker", "org.xms.g.common.AccountPicker");
        map.put("com.google.android.gms.common.AccountPicker.AccountChooserOptions", "org.xms.g.common.AccountPicker$AccountChooserOptions");
        map.put("com.google.android.gms.common.AccountPicker.AccountChooserOptions.Builder", "org.xms.g.common.AccountPicker$AccountChooserOptions$Builder");
        map.put("com.google.android.gms.common.ConnectionResult", "org.xms.g.common.ConnectionResult");
        map.put("com.huawei.hms.api.ConnectionResult", "org.xms.g.common.ConnectionResult");
        map.put("com.google.android.gms.common.ErrorDialogFragment", "org.xms.g.common.ErrorDialogFragment");
        map.put("com.huawei.hms.common.ErrorDialogFragment", "org.xms.g.common.ErrorDialogFragment");
        map.put("com.google.android.gms.common.GoogleApiAvailability", "org.xms.g.common.ExtensionApiAvailability");
        map.put("com.huawei.hms.api.HuaweiApiAvailability", "org.xms.g.common.ExtensionApiAvailability");
        map.put("com.google.android.gms.common.GooglePlayServicesNotAvailableException", "org.xms.g.common.ExtensionPlayServicesNotAvailableException");
        map.put("com.huawei.hms.api.HuaweiServicesNotAvailableException", "org.xms.g.common.ExtensionPlayServicesNotAvailableException");
        map.put("com.google.android.gms.common.GooglePlayServicesRepairableException", "org.xms.g.common.ExtensionPlayServicesRepairableException");
        map.put("com.huawei.hms.api.HuaweiServicesRepairableException", "org.xms.g.common.ExtensionPlayServicesRepairableException");
        map.put("com.google.android.gms.common.GooglePlayServicesUtil", "org.xms.g.common.ExtensionPlayServicesUtil");
        map.put("com.huawei.hms.api.HuaweiMobileServicesUtil", "org.xms.g.common.ExtensionPlayServicesUtil");
        map.put("com.google.android.gms.common.Scopes", "org.xms.g.common.Scopes");
        map.put("com.google.android.gms.common.SignInButton", "org.xms.g.common.SignInButton");
        map.put("com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton", "org.xms.g.common.SignInButton");
        map.put("com.google.android.gms.common.SupportErrorDialogFragment", "org.xms.g.common.SupportErrorDialogFragment");
        map.put("com.huawei.hms.common.ErrDlgFragmentForSupport", "org.xms.g.common.SupportErrorDialogFragment");
        map.put("com.google.android.gms.common.UserRecoverableException", "org.xms.g.common.UserRecoverableException");
        map.put("com.huawei.hms.api.UserRecoverableException", "org.xms.g.common.UserRecoverableException");
        map.put("com.google.android.gms.common.api.Api", "org.xms.g.common.api.Api");
        map.put("com.huawei.hms.api.Api", "org.xms.g.common.api.Api");
        map.put("com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions", "org.xms.g.common.api.Api$ApiOptions$HasAccountOptions$XImpl");
        map.put("com.huawei.hms.support.api.hwid.HuaweiAccountOptions.HasAccountOptions", "org.xms.g.common.api.Api$ApiOptions$HasAccountOptions$XImpl");
        map.put("com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions", "org.xms.g.common.api.Api$ApiOptions$HasExtensionSignInAccountOptions$XImpl");
        map.put("com.huawei.hms.support.api.hwid.HuaweiAccountOptions.HasAuthHuaweiIdOptions", "org.xms.g.common.api.Api$ApiOptions$HasExtensionSignInAccountOptions$XImpl");
        map.put("com.google.android.gms.common.api.Api.ApiOptions.HasOptions", "org.xms.g.common.api.Api$ApiOptions$HasOptions$XImpl");
        map.put("com.huawei.hms.api.Api.ApiOptions.HasOptions", "org.xms.g.common.api.Api$ApiOptions$HasOptions$XImpl");
        map.put("com.google.android.gms.common.api.Api.ApiOptions.NoOptions", "org.xms.g.common.api.Api$ApiOptions$NoOptions");
        map.put("com.huawei.hms.api.Api.ApiOptions.NoOptions", "org.xms.g.common.api.Api$ApiOptions$NoOptions");
        map.put("com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions", "org.xms.g.common.api.Api$ApiOptions$NotRequiredOptions$XImpl");
        map.put("com.huawei.hms.api.Api.ApiOptions.NotRequiredOptions", "org.xms.g.common.api.Api$ApiOptions$NotRequiredOptions$XImpl");
        map.put("com.google.android.gms.common.api.Api.ApiOptions.Optional", "org.xms.g.common.api.Api$ApiOptions$Optional$XImpl");
        map.put("com.huawei.hms.api.Api.ApiOptions.Optional", "org.xms.g.common.api.Api$ApiOptions$Optional$XImpl");
        map.put("com.google.android.gms.common.api.Api.ApiOptions", "org.xms.g.common.api.Api$ApiOptions$XImpl");
        map.put("com.huawei.hms.api.Api.ApiOptions", "org.xms.g.common.api.Api$ApiOptions$XImpl");
        map.put("com.google.android.gms.common.api.ApiException", "org.xms.g.common.api.ApiException");
        map.put("com.huawei.hms.common.ApiException", "org.xms.g.common.api.ApiException");
        map.put("com.google.android.gms.common.api.AvailabilityException", "org.xms.g.common.api.AvailabilityException");
        map.put("com.huawei.hms.common.api.AvailabilityException", "org.xms.g.common.api.AvailabilityException");
        map.put("com.google.android.gms.common.api.Batch", "org.xms.g.common.api.Batch");
        map.put("com.google.android.gms.common.api.Batch.Builder", "org.xms.g.common.api.Batch$Builder");
        map.put("com.google.android.gms.common.api.BatchResult", "org.xms.g.common.api.BatchResult");
        map.put("com.google.android.gms.common.api.BatchResultToken", "org.xms.g.common.api.BatchResultToken");
        map.put("com.google.android.gms.common.api.BooleanResult", "org.xms.g.common.api.BooleanResult");
        map.put("com.huawei.hms.common.api.BooleanResult", "org.xms.g.common.api.BooleanResult");
        map.put("com.google.android.gms.common.api.CommonStatusCodes", "org.xms.g.common.api.CommonStatusCodes");
        map.put("com.huawei.hms.common.api.CommonStatusCodes", "org.xms.g.common.api.CommonStatusCodes");
        map.put("com.google.android.gms.common.api.GoogleApi", "org.xms.g.common.api.ExtensionApi$XImpl");
        map.put("com.google.android.gms.common.api.GoogleApiClient.Builder", "org.xms.g.common.api.ExtensionApiClient$Builder");
        map.put("com.huawei.hms.api.HuaweiApiClient.Builder", "org.xms.g.common.api.ExtensionApiClient$Builder");
        map.put("com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks", "org.xms.g.common.api.ExtensionApiClient$ConnectionCallbacks$XImpl");
        map.put("com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks", "org.xms.g.common.api.ExtensionApiClient$ConnectionCallbacks$XImpl");
        map.put("com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener", "org.xms.g.common.api.ExtensionApiClient$OnConnectionFailedListener$XImpl");
        map.put("com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener", "org.xms.g.common.api.ExtensionApiClient$OnConnectionFailedListener$XImpl");
        map.put("com.google.android.gms.common.api.GoogleApiClient", "org.xms.g.common.api.ExtensionApiClient$XImpl");
        map.put("com.huawei.hms.api.HuaweiApiClient", "org.xms.g.common.api.ExtensionApiClient$XImpl");
        map.put("com.google.android.gms.common.api.HasApiKey", "org.xms.g.common.api.HasApiKey$XImpl");
        map.put("com.google.android.gms.common.api.OptionalPendingResult", "org.xms.g.common.api.OptionalPendingResult$XImpl");
        map.put("com.huawei.hms.common.api.OptionalPendingResult", "org.xms.g.common.api.OptionalPendingResult$XImpl");
        map.put("com.google.android.gms.common.api.PendingResult", "org.xms.g.common.api.PendingResult$XImpl");
        map.put("com.huawei.hms.support.api.client.PendingResult", "org.xms.g.common.api.PendingResult$XImpl");
        map.put("com.google.android.gms.common.api.PendingResults", "org.xms.g.common.api.PendingResults");
        map.put("com.huawei.hms.support.api.client.PendingResultsCreator", "org.xms.g.common.api.PendingResults");
        map.put("com.google.android.gms.common.api.Releasable", "org.xms.g.common.api.Releasable$XImpl");
        map.put("com.huawei.hms.common.api.Releasable", "org.xms.g.common.api.Releasable$XImpl");
        map.put("com.google.android.gms.common.api.ResolvableApiException", "org.xms.g.common.api.ResolvableApiException");
        map.put("com.huawei.hms.common.ResolvableApiException", "org.xms.g.common.api.ResolvableApiException");
        map.put("com.google.android.gms.common.api.ResolvingResultCallbacks", "org.xms.g.common.api.ResolvingResultCallbacks$XImpl");
        map.put("com.huawei.hms.support.api.client.ResolvingResultCallbacks", "org.xms.g.common.api.ResolvingResultCallbacks$XImpl");
        map.put("com.google.android.gms.common.api.Response", "org.xms.g.common.api.Response");
        map.put("com.huawei.hms.common.api.Response", "org.xms.g.common.api.Response");
        map.put("com.google.android.gms.common.api.Result", "org.xms.g.common.api.Result$XImpl");
        map.put("com.huawei.hms.support.api.client.Result", "org.xms.g.common.api.Result$XImpl");
        map.put("com.google.android.gms.common.api.ResultCallback", "org.xms.g.common.api.ResultCallback$XImpl");
        map.put("com.huawei.hms.support.api.client.ResultCallback", "org.xms.g.common.api.ResultCallback$XImpl");
        map.put("com.google.android.gms.common.api.ResultCallbacks", "org.xms.g.common.api.ResultCallbacks$XImpl");
        map.put("com.huawei.hms.support.api.client.ResultCallbacks", "org.xms.g.common.api.ResultCallbacks$XImpl");
        map.put("com.google.android.gms.common.api.ResultTransform", "org.xms.g.common.api.ResultTransform$XImpl");
        map.put("com.huawei.hms.support.api.client.ResultConvert", "org.xms.g.common.api.ResultTransform$XImpl");
        map.put("com.google.android.gms.common.api.Scope", "org.xms.g.common.api.Scope");
        map.put("com.huawei.hms.support.api.entity.auth.Scope", "org.xms.g.common.api.Scope");
        map.put("com.google.android.gms.common.api.Status", "org.xms.g.common.api.Status");
        map.put("com.huawei.hms.support.api.client.Status", "org.xms.g.common.api.Status");
        map.put("com.google.android.gms.common.api.TransformedResult", "org.xms.g.common.api.TransformedResult$XImpl");
        map.put("com.huawei.hms.support.api.client.ConvertedResult", "org.xms.g.common.api.TransformedResult$XImpl");
        map.put("com.google.android.gms.common.api.UnsupportedApiCallException", "org.xms.g.common.api.UnsupportedApiCallException");
        map.put("com.huawei.hms.common.api.UnsupportedApiCallException", "org.xms.g.common.api.UnsupportedApiCallException");
        map.put("com.google.android.gms.common.data.AbstractDataBuffer", "org.xms.g.common.data.AbstractDataBuffer$XImpl");
        map.put("com.huawei.hms.common.data.AbstractDataBuffer", "org.xms.g.common.data.AbstractDataBuffer$XImpl");
        map.put("com.google.android.gms.common.data.DataBuffer", "org.xms.g.common.data.DataBuffer$XImpl");
        map.put("com.huawei.hms.common.data.DataBuffer", "org.xms.g.common.data.DataBuffer$XImpl");
        map.put("com.google.android.gms.common.data.DataBufferObserver.Observable", "org.xms.g.common.data.DataBufferObserver$Observable$XImpl");
        map.put("com.google.android.gms.common.data.DataBufferObserver", "org.xms.g.common.data.DataBufferObserver$XImpl");
        map.put("com.huawei.hms.common.data.DataBufferObserver", "org.xms.g.common.data.DataBufferObserver$XImpl");
        map.put("com.google.android.gms.common.data.DataBufferObserverSet", "org.xms.g.common.data.DataBufferObserverSet");
        map.put("com.google.android.gms.common.data.DataBufferUtils", "org.xms.g.common.data.DataBufferUtils");
        map.put("com.huawei.hms.common.data.DataBufferUtils", "org.xms.g.common.data.DataBufferUtils");
        map.put("com.google.android.gms.common.data.Freezable", "org.xms.g.common.data.Freezable$XImpl");
        map.put("com.huawei.hms.common.data.Freezable", "org.xms.g.common.data.Freezable$XImpl");
        map.put("com.google.android.gms.common.data.FreezableUtils", "org.xms.g.common.data.FreezableUtils");
        map.put("com.huawei.hms.common.data.FreezableUtils", "org.xms.g.common.data.FreezableUtils");
        map.put("com.google.android.gms.common.images.ImageManager", "org.xms.g.common.images.ImageManager");
        map.put("com.google.android.gms.common.images.ImageManager.OnImageLoadedListener", "org.xms.g.common.images.ImageManager$OnImageLoadedListener$XImpl");
        map.put("com.google.android.gms.common.images.Size", "org.xms.g.common.images.Size");
        map.put("com.huawei.hms.common.size.Size", "org.xms.g.common.images.Size");
        map.put("com.google.android.gms.common.images.WebImage", "org.xms.g.common.images.WebImage");
        map.put("com.huawei.hms.common.webserverpic.WebServerPic", "org.xms.g.common.images.WebImage");
        map.put("com.google.android.gms.fitness.BleApi", "org.xms.g.fitness.BleApi$XImpl");
        map.put("com.google.android.gms.fitness.BleClient", "org.xms.g.fitness.BleClient");
        map.put("com.huawei.hms.hihealth.BleController", "org.xms.g.fitness.BleClient");
        map.put("com.google.android.gms.fitness.ConfigApi", "org.xms.g.fitness.ConfigApi$XImpl");
        map.put("com.google.android.gms.fitness.ConfigClient", "org.xms.g.fitness.ConfigClient");
        map.put("com.huawei.hms.hihealth.SettingController", "org.xms.g.fitness.ConfigClient");
        map.put("com.google.android.gms.fitness.Fitness", "org.xms.g.fitness.Fitness");
        map.put("com.huawei.hms.hihealth.HuaweiHiHealth", "org.xms.g.fitness.Fitness");
        map.put("com.google.android.gms.fitness.FitnessActivities", "org.xms.g.fitness.FitnessActivities");
        map.put("com.huawei.hms.hihealth.HiHealthActivities", "org.xms.g.fitness.FitnessActivities");
        map.put("com.google.android.gms.fitness.FitnessOptions", "org.xms.g.fitness.FitnessOptions");
        map.put("com.huawei.hms.hihealth.HiHealthOptions", "org.xms.g.fitness.FitnessOptions");
        map.put("com.google.android.gms.fitness.FitnessOptions.Builder", "org.xms.g.fitness.FitnessOptions$Builder");
        map.put("com.huawei.hms.hihealth.HiHealthOptions.Builder", "org.xms.g.fitness.FitnessOptions$Builder");
        map.put("com.google.android.gms.fitness.FitnessStatusCodes", "org.xms.g.fitness.FitnessStatusCodes");
        map.put("com.huawei.hms.hihealth.HiHealthStatusCodes", "org.xms.g.fitness.FitnessStatusCodes");
        map.put("com.google.android.gms.fitness.GoalsApi", "org.xms.g.fitness.GoalsApi$XImpl");
        map.put("com.google.android.gms.fitness.GoalsClient", "org.xms.g.fitness.GoalsClient");
        map.put("com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder", "org.xms.g.fitness.HistoryApi$ViewIntentBuilder");
        map.put("com.huawei.hms.hihealth.DataManager.GetIntentInfos", "org.xms.g.fitness.HistoryApi$ViewIntentBuilder");
        map.put("com.google.android.gms.fitness.HistoryApi", "org.xms.g.fitness.HistoryApi$XImpl");
        map.put("com.google.android.gms.fitness.HistoryClient", "org.xms.g.fitness.HistoryClient");
        map.put("com.huawei.hms.hihealth.DataController", "org.xms.g.fitness.HistoryClient");
        map.put("com.google.android.gms.fitness.RecordingApi", "org.xms.g.fitness.RecordingApi$XImpl");
        map.put("com.google.android.gms.fitness.RecordingClient", "org.xms.g.fitness.RecordingClient");
        map.put("com.huawei.hms.hihealth.AutoRecorderController", "org.xms.g.fitness.RecordingClient");
        map.put("com.google.android.gms.fitness.SensorsApi", "org.xms.g.fitness.SensorsApi$XImpl");
        map.put("com.google.android.gms.fitness.SensorsClient", "org.xms.g.fitness.SensorsClient");
        map.put("com.huawei.hms.hihealth.SensorsController", "org.xms.g.fitness.SensorsClient");
        map.put("com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder", "org.xms.g.fitness.SessionsApi$ViewIntentBuilder");
        map.put("com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos", "org.xms.g.fitness.SessionsApi$ViewIntentBuilder");
        map.put("com.google.android.gms.fitness.SessionsApi", "org.xms.g.fitness.SessionsApi$XImpl");
        map.put("com.google.android.gms.fitness.SessionsClient", "org.xms.g.fitness.SessionsClient");
        map.put("com.huawei.hms.hihealth.ActivityRecordsController", "org.xms.g.fitness.SessionsClient");
        map.put("com.google.android.gms.fitness.data.BleDevice", "org.xms.g.fitness.data.BleDevice");
        map.put("com.huawei.hms.hihealth.data.BleDeviceInfo", "org.xms.g.fitness.data.BleDevice");
        map.put("com.google.android.gms.fitness.data.Bucket", "org.xms.g.fitness.data.Bucket");
        map.put("com.huawei.hms.hihealth.data.Group", "org.xms.g.fitness.data.Bucket");
        map.put("com.google.android.gms.fitness.data.DataPoint", "org.xms.g.fitness.data.DataPoint");
        map.put("com.huawei.hms.hihealth.data.SamplePoint", "org.xms.g.fitness.data.DataPoint");
        map.put("com.google.android.gms.fitness.data.DataPoint.Builder", "org.xms.g.fitness.data.DataPoint$Builder");
        map.put("com.huawei.hms.hihealth.data.SamplePoint.Builder", "org.xms.g.fitness.data.DataPoint$Builder");
        map.put("com.google.android.gms.fitness.data.DataSet", "org.xms.g.fitness.data.DataSet");
        map.put("com.huawei.hms.hihealth.data.SampleSet", "org.xms.g.fitness.data.DataSet");
        map.put("com.google.android.gms.fitness.data.DataSet.Builder", "org.xms.g.fitness.data.DataSet$Builder");
        map.put("com.huawei.hms.hihealth.data.SampleSet.Builder", "org.xms.g.fitness.data.DataSet$Builder");
        map.put("com.google.android.gms.fitness.data.DataSource", "org.xms.g.fitness.data.DataSource");
        map.put("com.huawei.hms.hihealth.data.DataCollector", "org.xms.g.fitness.data.DataSource");
        map.put("com.google.android.gms.fitness.data.DataSource.Builder", "org.xms.g.fitness.data.DataSource$Builder");
        map.put("com.huawei.hms.hihealth.data.DataCollector.Builder", "org.xms.g.fitness.data.DataSource$Builder");
        map.put("com.google.android.gms.fitness.data.DataType", "org.xms.g.fitness.data.DataType");
        map.put("com.huawei.hms.hihealth.data.DataType", "org.xms.g.fitness.data.DataType");
        map.put("com.google.android.gms.fitness.data.DataUpdateNotification", "org.xms.g.fitness.data.DataUpdateNotification");
        map.put("com.huawei.hms.hihealth.data.DataModifyInfo", "org.xms.g.fitness.data.DataUpdateNotification");
        map.put("com.google.android.gms.fitness.data.Device", "org.xms.g.fitness.data.Device");
        map.put("com.huawei.hms.hihealth.data.DeviceInfo", "org.xms.g.fitness.data.Device");
        map.put("com.google.android.gms.fitness.data.Field", "org.xms.g.fitness.data.Field");
        map.put("com.huawei.hms.hihealth.data.Field", "org.xms.g.fitness.data.Field");
        map.put("com.google.android.gms.fitness.data.Goal", "org.xms.g.fitness.data.Goal");
        map.put("com.google.android.gms.fitness.data.Goal.DurationObjective", "org.xms.g.fitness.data.Goal$DurationObjective");
        map.put("com.google.android.gms.fitness.data.Goal.FrequencyObjective", "org.xms.g.fitness.data.Goal$FrequencyObjective");
        map.put("com.google.android.gms.fitness.data.Goal.MetricObjective", "org.xms.g.fitness.data.Goal$MetricObjective");
        map.put("com.google.android.gms.fitness.data.Goal.MismatchedGoalException", "org.xms.g.fitness.data.Goal$MismatchedGoalException");
        map.put("com.google.android.gms.fitness.data.Goal.Recurrence", "org.xms.g.fitness.data.Goal$Recurrence");
        map.put("com.google.android.gms.fitness.data.HealthDataTypes", "org.xms.g.fitness.data.HealthDataTypes");
        map.put("com.huawei.hms.hihealth.data.HealthDataTypes", "org.xms.g.fitness.data.HealthDataTypes");
        map.put("com.google.android.gms.fitness.data.HealthFields", "org.xms.g.fitness.data.HealthFields");
        map.put("com.huawei.hms.hihealth.data.HealthFields", "org.xms.g.fitness.data.HealthFields");
        map.put("com.google.android.gms.fitness.data.Session", "org.xms.g.fitness.data.Session");
        map.put("com.huawei.hms.hihealth.data.ActivityRecord", "org.xms.g.fitness.data.Session");
        map.put("com.google.android.gms.fitness.data.Session.Builder", "org.xms.g.fitness.data.Session$Builder");
        map.put("com.huawei.hms.hihealth.data.ActivityRecord.Builder", "org.xms.g.fitness.data.Session$Builder");
        map.put("com.google.android.gms.fitness.data.Subscription", "org.xms.g.fitness.data.Subscription");
        map.put("com.huawei.hms.hihealth.data.Record", "org.xms.g.fitness.data.Subscription");
        map.put("com.google.android.gms.fitness.data.Value", "org.xms.g.fitness.data.Value");
        map.put("com.huawei.hms.hihealth.data.Value", "org.xms.g.fitness.data.Value");
        map.put("com.google.android.gms.fitness.data.WorkoutExercises", "org.xms.g.fitness.data.WorkoutExercises");
        map.put("com.google.android.gms.fitness.request.BleScanCallback", "org.xms.g.fitness.request.BleScanCallback$XImpl");
        map.put("com.huawei.hms.hihealth.options.BleScanCallback", "org.xms.g.fitness.request.BleScanCallback$XImpl");
        map.put("com.google.android.gms.fitness.request.DataDeleteRequest", "org.xms.g.fitness.request.DataDeleteRequest");
        map.put("com.huawei.hms.hihealth.options.DeleteOptions", "org.xms.g.fitness.request.DataDeleteRequest");
        map.put("com.google.android.gms.fitness.request.DataDeleteRequest.Builder", "org.xms.g.fitness.request.DataDeleteRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.DeleteOptions.Builder", "org.xms.g.fitness.request.DataDeleteRequest$Builder");
        map.put("com.google.android.gms.fitness.request.DataReadRequest", "org.xms.g.fitness.request.DataReadRequest");
        map.put("com.huawei.hms.hihealth.options.ReadOptions", "org.xms.g.fitness.request.DataReadRequest");
        map.put("com.google.android.gms.fitness.request.DataReadRequest.Builder", "org.xms.g.fitness.request.DataReadRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.ReadOptions.Builder", "org.xms.g.fitness.request.DataReadRequest$Builder");
        map.put("com.google.android.gms.fitness.request.DataSourcesRequest", "org.xms.g.fitness.request.DataSourcesRequest");
        map.put("com.huawei.hms.hihealth.options.DataCollectorsOptions", "org.xms.g.fitness.request.DataSourcesRequest");
        map.put("com.google.android.gms.fitness.request.DataSourcesRequest.Builder", "org.xms.g.fitness.request.DataSourcesRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder", "org.xms.g.fitness.request.DataSourcesRequest$Builder");
        map.put("com.google.android.gms.fitness.request.DataTypeCreateRequest", "org.xms.g.fitness.request.DataTypeCreateRequest");
        map.put("com.huawei.hms.hihealth.options.DataTypeAddOptions", "org.xms.g.fitness.request.DataTypeCreateRequest");
        map.put("com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder", "org.xms.g.fitness.request.DataTypeCreateRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder", "org.xms.g.fitness.request.DataTypeCreateRequest$Builder");
        map.put("com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest", "org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest");
        map.put("com.huawei.hms.hihealth.options.ModifyDataMonitorOptions", "org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest");
        map.put("com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder", "org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder", "org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest$Builder");
        map.put("com.google.android.gms.fitness.request.DataUpdateRequest", "org.xms.g.fitness.request.DataUpdateRequest");
        map.put("com.huawei.hms.hihealth.options.UpdateOptions", "org.xms.g.fitness.request.DataUpdateRequest");
        map.put("com.google.android.gms.fitness.request.DataUpdateRequest.Builder", "org.xms.g.fitness.request.DataUpdateRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.UpdateOptions.Builder", "org.xms.g.fitness.request.DataUpdateRequest$Builder");
        map.put("com.google.android.gms.fitness.request.GoalsReadRequest", "org.xms.g.fitness.request.GoalsReadRequest");
        map.put("com.google.android.gms.fitness.request.GoalsReadRequest.Builder", "org.xms.g.fitness.request.GoalsReadRequest$Builder");
        map.put("com.google.android.gms.fitness.request.OnDataPointListener", "org.xms.g.fitness.request.OnDataPointListener$XImpl");
        map.put("com.huawei.hms.hihealth.options.OnSamplePointListener", "org.xms.g.fitness.request.OnDataPointListener$XImpl");
        map.put("com.google.android.gms.fitness.request.SensorRequest", "org.xms.g.fitness.request.SensorRequest");
        map.put("com.huawei.hms.hihealth.options.SensorOptions", "org.xms.g.fitness.request.SensorRequest");
        map.put("com.google.android.gms.fitness.request.SensorRequest.Builder", "org.xms.g.fitness.request.SensorRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.SensorOptions.Builder", "org.xms.g.fitness.request.SensorRequest$Builder");
        map.put("com.google.android.gms.fitness.request.SessionInsertRequest", "org.xms.g.fitness.request.SessionInsertRequest");
        map.put("com.huawei.hms.hihealth.options.ActivityRecordInsertOptions", "org.xms.g.fitness.request.SessionInsertRequest");
        map.put("com.google.android.gms.fitness.request.SessionInsertRequest.Builder", "org.xms.g.fitness.request.SessionInsertRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder", "org.xms.g.fitness.request.SessionInsertRequest$Builder");
        map.put("com.google.android.gms.fitness.request.SessionReadRequest", "org.xms.g.fitness.request.SessionReadRequest");
        map.put("com.huawei.hms.hihealth.options.ActivityRecordReadOptions", "org.xms.g.fitness.request.SessionReadRequest");
        map.put("com.google.android.gms.fitness.request.SessionReadRequest.Builder", "org.xms.g.fitness.request.SessionReadRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder", "org.xms.g.fitness.request.SessionReadRequest$Builder");
        map.put("com.google.android.gms.fitness.request.StartBleScanRequest", "org.xms.g.fitness.request.StartBleScanRequest");
        map.put("com.huawei.hms.hihealth.options.StartBleScanOptions", "org.xms.g.fitness.request.StartBleScanRequest");
        map.put("com.google.android.gms.fitness.request.StartBleScanRequest.Builder", "org.xms.g.fitness.request.StartBleScanRequest$Builder");
        map.put("com.huawei.hms.hihealth.options.StartBleScanOptions.Builder", "org.xms.g.fitness.request.StartBleScanRequest$Builder");
        map.put("com.google.android.gms.fitness.result.BleDevicesResult", "org.xms.g.fitness.result.BleDevicesResult");
        map.put("com.huawei.hms.hihealth.result.BleDeviceInfosResult", "org.xms.g.fitness.result.BleDevicesResult");
        map.put("com.google.android.gms.fitness.result.DailyTotalResult", "org.xms.g.fitness.result.DailyTotalResult");
        map.put("com.huawei.hms.hihealth.result.DailyStatisticsResult", "org.xms.g.fitness.result.DailyTotalResult");
        map.put("com.google.android.gms.fitness.result.DataReadResponse", "org.xms.g.fitness.result.DataReadResponse");
        map.put("com.huawei.hms.hihealth.result.ReadReply", "org.xms.g.fitness.result.DataReadResponse");
        map.put("com.google.android.gms.fitness.result.DataReadResult", "org.xms.g.fitness.result.DataReadResult");
        map.put("com.huawei.hms.hihealth.result.ReadDetailResult", "org.xms.g.fitness.result.DataReadResult");
        map.put("com.google.android.gms.fitness.result.DataSourcesResult", "org.xms.g.fitness.result.DataSourcesResult");
        map.put("com.huawei.hms.hihealth.result.DataCollectorsResult", "org.xms.g.fitness.result.DataSourcesResult");
        map.put("com.google.android.gms.fitness.result.DataTypeResult", "org.xms.g.fitness.result.DataTypeResult");
        map.put("com.huawei.hms.hihealth.result.DataTypeResult", "org.xms.g.fitness.result.DataTypeResult");
        map.put("com.google.android.gms.fitness.result.GoalsResult", "org.xms.g.fitness.result.GoalsResult");
        map.put("com.google.android.gms.fitness.result.ListSubscriptionsResult", "org.xms.g.fitness.result.ListSubscriptionsResult");
        map.put("com.huawei.hms.hihealth.result.ListRecordsResult", "org.xms.g.fitness.result.ListSubscriptionsResult");
        map.put("com.google.android.gms.fitness.result.SessionReadResponse", "org.xms.g.fitness.result.SessionReadResponse");
        map.put("com.huawei.hms.hihealth.result.ActivityRecordReply", "org.xms.g.fitness.result.SessionReadResponse");
        map.put("com.google.android.gms.fitness.result.SessionReadResult", "org.xms.g.fitness.result.SessionReadResult");
        map.put("com.huawei.hms.hihealth.result.ActivityRecordResult", "org.xms.g.fitness.result.SessionReadResult");
        map.put("com.google.android.gms.fitness.result.SessionStopResult", "org.xms.g.fitness.result.SessionStopResult");
        map.put("com.huawei.hms.hihealth.result.ActivityRecordStopResult", "org.xms.g.fitness.result.SessionStopResult");
        map.put("com.google.android.gms.fitness.service.FitnessSensorService", "org.xms.g.fitness.service.FitnessSensorService$XImpl");
        map.put("com.google.android.gms.fitness.service.FitnessSensorServiceRequest", "org.xms.g.fitness.service.FitnessSensorServiceRequest");
        map.put("com.google.android.gms.fitness.service.SensorEventDispatcher", "org.xms.g.fitness.service.SensorEventDispatcher$XImpl");
        map.put("com.google.android.gms.gcm.GcmNetworkManager", "org.xms.g.gcm.GcmNetworkManager");
        map.put("com.google.android.gms.gcm.GcmTaskService", "org.xms.g.gcm.GcmTaskService$XImpl");
        map.put("com.google.android.gms.gcm.OneoffTask", "org.xms.g.gcm.OneoffTask");
        map.put("com.google.android.gms.gcm.OneoffTask.Builder", "org.xms.g.gcm.OneoffTask$Builder");
        map.put("com.google.android.gms.gcm.PeriodicTask", "org.xms.g.gcm.PeriodicTask");
        map.put("com.google.android.gms.gcm.PeriodicTask.Builder", "org.xms.g.gcm.PeriodicTask$Builder");
        map.put("com.google.android.gms.gcm.Task", "org.xms.g.gcm.Task");
        map.put("com.google.android.gms.gcm.Task.Builder", "org.xms.g.gcm.Task$Builder$XImpl");
        map.put("com.google.android.gms.gcm.TaskParams", "org.xms.g.gcm.TaskParams");
        map.put("com.google.android.gms.iid.InstanceID", "org.xms.g.iid.InstanceID");
        map.put("com.google.android.gms.iid.InstanceIDListenerService", "org.xms.g.iid.InstanceIDListenerService");
        map.put("com.google.android.gms.location.ActivityRecognition", "org.xms.g.location.ActivityRecognition");
        map.put("com.huawei.hms.location.ActivityIdentification", "org.xms.g.location.ActivityRecognition");
        map.put("com.google.android.gms.location.ActivityRecognitionApi", "org.xms.g.location.ActivityRecognitionApi$XImpl");
        map.put("com.google.android.gms.location.ActivityRecognitionClient", "org.xms.g.location.ActivityRecognitionClient");
        map.put("com.huawei.hms.location.ActivityIdentificationService", "org.xms.g.location.ActivityRecognitionClient");
        map.put("com.google.android.gms.location.ActivityRecognitionResult", "org.xms.g.location.ActivityRecognitionResult");
        map.put("com.huawei.hms.location.ActivityIdentificationResponse", "org.xms.g.location.ActivityRecognitionResult");
        map.put("com.google.android.gms.location.ActivityTransition", "org.xms.g.location.ActivityTransition");
        map.put("com.huawei.hms.location.ActivityConversionInfo", "org.xms.g.location.ActivityTransition");
        map.put("com.google.android.gms.location.ActivityTransition.Builder", "org.xms.g.location.ActivityTransition$Builder");
        map.put("com.huawei.hms.location.ActivityConversionInfo.Builder", "org.xms.g.location.ActivityTransition$Builder");
        map.put("com.google.android.gms.location.ActivityTransitionEvent", "org.xms.g.location.ActivityTransitionEvent");
        map.put("com.huawei.hms.location.ActivityConversionData", "org.xms.g.location.ActivityTransitionEvent");
        map.put("com.google.android.gms.location.ActivityTransitionRequest", "org.xms.g.location.ActivityTransitionRequest");
        map.put("com.huawei.hms.location.ActivityConversionRequest", "org.xms.g.location.ActivityTransitionRequest");
        map.put("com.google.android.gms.location.ActivityTransitionResult", "org.xms.g.location.ActivityTransitionResult");
        map.put("com.huawei.hms.location.ActivityConversionResponse", "org.xms.g.location.ActivityTransitionResult");
        map.put("com.google.android.gms.location.DetectedActivity", "org.xms.g.location.DetectedActivity");
        map.put("com.huawei.hms.location.ActivityIdentificationData", "org.xms.g.location.DetectedActivity");
        map.put("com.google.android.gms.location.FusedLocationProviderApi", "org.xms.g.location.FusedLocationProviderApi$XImpl");
        map.put("com.google.android.gms.location.FusedLocationProviderClient", "org.xms.g.location.FusedLocationProviderClient");
        map.put("com.huawei.hms.location.FusedLocationProviderClient", "org.xms.g.location.FusedLocationProviderClient");
        map.put("com.google.android.gms.location.Geofence.Builder", "org.xms.g.location.Geofence$Builder");
        map.put("com.huawei.hms.location.Geofence.Builder", "org.xms.g.location.Geofence$Builder");
        map.put("com.google.android.gms.location.Geofence", "org.xms.g.location.Geofence$XImpl");
        map.put("com.huawei.hms.location.Geofence", "org.xms.g.location.Geofence$XImpl");
        map.put("com.google.android.gms.location.GeofenceStatusCodes", "org.xms.g.location.GeofenceStatusCodes");
        map.put("com.huawei.hms.location.GeofenceErrorCodes", "org.xms.g.location.GeofenceStatusCodes");
        map.put("com.google.android.gms.location.GeofencingApi", "org.xms.g.location.GeofencingApi$XImpl");
        map.put("com.google.android.gms.location.GeofencingClient", "org.xms.g.location.GeofencingClient");
        map.put("com.huawei.hms.location.GeofenceService", "org.xms.g.location.GeofencingClient");
        map.put("com.google.android.gms.location.GeofencingEvent", "org.xms.g.location.GeofencingEvent");
        map.put("com.huawei.hms.location.GeofenceData", "org.xms.g.location.GeofencingEvent");
        map.put("com.google.android.gms.location.GeofencingRequest", "org.xms.g.location.GeofencingRequest");
        map.put("com.huawei.hms.location.GeofenceRequest", "org.xms.g.location.GeofencingRequest");
        map.put("com.google.android.gms.location.GeofencingRequest.Builder", "org.xms.g.location.GeofencingRequest$Builder");
        map.put("com.huawei.hms.location.GeofenceRequest.Builder", "org.xms.g.location.GeofencingRequest$Builder");
        map.put("com.google.android.gms.location.LocationAvailability", "org.xms.g.location.LocationAvailability");
        map.put("com.huawei.hms.location.LocationAvailability", "org.xms.g.location.LocationAvailability");
        map.put("com.google.android.gms.location.LocationCallback", "org.xms.g.location.LocationCallback");
        map.put("com.huawei.hms.location.LocationCallback", "org.xms.g.location.LocationCallback");
        map.put("com.google.android.gms.location.LocationListener", "org.xms.g.location.LocationListener$XImpl");
        map.put("com.google.android.gms.location.LocationRequest", "org.xms.g.location.LocationRequest");
        map.put("com.huawei.hms.location.LocationRequest", "org.xms.g.location.LocationRequest");
        map.put("com.google.android.gms.location.LocationResult", "org.xms.g.location.LocationResult");
        map.put("com.huawei.hms.location.LocationResult", "org.xms.g.location.LocationResult");
        map.put("com.google.android.gms.location.LocationServices", "org.xms.g.location.LocationServices");
        map.put("com.huawei.hms.location.LocationServices", "org.xms.g.location.LocationServices");
        map.put("com.google.android.gms.location.LocationSettingsRequest", "org.xms.g.location.LocationSettingsRequest");
        map.put("com.huawei.hms.location.LocationSettingsRequest", "org.xms.g.location.LocationSettingsRequest");
        map.put("com.google.android.gms.location.LocationSettingsRequest.Builder", "org.xms.g.location.LocationSettingsRequest$Builder");
        map.put("com.huawei.hms.location.LocationSettingsRequest.Builder", "org.xms.g.location.LocationSettingsRequest$Builder");
        map.put("com.google.android.gms.location.LocationSettingsResponse", "org.xms.g.location.LocationSettingsResponse");
        map.put("com.huawei.hms.location.LocationSettingsResponse", "org.xms.g.location.LocationSettingsResponse");
        map.put("com.google.android.gms.location.LocationSettingsResult", "org.xms.g.location.LocationSettingsResult");
        map.put("com.huawei.hms.location.LocationSettingsResult", "org.xms.g.location.LocationSettingsResult");
        map.put("com.google.android.gms.location.LocationSettingsStates", "org.xms.g.location.LocationSettingsStates");
        map.put("com.huawei.hms.location.LocationSettingsStates", "org.xms.g.location.LocationSettingsStates");
        map.put("com.google.android.gms.location.LocationSettingsStatusCodes", "org.xms.g.location.LocationSettingsStatusCodes");
        map.put("com.huawei.hms.location.LocationSettingsStatusCodes", "org.xms.g.location.LocationSettingsStatusCodes");
        map.put("com.google.android.gms.location.LocationStatusCodes", "org.xms.g.location.LocationStatusCodes");
        map.put("com.huawei.hms.location.GeofenceErrorCodes", "org.xms.g.location.LocationStatusCodes");
        map.put("com.google.android.gms.location.SettingsApi", "org.xms.g.location.SettingsApi$XImpl");
        map.put("com.google.android.gms.location.SettingsClient", "org.xms.g.location.SettingsClient");
        map.put("com.huawei.hms.location.SettingsClient", "org.xms.g.location.SettingsClient");
        map.put("com.google.android.gms.measurement.AppMeasurementContentProvider", "org.xms.g.measurement.AppMeasurementContentProvider");
        map.put("com.google.android.gms.measurement.AppMeasurementInstallReferrerReceiver", "org.xms.g.measurement.AppMeasurementInstallReferrerReceiver");
        map.put("com.google.android.gms.measurement.AppMeasurementJobService", "org.xms.g.measurement.AppMeasurementJobService");
        map.put("com.google.android.gms.measurement.AppMeasurementReceiver", "org.xms.g.measurement.AppMeasurementReceiver");
        map.put("com.google.android.gms.measurement.AppMeasurementService", "org.xms.g.measurement.AppMeasurementService");
        map.put("com.google.android.gms.security.ProviderInstaller", "org.xms.g.security.ProviderInstaller");
        map.put("com.huawei.hms.security.SecComponentInstallWizard", "org.xms.g.security.ProviderInstaller");
        map.put("com.google.android.gms.security.ProviderInstaller.ProviderInstallListener", "org.xms.g.security.ProviderInstaller$ProviderInstallListener$XImpl");
        map.put("com.huawei.hms.security.SecComponentInstallWizard.SecComponentInstallWizardListener", "org.xms.g.security.ProviderInstaller$ProviderInstallListener$XImpl");
        map.put("com.google.android.gms.tasks.CancellationToken", "org.xms.g.tasks.CancellationToken$XImpl");
        map.put("com.huawei.hmf.tasks.CancellationToken", "org.xms.g.tasks.CancellationToken$XImpl");
        map.put("com.google.android.gms.tasks.CancellationTokenSource", "org.xms.g.tasks.CancellationTokenSource");
        map.put("com.huawei.hmf.tasks.CancellationTokenSource", "org.xms.g.tasks.CancellationTokenSource");
        map.put("com.google.android.gms.tasks.Continuation", "org.xms.g.tasks.Continuation$XImpl");
        map.put("com.huawei.hmf.tasks.Continuation", "org.xms.g.tasks.Continuation$XImpl");
        map.put("com.google.android.gms.tasks.OnCanceledListener", "org.xms.g.tasks.OnCanceledListener$XImpl");
        map.put("com.huawei.hmf.tasks.OnCanceledListener", "org.xms.g.tasks.OnCanceledListener$XImpl");
        map.put("com.google.android.gms.tasks.OnCompleteListener", "org.xms.g.tasks.OnCompleteListener$XImpl");
        map.put("com.huawei.hmf.tasks.OnCompleteListener", "org.xms.g.tasks.OnCompleteListener$XImpl");
        map.put("com.google.android.gms.tasks.OnFailureListener", "org.xms.g.tasks.OnFailureListener$XImpl");
        map.put("com.huawei.hmf.tasks.OnFailureListener", "org.xms.g.tasks.OnFailureListener$XImpl");
        map.put("com.google.android.gms.tasks.OnSuccessListener", "org.xms.g.tasks.OnSuccessListener$XImpl");
        map.put("com.huawei.hmf.tasks.OnSuccessListener", "org.xms.g.tasks.OnSuccessListener$XImpl");
        map.put("com.google.android.gms.tasks.OnTokenCanceledListener", "org.xms.g.tasks.OnTokenCanceledListener$XImpl");
        map.put("com.google.android.gms.tasks.RuntimeExecutionException", "org.xms.g.tasks.RuntimeExecutionException");
        map.put("com.google.android.gms.tasks.SuccessContinuation", "org.xms.g.tasks.SuccessContinuation$XImpl");
        map.put("com.huawei.hmf.tasks.SuccessContinuation", "org.xms.g.tasks.SuccessContinuation$XImpl");
        map.put("com.google.android.gms.tasks.Task", "org.xms.g.tasks.Task$XImpl");
        map.put("com.huawei.hmf.tasks.Task", "org.xms.g.tasks.Task$XImpl");
        map.put("com.google.android.gms.tasks.TaskCompletionSource", "org.xms.g.tasks.TaskCompletionSource");
        map.put("com.huawei.hmf.tasks.TaskCompletionSource", "org.xms.g.tasks.TaskCompletionSource");
        map.put("com.google.android.gms.tasks.TaskExecutors", "org.xms.g.tasks.TaskExecutors");
        map.put("com.google.android.gms.tasks.Tasks", "org.xms.g.tasks.Tasks");
        map.put("com.huawei.hmf.tasks.Tasks", "org.xms.g.tasks.Tasks");
        
        
    }
}
