package io.github.cassioamartim.quiz.controller;

import io.github.cassioamartim.quiz.account.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountController {

    private final Map<UUID, Account> ACCOUNT_MAP;

    public AccountController() {
        ACCOUNT_MAP = new HashMap<>();
    }

    public void load(Account account) {
        ACCOUNT_MAP.put(account.getUniqueId(), account);
    }

    public void remove(UUID uniqueId) {
        ACCOUNT_MAP.remove(uniqueId);
    }

    public void update(Account account) {
        ACCOUNT_MAP.putIfAbsent(account.getUniqueId(), account);
    }

    public Account get(UUID uniqueId) {
        return ACCOUNT_MAP.get(uniqueId);
    }

    public Account get(String name) {
        return ACCOUNT_MAP.values().stream().filter(account -> account.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
