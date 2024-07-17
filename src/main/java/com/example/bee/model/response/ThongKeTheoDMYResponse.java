package com.example.bee.model.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "ThongKeTheoDMYMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ThongKeTheoDMYResponse.class,
                        columns = {
                                @ColumnResult(name = "tongDoanhThu", type = Long.class),
                                @ColumnResult(name = "tongSoDonThanhCong", type = Long.class),
                                @ColumnResult(name = "tongSoDonHuy", type = Long.class),
                                @ColumnResult(name = "tongSoSanPhamDaBan", type = Long.class),
                                @ColumnResult(name = "tongSoDonTaiQuay", type = Long.class),
                                @ColumnResult(name = "tongSoDonOnline", type = Long.class),
                        }
                )
        }
)
public class ThongKeTheoDMYResponse {

    private Long tongDoanhThu;

    private Long tongSoDonThanhCong;

    private Long tongSoDonHuy;

    private Long tongSoSanPhamDaBan;

    private Long tongSoDonTaiQuay;

    private Long tongSoDonOnline;

}
