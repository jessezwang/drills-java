public class ValidNumber {
	 boolean isN(String s){
			int indOfDot = s.indexOf('.');
	    	if (indOfDot == -1) return isInt(s);
	    	if (indOfDot==0 && indOfDot==s.length()-1) return false;
	    	if (indOfDot==0) return isF(s.substring(indOfDot+1,s.length()));
	    	if (indOfDot==s.length()-1) return isInt(s.substring(0,indOfDot));
	    	return isInt(s.substring(0,indOfDot)) && isF(s.substring(indOfDot+1,s.length()));
	    }
		
		boolean isF(String s){
			if (s.length()==0) return true;
			for (int i=0; i<s.length(); i++)
				if (s.charAt(i)<'0'||s.charAt(i)>'9') return false;
			return true;
		}

		boolean isInt(String s){
			int i=0;
			if (s.charAt(i)=='-'||s.charAt(i)=='+') i++;
			if (i==s.length()) return false;
			if (i+1==s.length() && s.charAt(i)=='0') return true;
			if (s.charAt(i)<'1' || s.charAt(i)>'9') return false;
	        else i++;
			for (; i<s.length(); i++)
				if (s.charAt(i)<'0' || s.charAt(i)>'9') return false;
			return true;							
		}
		
	    public boolean isNumber(String s) {
	    	int end=s.length()-1;
	    	int begin=0;
			for (;end>=0; end--)
				if (s.charAt(end)!=' ') break;
			if (end==-1) return false;
			for (; begin<s.length(); begin++)
				if (s.charAt(begin)!=' ') break;
			s=s.substring(begin,end+1);
	    	int indOfE = s.indexOf('e');
	    	if (indOfE == -1) indOfE = s.indexOf('E');
	    	if (indOfE == -1) return isN(s);
	    	if (indOfE==0 || indOfE==s.length()-1) return false;
	    	return isN(s.substring(0,indOfE)) && isInt(s.substring(indOfE+1,s.length()));
	    	
	    }
	    
	    public static void main(String[] args){
	    	ValidNumber vn = new ValidNumber();
	    	System.out.println(vn.isNumber("0.") );
	    }
}
