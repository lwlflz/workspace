package com.build.cloud.common.annotation;

import java.lang.annotation.*;

/**
 * 
 * @ClassName: DataFilter   
 * @Description: 数据过滤
 * @author: liutao
 * @date: 2018年3月16日 下午2:26:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**  表的别名 */
    String tableAlias() default "";

    /**  true：没有本部门数据权限，也能查询本人数据 */
    boolean user() default true;
    
    /**  true：拥有子公司数据权限 */
    boolean subCompany() default false;

    /**  true：拥有子部门数据权限 */
    boolean subDept() default false;
    
    /**  公司ID */
    String companyId() default "company_id";

    /**  部门ID */
    String deptId() default "dept_id";

    /**  用户ID */
    String userId() default "user_id";
}
