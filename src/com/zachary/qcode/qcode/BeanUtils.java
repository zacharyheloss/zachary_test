package com.zachary.qcode;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author zhangcheng
 * @describe 集合处理类
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BeanUtils implements Converter {
	// 日志类

	/**
	 * 
	 * @Title: cglibCopyProperties
	 * @Description: 缓存copy，比spring copy快0.12s
	 * @return void
	 */
	public static void cglibCopyProperties(Class sClass, Class tClass,
			Object source, Object target, boolean useConverter) {
		if (source == null)
			return;

		BeanCopier copier = BeanCopier.create(sClass, tClass, useConverter);
		if (useConverter) {
			copier.copy(source, target, new BeanUtils());
		} else {
			copier.copy(source, target, null);
		}
		copier = null;

	}

	/**
	 * 
	 * @Title: cglibCopyPropertiesList
	 * @Description: 缓存copy，比spring copy快0.12s
	 * @return void
	 */
	public static <T> List<T> cglibCopyPropertiesList(Class sClass,
			Class tClass, Object sourceList, boolean useConverter) {

		List<T> result = new ArrayList<T>();// 实例化返回结果
		if (isEmptyContainer(sourceList))
			return result;

		if (sourceList instanceof List) {// 数组
			List<T> list = (List<T>) sourceList;
			for (T ts : list) {
				try {
					T tt = (T) tClass.newInstance();
					cglibCopyProperties(sClass, tClass, ts, tt, useConverter);
					result.add(tt);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: isEmptyContainer
	 * @Description: 判断集合 从写collectionutils
	 * @return void
	 */
	public final static boolean isEmptyContainer(Object container) {
		if (container == null) {
			return true;
		}
		if (container.getClass().isArray()) {
			return Array.getLength(container) == 0;
		}
		if (container instanceof Collection) {
			return ((Collection) container).size() == 0;
		}
		if (container instanceof Map) {
			return ((Map) container).size() == 0;
		}
		return false;
	}

	public Object convert(Object value, Class target, Object context) {
		if (target.equals(Integer.TYPE)) {
			if (value == null) {
				value = 0;
			}
		} else if (target.equals(String.class)) {
			if (value != null) {
				value = value + "";
			}
		}
		return value;
	}

	/**
	 * 判断集合大小
	 * 
	 * @param list
	 * @return
	 */
	public static final Integer size(Object list) {
		if (list == null) {
			return Integer.valueOf(0);
		}
		if (list.getClass().isArray()) {
			return new Integer(Array.getLength(list));
		}
		if (list instanceof Collection) {
			return Integer.valueOf(((Collection) list).size());
		}
		if (list instanceof Map) {
			return Integer.valueOf(((Map) list).size());
		} else {
			return Integer.valueOf(0);
		}
	}

	/**
	 * @desc: 获取list中记录数=条数
	 * @param：
	 */
	public static <T> List<T> getSubList(List<T> list, int from, int maxnum) {
		if (list == null || list.size() <= from) {
			return new ArrayList<T>();
		}
		return new ArrayList(list.subList(from, Math.min(maxnum, list.size())));
	}

	/**
	 * @desc: 判断是否json
	 * @param：
	 */
	public static boolean isJsonString(String jsonStr) {
		try {
			JSONObject.parse(jsonStr);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @desc: 拼接字符串
	 * @param:separator 链接符号
	 */
	public final static <T> String segmentationChar(List<T> collection,
			String separator) {

		return isEmptyContainer(collection) ? "" : segmentaValue(collection,
				separator);
	}

	/**
	 * @desc: 拼接字符串
	 * @param:separator 链接符号
	 */
	public final static <T> String segmentationChar(String[] splitList,
			String separator) {

		return isEmptyContainer(splitList) ? "" : segmentaValue(
				Arrays.asList(splitList), separator);
	}

	private static <T> String segmentaValue(List<T> dataList, String separator) {
		StringBuffer sbf = new StringBuffer();
		for (T data : dataList) {
			if (data != null) {
				sbf.append(data + separator);
			}
		}
		return sbf.deleteCharAt(sbf.length() - 1).toString();
	}

	/**
	 * @desc: 获取列表集合指定属性数据
	 * @param:propertyName 属性名称，unique是否可以重复（true不可以，false可以）
	 */
	public static <T> List getPropertyValueList(Collection<T> beanList,
			String propertyName, boolean unique) {
		List result = new ArrayList();
		if (beanList == null) {
			return result;
		}
		Iterator<T> beanItor = beanList.iterator();
		do {
			if (!beanItor.hasNext()) {
				break;
			}
			Object bean = beanItor.next();
			try {
				Object pv = getPropertyValue(bean, propertyName);
				if (pv != null && (!unique || !result.contains(pv))) {
					result.add(pv);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);
		return result;
	}

	private static Field[] getFildArray(Object bean) {

		// 声明全局集合
		List<Field> fileList = new ArrayList<Field>();
		// 获取当前类
		Field[] fields = bean.getClass().getDeclaredFields();
		// 获取父类
		Field[] superfields = bean.getClass().getSuperclass()
				.getDeclaredFields();
		Collections.addAll(fileList, fields);
		Collections.addAll(fileList, superfields);

		Field[] result = fileList.toArray(new Field[fileList.size()]);
		Field.setAccessible(result, true);
		return result;
	}

	/**
	 * @desc: 获取对象指定属性数据
	 * @param:propertyName 属性名称
	 */
	public static <T> Object getPropertyValue(T bean, String propertyName) {
		if (bean == null) {
			return null;
		}
		if (bean instanceof Map) {
			return ((Map) bean).get(propertyName);
		}
		Object obj = null;
		for (Field field : getFildArray(bean)) {
			if (propertyName.equals(field.getName())) {
				try {
					obj = field.get(bean);
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				}
			}
		}
		return obj;
	}

	/**
	 * @desc: 对象去除null
	 * @param:propertyName 属性名称
	 */
	public static <T> void beanIsEmpty(T bean) {
		if (bean == null) {
			return;
		}
		for (Field field : getFildArray(bean)) {
			try {
				if (field.get(bean) == null) {
					if (field.getType().getCanonicalName().endsWith("Long")) {
						field.set(bean, 0l);
					} else if (field.getType().getCanonicalName()
							.endsWith("Integer")) {
						field.set(bean, 0);
					} else if (field.getType().getCanonicalName()
							.endsWith("String")) {
						field.set(bean, "");
					} else if (field.getType().getCanonicalName()
							.endsWith("Date")) {
						field.set(bean, DateUtils.getCurrentDate());
					} else if (field.getType().getCanonicalName()
							.endsWith("Boolean")) {
						field.set(bean, false);
					}
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}

		}
	}

	/**
	 * @desc: 分组groupby
	 * @param:property 属性名称
	 */
	public static Map groupBeanList(Collection beanList, String property) {
		return groupBeanList(beanList, property, null);
	}

	private static Map groupBeanList(Collection beanList, String property,
			Object nullKey) {
		Map result = new LinkedHashMap();
		Iterator it = beanList.iterator();
		do {
			if (!it.hasNext()) {
				break;
			}
			Object bean = it.next();
			try {
				Object keyvalue = getPropertyValue(bean, property);
				if (keyvalue == null) {
					keyvalue = nullKey;
				}
				if (keyvalue != null) {
					List tmpList = (List) result.get(keyvalue + "");
					if (tmpList == null) {
						tmpList = new ArrayList();
						result.put(keyvalue + "", tmpList);
					}
					tmpList.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);
		return result;
	}

	/**
	 * @desc: 获取map指定k，v数据
	 * @param:
	 */
	public static Map getObjectPropertyMap(Collection beanList, String keyname,
			String valuename) {
		Map result = new HashMap();
		if (beanList == null) {
			return result;
		}
		Iterator it = beanList.iterator();
		do {
			if (!it.hasNext()) {
				break;
			}
			Object bean = it.next();
			try {
				if (bean != null) {
					result.put(getPropertyValue(bean, keyname) + "",
							getPropertyValue(bean, valuename) + "");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (true);
		return result;
	}

	/**
	 * @desc: 单属性去除null
	 * @param:
	 */
	public static <T> T trimIsEmpty(Object obj, Class<T> clazz) {
		if (obj == null) {
			if (clazz.getName().contains("String")) {
				obj = "";
			} else if (clazz.getName().contains("Integer")) {
				obj = 0;
			} else if (clazz.getName().contains("Long")) {
				obj = 0l;
			} else if (clazz.getName().contains("Date")) {
				obj = DateUtils.getCurrentDate();
			} else if (clazz.getName().contains("Boolean")) {
				obj = false;
			}

		}
		return (T) obj;
	}

	/**
	 * 去掉空格
	 * 
	 * @param obj
	 * @return
	 */
	public static String trimStr(String obj) {
		return StringUtils.isNotBlank(obj) ? ("null".equalsIgnoreCase(obj
				.trim()) ? "" : obj.trim()) : "";
	}

}
