using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class TheLoai
    {
        [Key]
        public string MaTheLoai {  get; set; }
        [Required]
        public string TenTheLoai { get; set; }
        public string HinhAnh {  get; set; }
    }
}
