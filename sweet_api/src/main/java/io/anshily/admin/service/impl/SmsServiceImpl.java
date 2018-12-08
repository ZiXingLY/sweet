package io.anshily.admin.service.impl;

import io.anshily.admin.dao.SmsMapper;
import io.anshily.model.Sms;
import io.anshily.admin.service.SmsService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by hang on 2018/08/14.
 */
@Service
@Transactional
public class SmsServiceImpl extends AbstractService<Sms> implements SmsService {
    @Resource
    private SmsMapper qySmsMapper;

}
