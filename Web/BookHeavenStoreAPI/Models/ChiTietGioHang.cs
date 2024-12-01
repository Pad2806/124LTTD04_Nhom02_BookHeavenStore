using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class ChiTietGioHang
    {
        [Key]
        public string MaGioHang { get; set; }
        [Required]
        public string MaSach {  get; set; }
        public int SoLuong { get; set; }
        public int ThanhTien { get; set; }
    }
}
