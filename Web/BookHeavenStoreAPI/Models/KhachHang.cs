using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class KhachHang
    {
        [Key]
        public string MaKhachHang { get; set; }
        [Required]
        public string TenKhachHang { get; set; }
        public string HinhAnh { get; set; }
        public string DiaChi { get; set; }
        public string MatKhau { get; set; }
        public string Email { get; set; }
        public string SDT { get; set; }
        public DateTime NgaySinh { get; set; }
        public DateTime NgayDangKy { get; set; }
        public string HangThanhVien { get; set; }
        public string MaGioHang { get; set; }
    }
}
