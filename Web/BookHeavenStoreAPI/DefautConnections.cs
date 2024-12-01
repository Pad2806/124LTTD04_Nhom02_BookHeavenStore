using BookHeavenStoreAPI.Models;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI
{
    public class DefautConnections : DbContext
    {
        public DefautConnections(DbContextOptions<DefautConnections> options) : base(options)
        {
        }
        public DbSet<KhachHang> KhachHang { get; set; }
        public DbSet<Sach> Sach { get; set; }
        public DbSet<NXB> NXB { get; set; }
        public DbSet<TacGia> TacGia { get; set; }
        public DbSet<TheLoai> TheLoai { get; set; }
        public DbSet<PTTT> PTTT { get; set; }
        public DbSet<GioHang> GioHang { get; set; }
        public DbSet<DonHang> DonHang { get; set; }
        public DbSet<ChiTietGioHang> ChiTietGioHang { get; set; }
        public DbSet<ChiTietDonHang> ChiTietDonHang { get; set; }
    }
}
