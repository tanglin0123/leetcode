package interview;

// alibaba
public class Singleton {
	private static Singleton instance = null;

	private Singleton() {
		System.out.println("creating");
	}

	public static Singleton getInstance(){
		if(instance != null) {
			return instance;
		} else {
			synchronized(Singleton.class) {
				if(instance != null) {
					return instance;
				} else {
					instance = new Singleton();
					return instance;
				}
			}
		}
	}

	public static void main(String[] args) {
		Singleton.getInstance();
		Singleton.getInstance();
	}

}
