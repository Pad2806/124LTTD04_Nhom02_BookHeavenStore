using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreMVC.Models
{
    public class GioHang
    {
        [Key] 
        public string MaGioHang { get; set; }
        [Required]
        public int? TongTien { get; set; }
    }
}
