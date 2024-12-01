using System.ComponentModel.DataAnnotations;

namespace HeavenBookStore.Models
{
    public class KhachHang
    {
        [Key]
        public string maKhachHang { get; set; }
        [Required]
        public string tenKhachHang { get; set; }
        public string hinhAnh { get; set; }
        public string diaChi { get; set; }
        public string matKhau { get; set; }
        public string email {  get; set; }
        public string sdt { get; set; }
        public DateTime ngaySinh { get; set; }
        public DateTime ngayDangKy { get; set; }
        public string hangThanhVien {  get; set; }
        public string maGioHang { get; set; }
    }
}
