public class ValidNumber {
	boolean isN(String s){
		int indOfDot = s.indexOf('.');
    	if (indOfDot==-1) 
            if (s.length()==1 && (s.charAt(0)=='+' || s.charAt(0)=='-')) return false;
            else return isInt(s, true, true);
    	if (indOfDot==0 && s.length()==1) return false;
    	if (indOfDot==0) return isInt(s.substring(1,s.length()), false, false);
    	if (indOfDot==s.length()-1) return isInt(s.substring(0,indOfDot), true, false);
    	return isInt(s.substring(0,indOfDot), true, true) && isInt(s.substring(indOfDot+1,s.length()), false, true);
    }
    
	boolean isInt(String s, boolean signed, boolean empty){
		int i=0;
		if ( signed && (s.charAt(0)=='-' || s.charAt(0)=='+')) i++;
		if (i==s.length())
            if (empty) return true;
            else return false;
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
    	return isN(s.substring(0,indOfE)) && isInt(s.substring(indOfE+1,s.length()), true, false);
    }
	    
	    public static void main(String[] args){
	    	ValidNumber vn = new ValidNumber();
	    	System.out.println(vn.isNumber(".+8") );
	    }
}
