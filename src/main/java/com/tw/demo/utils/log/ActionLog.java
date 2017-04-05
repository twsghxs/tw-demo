package com.tw.demo.utils.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 注意：同一个类如下调用，调用b方法，c方法是不能记录日志
 * 
 * @ActionLog public void a(){}
 * @ActionLog public void b(){c();}
 * @ActionLog public void c(){}
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ActionLog {

}
