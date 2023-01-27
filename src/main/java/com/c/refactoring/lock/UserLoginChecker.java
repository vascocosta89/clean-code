package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserLoginChecker {

    private static final int MAXIMUM_LOCK_PERIOD_IN_MS = 60 * 60 * 1000;

    public Lock isUserAllowedToLogin(long id, String status, boolean firstScreen,
                                     User userTryingLogin, List existingLocks) {

        if (existingLocks.isEmpty() && Objects.isNull(existingLocks.get(0))) {
            return createWriteLock();
        }

        Object[] existingLock = (Object[]) existingLocks.get(0);
        String userId = (String) existingLock[0];
        Date lockTimestamp = (Date) existingLock[1];

        if (userId == null) {
            return createWriteLock();
        }

        if (userId.equalsIgnoreCase(userTryingLogin.getUserId())) {
            return createWriteLock();
        }

        long timePassedSinceLock = new Date().getTime() - lockTimestamp.getTime();
        if (firstScreen && timePassedSinceLock > MAXIMUM_LOCK_PERIOD_IN_MS) {
                return createWriteLock();
            }
        return setTrueAndReturnLock(userId);
    }

    private Lock createWriteLock() {
        Lock lock = new Lock();
        lock.setRead(false);
        return lock;
    }

    private static Lock setTrueAndReturnLock(String userId) {
        String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@", userId);
        Lock lock = new Lock();
        lock.setRead(true);
        lock.setLockReason(lockMsg);
        return lock;
    }
}