package org.gameprototype.datasource;

import org.apache.commons.lang.StringUtils;

public class DbContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		String dbType = (String) contextHolder.get();
		if(StringUtils.isEmpty(dbType)){
			return DataSourceType.getDefaultType().getValue();
		}
		return dbType;
	}

	public static void clearDbType() {
		contextHolder.remove();
	}
	
	enum DataSourceType{
		ds_r("r"), // 读库
		ds_w("w"); // 写库
		
		private String value;

		private DataSourceType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
		
		public static DataSourceType getDefaultType(){
			return DataSourceType.ds_r;
		}
		
		public static DataSourceType getCouponStatus(String value){
			for(DataSourceType status : values()){
				if(status.value.equals(value)){
					return status;
				}
			}
			return null;
		}
		
	}
}