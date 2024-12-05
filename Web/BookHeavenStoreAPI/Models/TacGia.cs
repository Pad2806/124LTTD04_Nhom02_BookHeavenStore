using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class TacGia
    {
        [Key] 
        public string MaTacGia {  get; set; }
        [Required]
        public string TenTacGia { get; set; }
        public int? SoLuongTP { get; set; }
        public string? HinhAnh {  get; set; }
    }
}
