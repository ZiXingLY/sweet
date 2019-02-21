package io.anshily.admin.service.impl;

import io.anshily.admin.service.SubjectService;

import java.util.HashMap;
import java.util.Map;

public class SubjectServiceImpl implements SubjectService {

    Map<String,Object> subjectMap = new HashMap<String, Object>();
    @Override
    public void save(String key, Object value) {
        subjectMap.put(key, value);
    }

    @Override
    public Object find(String key) {
        return subjectMap.get(key);
    }
}
