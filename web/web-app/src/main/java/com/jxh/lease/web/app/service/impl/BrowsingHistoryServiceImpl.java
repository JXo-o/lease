package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.BrowsingHistory;
import com.jxh.lease.web.app.mapper.BrowsingHistoryMapper;
import com.jxh.lease.web.app.service.BrowsingHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistory>
        implements BrowsingHistoryService {
}
