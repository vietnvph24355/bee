package com.example.bee.controller.ac;

import com.example.bee.common.CommonEnum;
import com.example.bee.config.VNPayConfig;
import com.example.bee.model.response.GiaoDichResponse;
import com.example.bee.model.response.HoaDonResponse;
import com.example.bee.service.DonHangService;
import com.example.bee.service.GiaoDichService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin("*")
@Controller
@RequestMapping("/admin/api/vn-pay")
public class PayVNController {
    @Autowired
    private GiaoDichService giaoDichService;

    @Autowired
    private DonHangService donHangService;

    @GetMapping("/create-payment")
    public ResponseEntity<?> createdPayment(@RequestParam("soTienThanhToan") long soTienThanhToan,
                                            @RequestParam("maGiaoDich") String vnp_TxnRef,
                                            @RequestParam(value = "vnp_BankCode", defaultValue = "") String vnp_BankCode


    ) throws UnsupportedEncodingException {
        String vnp_IpAddr = "127.0.0.1";
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";

//        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        long amount = soTienThanhToan * 100;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", vnp_BankCode);

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        return ResponseEntity.ok(paymentUrl);
    }

    @GetMapping("end-payment")
    public RedirectView endPayment(HttpServletRequest request) {
        // Process return from VNPAY
        Map<String, String> fields = new HashMap<>();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String maGiaoDich = request.getParameter("vnp_TxnRef");
        String ngayThanhToan = request.getParameter("vnp_PayDate");
        if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
            String url = giaoDichService.updateByMa(maGiaoDich, ngayThanhToan, CommonEnum.TrangThaiGiaoDich.SUCCESS);
            GiaoDichResponse giaoDichResponse = giaoDichService.findByMaGiaoDich(maGiaoDich);
            HoaDonResponse hoaDonResponse = donHangService.getOneDonHang(giaoDichResponse.getHoaDon().getMa());
            if(hoaDonResponse.getLoaiHoaDon()== CommonEnum.LoaiHoaDon.ONLINE){
                donHangService.sendEmailDonHang(hoaDonResponse.getId());
            }
            return new RedirectView(url + "?hoaDon=" + giaoDichResponse.getHoaDon().getId() + "&&thanhToan=success");
        } else {
            String url = giaoDichService.updateByMa(maGiaoDich, ngayThanhToan, CommonEnum.TrangThaiGiaoDich.PENDING);
            return new RedirectView(url + "?thanhToan=failed");
        }
    }
}
