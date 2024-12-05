using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class ChiTietDonHang
    {
        [Key]
        public string MaDonHang { get; set; }
        [Required]
        public string MaSach {  get; set; }
        public int SoLuong { get; set; }
        public int ThanhTien { get; set; }
        public string? MaDanhGia { get; set; }
        public int? DanhGia { get; set; }
        public string? NoiDung {  get; set; }
    }
}
