package com.java.hash;

import java.util.*;

// Same as above only .equals now considers name too
public class HashSetMapExperiment2 {

    public static void main(String[] args) {
        HashSetMapExperiment2.User user1 = new HashSetMapExperiment2.User(1, "rohit");
        HashSetMapExperiment2.User user2 = new HashSetMapExperiment2.User(2, "nandi");

        HashSet<HashSetMapExperiment2.User> hs = new HashSet<>();
        hs.add(user1);
        hs.add(user2);
        printSet(hs);

        user2.id = 1;
        printSet(hs);

        System.out.println(hs.remove(user2));
        printSet(hs);

        HashSetMapExperiment2.User user3 = new HashSetMapExperiment2.User(1, "ganesh");
        hs.add(user3);
        printSet(hs);

        System.out.println(" -------------------------- MAP ------------------------ ");
        user1 = new HashSetMapExperiment2.User(1, "rohit");
        user2 = new HashSetMapExperiment2.User(2, "nandi");
        HashMap<HashSetMapExperiment2.User, Integer> hm = new HashMap<>();
        hm.put(user1, 1);
        hm.put(user2, 2);
        printMap(hm);

        user2.id = 1;
        printMap(hm);
        System.out.println(hm.get(user1));
        System.out.println(hm.get(user2));
        hm.put(user2, 3);
        System.out.println(hm.get(user2));
        System.out.println(hm.remove(user2));
        printMap(hm);
    }

    public static void printSet(Set<HashSetMapExperiment2.User> set) {
        System.out.println(set.stream()
                .map(c -> "(" + c.id + "," + c.name + ")").toList());
    }
    public static void printMap(Map<HashSetMapExperiment2.User, Integer> m) {
        System.out.println(m.entrySet().stream()
                .map(e -> "(" + e.getKey().id + "," + e.getKey().name + ")=" + e.getValue()).toList());
    }

    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            HashSetMapExperiment2.User user = (HashSetMapExperiment2.User) object;
            return id == user.id && name != null && name.equals(((User) object).name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
