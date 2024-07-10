package com.jxh.lease.web.app.vo.appointment;

import com.jxh.lease.model.entity.ViewAppointment;
import com.jxh.lease.web.app.vo.apartment.ApartmentItemVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "APP端预约看房详情")
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDetailVo extends ViewAppointment {

    @Schema(description = "公寓基本信息")
    private ApartmentItemVo apartmentItemVo;
}
