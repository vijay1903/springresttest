package vj.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//File created by vijayvishwakarma on 4/4/20
public class Tester {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("User 1", 22));
        userList.add(new User("User 2", 23));
        userList.add(new User("User 3", 24));
        userList.add(new User("User 4", 25));

        userList.stream()
                .filter(u -> u.getAge()>23)
                .map(user -> user.getName() + ": " +user.getAge())
                .forEach(System.out::println);
    }



    static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
