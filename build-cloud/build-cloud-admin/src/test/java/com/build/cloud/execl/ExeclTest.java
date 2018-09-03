package com.build.cloud.execl;
import java.util.List;
import java.util.Map;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
public class ExeclTest {
	public static void main(String[] args) {
		String path = "D:\\upload\\1.xlsx";
		test1(path);
		test2(path);
	}
	private static void test1(String path) {
		ExcelUtil.readBySax(path, 0, new RowHandler() {
			@Override
			public void handle(int sheetIndex, int rowIndex, List<Object> rowlist) {
				Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
			}
		});
	}
	//流
	private static void test2(String path) {
		ExcelReader reader = ExcelUtil.getReader(path);
		List<Map<String,Object>> readAll = reader.readAll();
		System.out.println(readAll);
		for (Map<String, Object> map : readAll) {
			for (Map.Entry<String, Object> entity : map.entrySet()) {
				System.out.println(entity);
			}
		}
	}
}
