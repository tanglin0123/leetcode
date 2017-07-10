package leetcode.problem468.method1;

public class Solution {
    public String validIPAddress(String ip) {
		if(ip.endsWith(".") || ip.endsWith(":")){
			return "Neither";
		}
    	
    	String[] ss = ip.split("\\.");
        
        if(ss.length == 4){ // check ipv4
            for(String s : ss){
                if(s.isEmpty()){
                    return "Neither";
                }
                
                int i = 0;
                try{
                	i = Integer.parseInt(s);
                } catch(Exception e){
                    return "Neither";
                }
                
                if(s.startsWith("0") && s.length() > 1){
                    return "Neither";
                }
                
                if(i < 0 || i > 255){
                    return "Neither";
                }
            }
            
            return "IPv4";
        } else if(ss.length == 1){ // check ipv6
            ss = ip.split(":");
            if(ss.length != 8){
                return "Neither";
            }
            
            for(String s : ss){
                if(s.isEmpty()){
                    return "Neither";
                }
                
                if(s.length() > 4){
                    return "Neither";
                }
                
                int i = 0;
                try{
                	i = Integer.parseInt(s, 16);
                } catch(Exception e){
                    return "Neither";
                }
                
            }
            
            return "IPv6";
            
        } else {
            return "Neither";
        }
    }

	public static void main(String[] args) {
//		String a = "172.16.254.1";
		String a = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
		
		
		
		Solution s = new Solution();
		System.out.println(s.validIPAddress(a));
	}

}
