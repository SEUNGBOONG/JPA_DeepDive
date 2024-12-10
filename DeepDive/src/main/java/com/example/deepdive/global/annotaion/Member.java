package com.example.deepdive.global.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Member {
}

// Target 어노테이션 :
//@Target(ElementType.PARAMETER) :  파라미터에만 사용 가능,
// @Retention(RetentionPolicy.RUNTIME) : 런타임 까지 유지시켜줌
// HandlerMethodARguentResolver와 같은 메커니즘에서 활용

