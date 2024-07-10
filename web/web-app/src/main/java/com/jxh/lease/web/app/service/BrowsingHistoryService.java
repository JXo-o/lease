package com.jxh.lease.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.BrowsingHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.app.vo.history.HistoryItemVo;

public interface BrowsingHistoryService extends IService<BrowsingHistory> {
    IPage<HistoryItemVo> pageHistoryItemByUserId(IPage<HistoryItemVo> page, Long userId);

    void saveHistory(Long userId, Long roomId);
}
