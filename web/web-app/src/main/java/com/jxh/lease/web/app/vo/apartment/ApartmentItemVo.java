package com.jxh.lease.web.app.vo.apartment;

import com.jxh.lease.model.entity.ApartmentInfo;
import com.jxh.lease.model.entity.LabelInfo;
import com.jxh.lease.web.app.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Schema(description = "App端公寓信息")
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentItemVo extends ApartmentInfo {

    private List<LabelInfo> labelInfoList;

    private List<GraphVo> graphVoList;

    private BigDecimal minRent;
}
