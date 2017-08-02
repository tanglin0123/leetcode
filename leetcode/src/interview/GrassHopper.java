package interview;

// from yangrui
public class GrassHopper {

	// a grasshopper can jump forward or backward alone a line, 
	// and the next step will be 1 unit farther than current step.
	// it stand on the point 0 initially. 
	// given a position point, 
	// return the minimum steps the grasshopper can jump to the target position point
	public int minSteps(int t) {
		t = Math.abs(t);
		if(t == 0 || t == 1) {
			return t;
		}
		for(int n = (int)Math.sqrt(2*t) - 1; true; ++n ) {
			if(n*(n+1) >= 2 * t) {
				if(t % 2 == 0 && (n % 4 == 3 || n % 4 == 0)) {
					return n;
				} else if(t % 2 == 1 && (n % 4 == 1 || n % 4 == 2)) {
					return n;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		GrassHopper g = new GrassHopper();
		
		for(int i = 0; i <= 55; ++i) {
			System.out.println(i + " -> " + g.minSteps(i));
		}
	}

}
