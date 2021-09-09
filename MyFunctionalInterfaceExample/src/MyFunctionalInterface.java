// 두개 이상의 추상메소드가 선언된 인터페이스는 람다식으로 구현객체 생성 x
// 하나의 추상메서드가 선언된 인터페이스만이 람다식의 타켓 타입
@FunctionalInterface // 두개 이상의 추상 메소드가 선언되지 않도록 체킹하는 어노테이션
public interface MyFunctionalInterface {
	public void method();
}
