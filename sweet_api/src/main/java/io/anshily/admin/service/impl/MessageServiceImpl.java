package io.anshily.admin.service.impl;

import io.anshily.admin.dao.MessageMapper;
import io.anshily.model.Message;
import io.anshily.admin.service.MessageService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2018/08/22.
 */
@Service
@Transactional
public class MessageServiceImpl extends AbstractService<Message> implements MessageService {
    @Resource
    private MessageMapper qyMessageMapper;

}
