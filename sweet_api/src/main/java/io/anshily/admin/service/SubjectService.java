package io.anshily.admin.service;

public interface SubjectService {
    void save(String key, Object value);
    Object find(String key);
}
