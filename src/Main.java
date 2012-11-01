
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		class Base{
			public final void func(){
				System.out.println("B");
			}
		};
		class Derived extends Base{
			public void func1(){
				System.out.println("D");
			}
		};

	//Base b = new Base();
	Base d = new Derived();
	d.func();
	}

}
