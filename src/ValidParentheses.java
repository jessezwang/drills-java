import java.util.*;
public class ValidParentheses {
    public boolean isValid(String s) 
    {
        Stack<Character> stack = new Stack<Character>();
        if(s.isEmpty()) return true;
        stack.push(s.charAt(0));
        for (int i=1; i<s.length(); i++)
            switch(s.charAt(i))
            {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek()!='(')
                        return false;
                    else
                        stack.pop();
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek()!='[')
                        return false;
                    else
                        stack.pop();
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '}':
                     if (stack.isEmpty() || stack.peek()!='{')
                        return false;
                    else
                        stack.pop();
                    break;
            }
        if(stack.isEmpty()) return true;
        else return false;       
    }
}