package util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年4月21日 上午10:38:14
* 类说明
*/
@Documented  
@Inherited  
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME) 
public @interface AuthPassport {
	
	boolean chek() default true;
}

