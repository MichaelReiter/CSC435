package environment;

public class ListNode<K, V> {
    K key;
    V value;
    int scope;
    ListNode<K,V> next;

    public ListNode(K k, V v, int s) {
        this.key = k;
        this.value = v;
        this.scope = s;
        this.next = null;
    }
}
