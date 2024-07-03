package com.codesignal;

import java.util.*;

public class InMemoryDatabaseUberScaledSolutionTest1 {

    public static void main(String[] args) {
        InMemoryDatabaseUberScaledSolutionTest1 db = new InMemoryDatabaseUberScaledSolutionTest1();
        db.setOrInc("A1", "F1", 1);
        db.setOrIncWithLock("A1", "F1", 3, "C1");
        db.setOrIncWithLock("A1", "F1", 3, "C2");
        db.deleteWithLock("A1", "F1", "C1");
        System.out.println(db.get("A1","F1"));
        db.lock("A1", "C1");
        db.lock("A1", "C2");
        db.lock("A1", "C1");
        db.setOrInc("A1", "F1", 4);
        db.setOrIncWithLock("A1", "F1", 1, "C1");
        db.unLock("A1", "C2");
        db.unLock("A1", "C1");
        db.unLock("A1", "C1");
    }

    public void printDb() {
        System.out.println(this.db);
    }

    public void printLocks() {
        System.out.println(this.locks);
        System.out.println(this.nextLockRequestQueue);
    }

    static class Record {
        String key;
        Map<String, Integer> fields;
        int updateFrequency;

        Record(String key) {
            this.key = key;
            this.fields = new HashMap<>();
            this.updateFrequency = 0;
        }

        public void onUpdate() {
            this.updateFrequency += 1;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "fields=" + fields +
                    ", updateFrequency=" + updateFrequency +
                    '}';
        }
    }

    Map<String, Record> db;
    Map<String, String> locks;
    Queue<String> nextLockRequestQueue;

    InMemoryDatabaseUberScaledSolutionTest1() {
        this.db = new HashMap<>();
        this.locks = new HashMap<>();
        this.nextLockRequestQueue = new LinkedList<>();
    }

    public boolean isCallerAuthorized(String key, String callerId) {
        String lockedCaller = locks.getOrDefault(key, null);
        return lockedCaller == null || lockedCaller.equals(callerId);
    }

    public String lock(String key, String callerId) {
        String lockedCaller = locks.getOrDefault(key, null);
        String message;

        if (lockedCaller == null) {
            locks.put(key, callerId);
            message = "Key locked successfully!!";
        } else {
            if (lockedCaller.equals(callerId)) {
                message = "CalledId already has a lock.";
            } else {
                if (nextLockRequestQueue.contains(callerId)) {
                    message = "Request for CallerId already exists.";
                } else {
                    nextLockRequestQueue.add(callerId);
                    message = "New Lock request added successfully.";
                }
            }
        }
        printLocks();
        return message;
    }

    public String unLock(String key, String callerId) {
        String lockedCaller = locks.getOrDefault(key, null);
        String message;
        if (lockedCaller == null) {
            message = "Key is already open.";
        }

        if (lockedCaller.equals(callerId)) {
            String nextLocker = null;
            if (!nextLockRequestQueue.isEmpty()) {
                nextLocker = nextLockRequestQueue.remove();
            }
            locks.put(key, nextLocker);
            message = "Key unlocked by caller";
        } else {
            message = "Caller UnAuthorized to unlock";
        }
        printLocks();
        return message;
    }

    public Optional<Integer> setOrIncWithLock(String key, String field, Integer value, String callerId) {
        Record record = db.getOrDefault(key, null);
        if (record == null || this.isCallerAuthorized(key, callerId)) {
            locks.put(key, callerId);
            return setOrInc(key, field, value);
        }
        return Optional.empty();
    }

    public boolean deleteWithLock(String key, String field, String callerId) {
        Record record = db.getOrDefault(key, null);
        if (record == null || this.isCallerAuthorized(key, callerId)) {
            return delete(key, field);
        }
        return false;
    }

    public Optional<Integer> setOrInc(String key, String field, Integer value) {
        Record record = db.getOrDefault(key, null);

        if (record == null) {
            record = new Record(key);
            db.put(key, record);
        }

        Map<String, Integer> fields = db.get(key).fields;

        fields.put(field, fields.getOrDefault(field, 0) + value);
        record.onUpdate();
        printDb();
        return Optional.of(fields.get(field));
    }

    public Optional<Integer> get(String key, String field) {
        Record record = db.getOrDefault(key, null);

        if (record == null) {
            return Optional.empty();
        } else {
            Map<String, Integer> fields = db.get(key).fields;
            if (!fields.containsKey(field)) {
                return Optional.empty();
            } else {
                record.onUpdate();
                return Optional.of(fields.get(field));
            }
        }
    }

    public boolean delete(String key, String field) {
        Record record = db.getOrDefault(key, null);
        boolean result;
        if (record == null) {
            result = false;
        } else {
            Map<String, Integer> fields = db.get(key).fields;
            if (!fields.containsKey(field)) {
                result = false;
            } else {
                fields.remove(field);
                record.onUpdate();
                if (fields.isEmpty()) {
                    db.remove(key);
                }
                result = true;
            }
        }
        printLocks();
        return result;
    }

}
