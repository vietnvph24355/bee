package com.example.bee.repository;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {

    // Nhan Vien
    @Query("SELECT tk FROM TaiKhoan tk " +
            "WHERE (tk.hoVaTen LIKE %:searchText% OR tk.soDienThoai LIKE %:searchText% OR tk.email LIKE %:searchText% OR tk.canCuocCongDan LIKE %:searchText%) " +
            "AND (:trangThai IS NULL OR tk.trangThai = :trangThai) " +
            "AND (:gioiTinh IS NULL OR tk.gioiTinh = :gioiTinh)"+
            "AND tk.vaiTro.id = 2")
    Page<TaiKhoan> findAllByVaiTro(
            Pageable pageable,
            @Param("searchText") String searchText,
            @Param("trangThai") CommonEnum.TrangThaiThuocTinh trangThai,
            @Param("gioiTinh") CommonEnum.GioiTinh gioiTinh
    );

    //Khach Hang
    @Query("SELECT tk FROM TaiKhoan tk " +
            "WHERE (tk.hoVaTen LIKE %:searchText% OR tk.soDienThoai LIKE %:searchText% OR tk.email LIKE %:searchText% OR tk.canCuocCongDan LIKE %:searchText%) " +
            "AND (:trangThai IS NULL OR tk.trangThai = :trangThai) " +
            "AND (:gioiTinh IS NULL OR tk.gioiTinh = :gioiTinh)"+
            "AND tk.vaiTro.id = 3")
    Page<TaiKhoan> findAllByVaiTro2(
            Pageable pageable,
            @Param("searchText") String searchText,
            @Param("trangThai") CommonEnum.TrangThaiThuocTinh trangThai,
            @Param("gioiTinh") CommonEnum.GioiTinh gioiTinh
    );
    // ALL khach hang
    @Query("SELECT tk FROM TaiKhoan tk " +
            "WHERE tk.vaiTro.id = 3")
    List<TaiKhoan> findAllKhachHang();

    // Tim theo sdt
    TaiKhoan findBySoDienThoai(String sdt);

    // Tim them cccd
    TaiKhoan findByCanCuocCongDan(String canCuoc);

    //Tim cccd
    boolean existsByCanCuocCongDan(String canCuoc);

    // tim sdt
    boolean existsBySoDienThoai(String sdt);

    // tim email
    boolean existsByEmail(String email);

    //
    //TaiKhoan findByEmail(String email);

    TaiKhoan findTaiKhoanByEmail(String email);

    //
    TaiKhoan findTaiKhoanByMatKhau(String matKhau);

    //
    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.email =:email")
    Optional<TaiKhoan> findByEmail(String email);

    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.soDienThoai = :sdt")
    Optional<TaiKhoan> findBySoDienThoai1(String sdt);

    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.email = :email")
    TaiKhoan findByEmail1(String email);

    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.matKhau = :matKhau")
    List<TaiKhoan> findByMatKhau(String matKhau);

    @Query("SELECT tk FROM TaiKhoan tk WHERE tk.id =:id")
    TaiKhoan findId(@Param("id") Long id);

    // xuat excel khach hang
    @Query("SELECT tk FROM TaiKhoan tk " +
            "WHERE tk.vaiTro.id = 3")
    List<TaiKhoan> findAllKhachHangExcel();

    // xuat excel nhan vien
    @Query("SELECT tk FROM TaiKhoan tk " +
            "WHERE tk.vaiTro.id = 2")
    List<TaiKhoan> findAllNhanVienExcel();

    @Query("SELECT u FROM TaiKhoan u WHERE u.email =:email")
    TaiKhoan findGmail1(String email);

}
