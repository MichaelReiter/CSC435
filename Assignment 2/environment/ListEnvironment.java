package environment;

public class ListEnvironment<K, V> implements Environment<K, V> {
    ListNode<K, V> head;
    int scopeLevel;

    public ListEnvironment() {
        this.head = null;
        this.scopeLevel = 0;
    }

    public void beginScope() {
        // Add null key, null value node to list
    }

    public void endScope() {

    }

    public boolean inCurrentScope(K key) {
        // verify that the variable exists and its scope matches the current scopeLevel
        return false;
    }

    public void add(K key, V value) {
        ListNode<K, V> node = new ListNode<K, V>(key, value, this.scopeLevel);
    }

    public V lookup(K key) {
        // return value for key
    }

    public String toString() {
        ListNode<K, V> n = head;
        String s = "lvl: " + scopeLevel + " {";
        while (n != null) {
            s += "(";
            if (n.key == null) {
                s += "<sm>";
            }
            else {
                s += n.key + "," + n.value;
            }
            s += ")";
            if (n.next != null) {
                s += ",";
            }
            n = n.next;
        }
        s += "}";
        return s;
    }

    public static void main(String[] args) {
        Environment<String, Integer> e1 = new ListEnvironment<String, Integer>();
        e1.beginScope();
        System.out.println(e1);
        e1.add("ABC", 1);
        e1.add("DEF", 2);
        e1.add("XYZ", 3);
        System.out.println(e1);
        if (e1.inCurrentScope("ABC"))
            System.out.println("Passed inCurrentScope 1");
        else
            System.out.println("Failed inCurrentScope 1");

        if (e1.inCurrentScope("DEF"))
            System.out.println("Passed inCurrentScope 2");
        else
            System.out.println("Failed inCurrentScope 2");

        if (e1.inCurrentScope("XYZ"))
            System.out.println("Passed inCurrentScope 3");
        else
            System.out.println("Failed inCurrentScope 3");

        if (!e1.inCurrentScope("DDD"))
            System.out.println("Passed inCurrentScope 4");
        else
            System.out.println("Failed inCurrentScope 4");
        
        e1.beginScope();
        e1.add("ABC", 123);
        e1.add("EER", 777);
        if (e1.inCurrentScope("ABC"))
            System.out.println("Passed inCurrentScope 5");
        else
            System.out.println("Failed inCurrentScope 5");

        if (!e1.inCurrentScope("DEF"))
            System.out.println("Passed inCurrentScope 6");
        else
            System.out.println("Failed inCurrentScope 6");

        if (!e1.inCurrentScope("XYZ"))
            System.out.println("Passed inCurrentScope 7");
        else
            System.out.println("Failed inCurrentScope 7");
        System.out.println(e1);

        if (e1.lookup("DEF").equals(2))
            System.out.println("Passed lookup 1");
        else
            System.out.println("Failed lookup 1");

        e1.beginScope();
        e1.add("BOBWAS", 999);
        System.out.println(e1);
        e1.endScope();
        System.out.println(e1);
        e1.endScope();
        System.out.println(e1);
        e1.endScope();
        System.out.println(e1);
    }
}
