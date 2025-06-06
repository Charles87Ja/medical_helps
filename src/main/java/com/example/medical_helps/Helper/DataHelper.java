package com.example.medical_helps.Helper;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

public class DataHelper extends HashMap implements Map{

    private static final long serialVersionUID = 1L;

    Map map = null;
    HttpServletRequest request;
    public DataHelper(HttpServletRequest request){
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new LinkedHashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        map = returnMap;
    }

    /**
     * 下划线转驼峰
     * @param underscoreName
     * @return
     */
    public  String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            String[] arrs = underscoreName.split("_");
            for (int i = 0; i < arrs.length; i++){
                String str =  arrs[i];
                if(i == 0 ){
                    result.append(Character.isLowerCase(str.charAt(0))?str:str.toLowerCase());
                }else{
                    result.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1).toLowerCase());
                }
            }

        }
        return result.toString();
    }

    public DataHelper() {
        map = new LinkedHashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        if(map.get(key) instanceof Object[]) {
            Object[] arr = (Object[])map.get(key);
            obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    /**
     * 返回字符串数据
     * @param key
     * @return
     */
    public String getString(Object key) {
        return (String)get(key);
    }


    /**
     * @param key
     * @return
     */
    public Long getLong(Object key){
        return (Long)get(key);
    }

    /**
     * @param key
     * @return
     */
    public Double getDouble(Object key){
        return get(key)!=null?new BigDecimal(get(key).toString()).doubleValue():null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        key = camelCaseName((String)key);
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return map.containsValue(value);
    }

    public Set entrySet() {
        // TODO Auto-generated method stub
        return map.entrySet();
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return map.isEmpty();
    }

    public Set keySet() {
        // TODO Auto-generated method stub
        return map.keySet();
    }

    @SuppressWarnings("unchecked")
    public void putAll(Map t) {
        // TODO Auto-generated method stub
        map.putAll(t);
    }

    public int size() {
        // TODO Auto-generated method stub
        return map.size();
    }

    public Collection values() {
        // TODO Auto-generated method stub
        return map.values();
    }

}

