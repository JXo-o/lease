package com.jxh.lease.web.admin.schedule;

import com.jxh.lease.model.entity.LeaseAgreement;
import com.jxh.lease.model.enums.LeaseStatus;
import com.jxh.lease.web.admin.service.LeaseAgreementService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: ScheduleTasks
 * Package: com.jxh.lease.web.admin.schedule
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/9 9:44
 */
@Component
public class ScheduleTasks {

    private final LeaseAgreementService leaseAgreementService;

    public ScheduleTasks(LeaseAgreementService leaseAgreementService) {
        this.leaseAgreementService = leaseAgreementService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkLeaseStatus() {
        leaseAgreementService.lambdaUpdate()
                .le(LeaseAgreement::getLeaseEndDate, new Date())
                .in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING, LeaseStatus.RENEWING)
                .set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED)
                .update(new LeaseAgreement());
    }

}
