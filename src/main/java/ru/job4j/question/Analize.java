package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int deleted = 0;
        int changed = 0;
        Map<Integer, User> oldMap = previous.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        for (User user : current) {
            if (!oldMap.containsKey(user.getId())) {
                added++;
            } else if (!oldMap.get(user.getId()).getName().equals(user.getName())) {
                changed++;
            }
        }
        deleted = previous.size() + added - current.size();
        return new Info(added, changed, deleted);
    }
}