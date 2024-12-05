using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreMVC.Models
{
    public class KhachHang
    {
        [Key]
        public required string MaKhachHang { get; set; }
        public string? TenKhachHang { get; set; }
        public string? HinhAnh { get; set; }
        public string? DiaChi { get; set; }
        public required string MatKhau { get; set; }
        public required string Email { get; set; }
        public string? SDT { get; set; }
        public DateTime? NgaySinh { get; set; }
        public DateTime NgayDangKy { get; set; }
        public string? HangThanhVien { get; set; }
        public required string MaGioHang { get; set; }
        public bool TrangThai { get; set; }
    }
}
