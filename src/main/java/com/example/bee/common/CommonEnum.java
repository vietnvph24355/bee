package com.example.bee.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommonEnum {

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum GioiTinh {
        MALE("MALE", "Nam", "blue"),
        FEMALE("FEMALE", "Nữ", "magenta"),
        OTHER("OTHER", "Khác", "default");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Ten {
        MANAGER("MANAGER", "Quản Lý", ""),
        EMPLOYEE("EMPLOYEE", "Nhân Viên", ""),
        CUSTOMER("CUSTOMER", "Khách Hàng", "");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiSanPham {
        ACTIVE("ACTIVE", "Đang bán", "success"),
        INACTIVE("INACTIVE", "Ngừng kinh doanh", "default");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum LoaiHoaDon {
        ONLINE("ONLINE", "Website", "green"),
        COUNTER("COUNTER", "Tại quầy", "blue");


        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiHoaDon {

        PENDING("PENDING", "Chờ xác nhận", "warning"),
        CONFIRMED("CONFIRMED", "Đã xác nhận", "success"),
        PICKUP("PICKUP", "Đang lấy hàng", "#ED5AB3"),
        SHIPPING("SHIPPING", "Đang vận chuyển", "geekblue"),
        CANCELLED("CANCELLED", "Đã hủy", "volcano"),
        APPROVED("APPROVED", "Đã hoàn thành", "magenta");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiHoaDonChiTiet {
        APPROVED("APPROVED", "Đã Xác Nhận", "primary"),
        UNAPPROVED("UNAPPROVED", "Không Xác Nhận", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum LoaiDiaChi {
        HOME("HOME", "Nhà riêng", ""),
        COMPANY("COMPANY", "Công ty", ""),
        OTHER("OTHER", "Khác", "");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiThuocTinh {
        ACTIVE("ACTIVE", "Hoạt động", "success"),
        INACTIVE("INACTIVE", "Không hoạt động", "red");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiDiaChi {
        ACTIVE("ACTIVE", "Hoạt động", "geekblue"),
        DEFAULT("DEFAULT", "Mặc định", "red");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiChiTietSanPham {
        ACTIVE("ACTIVE", "Hoạt động", "success"),
        INACTIVE("INACTIVE", "Ngừng kinh doanh", "red"),
        OUT_OF_STOCK("OUT_OF_STOCK", "Hết hàng", "green"),
        DELETED("DELETED", "Xóa", "");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiHinhAnh {
        DEFAULT("DEFAULT", "Mặc định", "success"),
        AVATAR("AVATAR", "Ảnh đại diện", "red"),
        DELETED("DELETED", "Đã xóa", "green");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum HinhThucGiam {
        ACTIVE("ACTIVE", "Hoạt động", "success"),
        INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiVoucher {
        UPCOMING("UPCOMING", "Sắp diễn ra", "gold"),
        ONGOING("ONGOING", "Đang diễn ra", "green"),
        ENDING_SOON("ENDING_SOON", "Sắp hết hạn", "volcano"),
        EXPIRED("EXPIRED", "Hết hạn", "blue"),
        OUT_OF_STOCK("OUT_OF_STOCK", "Đã hết", "purple"),
        CANCELLED("CANCELLED", "Hủy bỏ", "red");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum LoaiVoucher {
        INVOICE("INVOICE", "Hóa đơn", "blue"),
        CUSTOMER("CUSTOMER", "Khách hàng", "purple");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiVoucherChiTiet {
        ACTIVE("ACTIVE", "Hoạt động", "success"),
        INACTIVE("INACTIVE", "Không hoạt động", "red"),
        DELETED("DELETED", "Đã xóa", "red");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiGiaoDich {
        SUCCESS("SUCCESS", "Đã thanh toán", "success"),
        FAILED("FAILED", "Hủy bỏ", "error"),
        PENDING("PENDING", "Chờ thanh toán", "warning"),
        REFUND("REFUND", "Hoàn tiền", "processing");

        private final String ten;
        private final String moTa;
        private final String mauSac;
    }

}
