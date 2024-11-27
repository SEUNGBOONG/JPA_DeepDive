    @ExceptionHandler(MemberException.class)

@ExceptionHandler는 Spring MVC에서 예외를 처리하기 위해 사용되는 어노테이션입니다. @ExceptionHandler(MemberException.class)는 MemberException 타입의 예외가 발생했을 때 해당 예외를 처리하기 위한 메서드를 지정합니다.

역할 및 동작
특정 예외 처리:

컨트롤러나 서비스 계층에서 MemberException이 발생하면, Spring은 해당 예외를 처리하기 위해 @ExceptionHandler(MemberException.class)가 선언된 메서드를 호출합니다.
컨트롤러 내 또는 전역 적용:

컨트롤러 클래스 내부에 선언된 경우: 해당 컨트롤러에서 발생한 MemberException만 처리합니다.
전역 예외 처리 클래스 (@ControllerAdvice)에 선언된 경우: 애플리케이션 전역에서 발생한 MemberException을 처리합니다.
커스터마이즈된 응답 제공:

예외가 발생했을 때, 사용자에게 적절한 에러 메시지나 상태 코드를 포함한 응답을 반환할 수 있습니다.
