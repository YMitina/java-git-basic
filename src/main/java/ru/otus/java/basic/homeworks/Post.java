package ru.otus.java.basic.homeworks;


public enum Post {
    MANAGER(true), DIRECTOR(true), DRIVER(false), ENGINEER(false),
    SENIOR_MANAGER(true), DEVELOPER(false), QA(false),
    JANITOR(false), PLUMBER(false), BRANCH_DIRECTOR(true), JUNIOR_DEVELOPER(false);
    private boolean isManager;

    private Post(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isManager() {
        return this.isManager;
    }

    public static Post random() {
        return Post.values()[(int) (Math.random() * Post.values().length)];
    }


}

