using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class NXB
    {
        [Key]
        public string MaNXB { get; set; }
        [Required]
        public string TenNXB { get; set; }
        public string DiaChi { get; set; }
        public string Email { get; set; }
    }
}
