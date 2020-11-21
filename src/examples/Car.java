package examples;

public class Car {

	private String modelName;
	private int modelYear;
	private String color;

	// Constructor : this 참조 변수를 사용하여 instance 변수에 접근
	Car(String modelName, int modelYear) {
		this.modelName = modelName;
		this.modelYear = modelYear;
		this.color = "blue";
	}

	// Constructor : parameter 없이 내부에서 this() 메소드를 이용
	Car() {
		this("AVANTE", 2018);
	}

	public String getModel() {
		return this.modelYear + "년식 " + this.modelName;
	}

	public void methodOverloading(int A) {
		System.out.println(A);
	}

	public void methodOverloading2(int A, int B) {
		System.out.println(A + B);
	}

	public int sum(int x) {
		int result = 0;
		for (int i = 1; i <= x; i++) { result += i; }
		return result;
	}

	public int recursiveSum(int x) {
		if (x == 1) { return 1; }

		return x + recursiveSum(x-1);
	}

	public static void main(String[] args) {
		Car car = new Car("GENESIS", 2020);
		Car car2 = new Car();

		System.out.println(car.getModel() + " and the color is " + car.color);
		System.out.println(car2.getModel() + " and the color is " + car.color);

		car.methodOverloading(1);
		car.methodOverloading2(1, 2);

		System.out.println("sum = " + car.sum(10));
		System.out.println("recursive sum = " + car.recursiveSum(10));
	}

}
