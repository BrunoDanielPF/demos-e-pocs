package com.mycompany.app;

public final class User {
    private String name;

    private User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static AnimalBuilder builder() {
        return new AnimalBuilder();
    }


    public static class AnimalBuilder {
        private String name;

        AnimalBuilder() {
        }

        public AnimalBuilder name(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            return new User(name);
        }

        public String toString() {
            return "User.AnimalBuilder(name=" + this.name + ")";
        }
    }
}
