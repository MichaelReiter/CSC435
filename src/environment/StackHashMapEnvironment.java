package environment;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class StackHashMapEnvironment<K, V> implements Environment<K, V> {
    private final Deque<HashMap<K, V>> stack;

    public StackHashMapEnvironment() {
        this.stack = new ArrayDeque<HashMap<K, V>>();
    }

    public void beginScope() {
        this.stack.push(new HashMap<K, V>());
    }

    public void endScope() {
        this.stack.pop();
    }

    public boolean inCurrentScope(K key) {
        AbstractMap<K, V> scope = this.stack.peek();
        return scope.containsKey(key);
    }

    public void add(K key, V value) {
        AbstractMap<K, V> scope = this.stack.peek();
        scope.put(key, value);
        System.out.println(scope);
    }

    public V lookup(K key) {
        AbstractMap<K, V> scope = this.stack.peek();        
        return scope.get(key);
    }
}
