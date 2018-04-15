package com.zachary.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * @Title: ExtComparator.java
 * @Description: 排序
 * @author zhangcheng
 * @version V1.0
 * @demo(Collections.sort(List<Object>,new ExtComparator(new String[] {
 *                                         "Object.A1", "Object.A2" }, new
 *                                         boolean[] { false, true }));)
 */
public class ListComparator implements Comparator<Object> {
	private String properties[];
	private boolean asc[];
	private int length;

	/***
	 * 
	 * @param properties
	 *            bean对应属性
	 * @param asc
	 *            【true 升序 false 降序】
	 */
	public ListComparator(String properties[], boolean asc[]) {
		this.properties = null;
		length = 0;
		this.properties = properties;
		length = Math.min(properties.length, asc.length);
		this.asc = asc;
	}

	/**
	 * 比较对象大小
	 * @param o1
	 * @param o2
	 * o1 > o2 return 1
	 * o1 = o2 return 0
	 * o1 < o2 return -1
	 */
	@SuppressWarnings("unchecked")
	public int compare(Object o1, Object o2) {
		int result = 0;
		if (o1 != null && o2 == null)
			result = 1;
		else if (o1 == null && o2 != null)
			result = -1;
		else if (o1 != null) {
			for (int i = 0; i < length; i++)
				try {
					Comparable<Object> p1 = (Comparable<Object>) PropertyUtils.getProperty(o1,
							properties[i]);
					Comparable<Object> p2 = (Comparable<Object>) PropertyUtils.getProperty(o2,
							properties[i]);
					if (p1 == null && p2 != null)
						result = -1;
					else if (p2 == null && p1 != null)
						result = 1;
					else if (p1 != null)
						result = p1.compareTo(p2);
					if (result != 0)
						return asc[i] ? result : -result;
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				}

		}
		return 0;
	}


	public static void main(String[] args) {
		/*AddressBook  addressBook =new AddressBook();
		addressBook.setAddress("湖南省101");
		addressBook.setBindPhone("15901998440");
		addressBook.setCreateTime(DateUtil.parse("2018-01-05 23:10:10",DateUtil.TIMESTAMP_FORMAT));
		addressBook.setUpdateTime(DateUtil.parse("2018-01-02 23:10:10",DateUtil.TIMESTAMP_FORMAT));

		AddressBook  addressBook1 =new AddressBook();
		addressBook1.setAddress("湖南省101");
		addressBook1.setBindPhone("15901998440");
		addressBook1.setCreateTime(DateUtil.parse("2018-01-10 23:10:10",DateUtil.TIMESTAMP_FORMAT));
		addressBook1.setUpdateTime(DateUtil.parse("2018-01-03 23:10:10",DateUtil.TIMESTAMP_FORMAT));

		List<AddressBook> AddressBookList=new ArrayList<>();
		AddressBookList.add(addressBook);
		AddressBookList.add(addressBook1);

		Collections.sort(AddressBookList,new ListComparator(new String[] {"updateTime", "createTime" }, new
				                                        boolean[] { false, false }));

		System.out.println(JSON.toJSONString(AddressBookList));*/
	}
}
