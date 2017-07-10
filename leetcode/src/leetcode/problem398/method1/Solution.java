package leetcode.problem398.method1;

import java.util.*;

public class Solution {
    static class Div{
        String num1;
        String num2;
        double value;
        
        Div(String num1, String num2, double value){
            this.num1 = num1;
            this.num2 = num2;
            this.value = value;
        }
        
    }
    
    
    
    public double[] calcEquation(String[][] eq, double[] values, String[][] que) {
        
        Map<String, Map<String, Div>> known = new HashMap<String, Map<String, Div>>();
        
        prepare(known, eq, values);
        
        double[] result = new double[que.length];
        for(int i = 0; i < que.length; ++i){
            String[] q = que[i];
            Set<Div> visited = new HashSet<Div>();
            result[i] = calculate(q[0], q[1], known, visited);
        }
        
        return result;
    }
    
    double calculate(String num1, String num2, Map<String, Map<String, Div>> known, Set<Div> visited){
        Div exist = getKnown(known, num1, num2);
        
        if(exist != null){
            return exist.value;
        }
        
        if(!known.keySet().contains(num1) || !known.keySet().contains(num2)){
            return -1.0;
        }
        
        if(num1.equals(num2)){
            return 1.0;    
        }
                
        for(Div d : known.get(num1).values()){
            // if(d.num2.equals(num2)){
            //     return d.value;
            // }
            
        	if(visited.contains(d)){
        		continue;
        	}
        	
        	visited.add(d);
        	Div rev = getKnown(known, d.num2, d.num1);
        	if(rev!=null){
        		visited.add(rev);
        	}
        	
            double ans1 = calculate(d.num2, num2, known, visited);
            
            if(ans1!=-1.0){
                double result = d.value*ans1;
                
                putKnown(known, num1, num2, new Div(num1, num2, result));
                if(result != 0.0){
                    putKnown(known, num2, num1, new Div(num2, num1, 1.0/result));
                }
                
                return result;
            }
            
            visited.remove(d);
            if(rev != null){
            	visited.remove(rev);
            }
        }
        
        return -1.0;
    }
    
    
    void putKnown(Map<String, Map<String, Div>> known, String num1, String num2, Div d){
        Map<String, Div> m1 = known.get(num1);
        if(m1 == null){
            m1 = new HashMap<String, Div>();
            known.put(num1, m1);
        }
        m1.put(num2, d);
    }
    
    Div getKnown(Map<String, Map<String, Div>> known, String num1, String num2){
        Map<String, Div> m1 = known.get(num1);
        if(m1 == null){
            return null;
        }
        return m1.get(num2);
    }
    
    void prepare(Map<String, Map<String, Div>> known, String[][] eq, double[] values){
        
        for(int i = 0; i < values.length; ++i){
            String[] e = eq[i];
            
            Div d1 = new Div(e[0], e[1], values[i]);
            putKnown(known, e[0], e[1], d1);
            
            if(values[i] != 0.0){
                Div d2 = new Div(e[1], e[0], 1.0/values[i]);    
                putKnown(known, e[1], e[0], d2);
            }
            
        }
    }


	public static void main(String[] args) {
//		String[][] a = new String[][]{{"a","b"}};
//		double[] b = new double[]{0.5};
//		String[][] c = {{"a","b"},{"b","a"},{"a","c"},{"x","y"}};
		
		String[][] a = new String[][]{{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}};
		double[] b = new double[]{3.0,4.0,5.0,6.0};
		String[][] c = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
		
		Solution s = new Solution();
		for(double r : s.calcEquation(a, b, c)){
			System.out.println(r);
		}
		
		
	}

}
