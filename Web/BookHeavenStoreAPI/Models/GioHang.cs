using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class GioHang
    {
        [Key] 
        public string MaGioHang { get; set; }
        public int? TongTien { get; set; }
    }
}
