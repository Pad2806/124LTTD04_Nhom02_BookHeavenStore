using Org.BouncyCastle.Asn1.X509;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class DonHang
    {
        [Key]
        public string MaDonHang { get; set; }
        [Required]
        public string MaKhachHang { get; set; }
        public string? TenDonHang { get; set; }
        public int TongTien {  get; set; }
        public int? GiamGia { get; set; }
        public string TrangThai { get; set; }
        public int? PhiVanChuyen { get; set; }
        public string MaPTTT { get; set; }
        public DateTime NgayDatHang { get; set; }
        public DateTime? NgayGiaoHang { get; set; }
        public DateTime? NgayThanhToan { get;set; }
    }
}
