using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreMVC.Models
{
    public class PTTT
    {
        [Key]
        public string MaPTTT { get; set; }
        [Required]
        public string TenPTTT { get; set; }
    }
}
