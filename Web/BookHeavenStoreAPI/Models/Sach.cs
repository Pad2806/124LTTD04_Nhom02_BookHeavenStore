using System.ComponentModel.DataAnnotations;

namespace BookHeavenStoreAPI.Models
{
    public class Sach
    {
        [Key]
        public string MaSach {  get; set; }
        [Required]
        public string TenSach { get; set; }
        public string MaTheLoai { get; set; }
        public string MaTacGia { get; set; }
        public string MaNXB {  get; set; }
        public int? DonGiaBan { get; set; }
        public int? SoLuongCon {  get; set; }
        public int? SoLuongBan { get; set; }
        public string? HinhAnh {  get; set; }
        public string? MoTa {  get; set; }
        public int? DiemDanhGia {  get; set; }
    }
}
