package lab01;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private List<T> stack = new ArrayList<>();
    private final int maxSize = 5; // Para fins de testes

    public T pop(){
        return stack.removeLast();
    }

    public int size(){
        return stack.size();
    }

    public T peek(){
        return stack.getLast();
    }

    public void push(T t){
        if (size() < maxSize) stack.add(t);
        else throw new IllegalStateException();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public T popTopN(int n) {
        if (n > size() || n <= 0) {
            throw new NoSuchElementException();
        }
        
        for (int i = 1; i < n; i++) {
            stack.remove(stack.size() - 1);
        }

        return pop();
    }
}