package com.build.cloud.modules.punch;

import java.util.Comparator;

import com.build.cloud.modules.punch.bean.PunchBean;

public class ListSort implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		PunchBean bean1 = (PunchBean)o1;  
		PunchBean bean2 = (PunchBean)o2;  
		return bean2.getAttendDate().compareTo(bean1.getAttendDate());  
	}

}
